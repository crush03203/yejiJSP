package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/now.do")
public class NowServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//1) 인포메이션 만들기 
		Date now = new Date();
		req.setAttribute("now", now); //a가 요청을 받아서 req에 정보를 담아놨음  = > tmplServlet.java의 req에 넘겨줌 
		
		
		//2) sample.tmpl에 있는 템플릿을 사용할 거야  
		//3) 제어권은 tmplServlet한테 넘겨짐 
		String viewPath = "/01/sample.tmpl";
		
		//b로 이동 sample.tmpl (tmplServlet에서 sample.tmpl을 읽어들임) 
		req.getRequestDispatcher(viewPath).forward(req, resp);
		
		
	}
	

}
