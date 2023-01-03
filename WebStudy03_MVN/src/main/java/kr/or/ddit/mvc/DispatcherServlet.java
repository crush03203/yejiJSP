package kr.or.ddit.mvc;


import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.commons.IndexController;
import kr.or.ddit.login.controller.LoginProcessController;
import kr.or.ddit.login.controller.LogoutController;
import kr.or.ddit.member.controller.MemberInsertController;
import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.member.controller.MemberViewController;
import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.mvc.view.ViewResolver;
import kr.or.ddit.prod.controller.ProdInsertController;
import kr.or.ddit.prod.controller.ProdListController;

//컨트롤러에서 반복적으로 사용되던 걸 피하기 위해서 
//이제 서블릿은 front controller만 가지고있으면 됨 
//나머지 컨트롤러에는 서블릿 더이상 필요없어짐 
public class DispatcherServlet extends HttpServlet{

   private ViewResolver viewResolver;
   //어노테이션 생성 중~~
   private HandlerMapping handlerMapping;
   private HandlerAdapter handlerAdapter;
   
   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      viewResolver = new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
      handlerMapping = new RequestMappingHandlerMapping("kr.or.ddit");
      handlerAdapter = new RequestMappingHandlerAdapter();
   }
   
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      
      String requestURI = req.getServletPath();
      RequestMappingInfo mappingInfo = handlerMapping.findCommandHalder(req);
//      //컨트롤러 이동
//      AbstractController controller = null;
//      }else if("/prod/prodList.do".equals(requestURI)) {
//         controller = new ProdListController();
//      }else if("/member/memberView.do".equals(requestURI)) {
//         controller = new MemberViewController();
//      }else if("/index.do".equals(requestURI)) {
//         controller = new IndexController();
//      }else if("/login/loginProcess.do".equals(requestURI)) {
//         controller = new LoginProcessController();
//      }else if("/login/logout.do".equals(requestURI)) {
//         controller = new LogoutController();
//      }else if("/prod/prodInsert.do".equals(requestURI)) {
//         controller = new ProdInsertController();
//      }
      

      
      if(mappingInfo==null) { //컨트롤러를 찾을수 없다면 
         resp.sendError(404,requestURI+" 는 처리할 수 없는 자원임(Not found)");
         return;
      }
      
//      String viewName = controller.process(req, resp);
      //어노테이션 생성중~~~~
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