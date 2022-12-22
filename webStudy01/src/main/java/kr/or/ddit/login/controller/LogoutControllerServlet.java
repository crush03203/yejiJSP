package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		session.removeAttribute("authMember");
		session.invalidate(); //우리가 지우지 않더라도 세션을 자동으로 종료시켜준다
		
		String viewName = "redirect:/";
//		 5.
	      if(viewName.startsWith("redirect:")) {
	    	 viewName = viewName.substring("redirect:".length());
	         resp.sendRedirect(req.getContextPath() + viewName);
	      }else {
	         // 디스패치로 포워딩
	         req.getRequestDispatcher(viewName).forward(req, resp);
	         
	     
	   }
	}
}
