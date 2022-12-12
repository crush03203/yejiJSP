package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/02/imageForm.do") //1. 요청받기 
public class ImageStreamingFormServlet02 extends HttpServlet {
	
	private ServletContext application;  //단 한번 생성되는 싱글톤 객체 
	private String imageFolder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext(); //단 한번 생성되는 싱글톤 객체 
		imageFolder = application.getInitParameter("imageFolder");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		

		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles((dir, name)-> { 
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image/"); 
		});

		
		//#options#
		String pattern = "<option>%s</option>\n";
		StringBuffer options = new StringBuffer();
		for(File tmp :imageFiles) {
			options.append(String.format(pattern, tmp.getName()));
		}
		

		//2. 인포메이션 만들기 
		req.setAttribute("options", options);
		req.setAttribute("cPath", req.getContextPath()); //tmplServlet의 req로 던져주기
		
		//어떤 템플릿 사용할 건지 결정한 후 제어권은 tmplServlet에게 넘겨주기 
		//String viewPath = "/01/imageForm.tmpl";
		//req.getRequestDispatcher(viewPath).forward(req,resp);
		String viewName = "/WEB-INF/views/01/imageForm.jsp";
		req.getRequestDispatcher(viewName).forward(req,resp);
		
		
		
		
	
	
	}
}
