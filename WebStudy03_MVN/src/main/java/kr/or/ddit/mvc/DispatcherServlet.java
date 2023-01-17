package kr.or.ddit.mvc;


import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.mvc.view.ViewResolver;

//컨트롤러에서 반복적으로 사용되던 걸 피하기 위해
//이제 서블릿은 front controller만 가지고있으면 됨 
//나머지 컨트롤러에는 서블릿 더이상 필요없어짐 , extends는 Servlet만 
public class DispatcherServlet extends HttpServlet{
//DispatcherServlet에서  handlerMapping,handlerAdapter,viewResolver
	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		handlerMapping = new RequestMappingHandlerMapping("kr.or.ddit");
		handlerAdapter = new RequestMappingHandlerAdapter();
		viewResolver = new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String requestURI = req.getServletPath();
		

		//이것들을 handlermapping으로 넘겼음 
		RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req); 
		if(mappingInfo==null) { //컨트롤러를 찾을수 없다면 
			resp.sendError(404,requestURI+" 는 처리할 수 없는 자원임(Not found)");
			return;
		}
		
		
		//mappingInfo안에 들어있는 정보를 가지고 실제로 화면에 띄울수있는 것들을 다시 분리 시킴 => handlerAdapter, RequestMappingHandlerAdapter
//		String viewName = controller.process(req, resp);
		String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
		if(viewName==null) {
			if(!resp.isCommitted()) { //응답상태코드가 이미 결정되어있지 않다면  500에러!  
				resp.sendError(500, "논리적인 뷰 네임은 null일 수 없음");
			}// 이미 결정되어있으면 그냥 결정되어있는 에러로 출력되면됨 
			
		}else {
			viewResolver.resolveView(viewName, req, resp);
		}
	}
	
}
