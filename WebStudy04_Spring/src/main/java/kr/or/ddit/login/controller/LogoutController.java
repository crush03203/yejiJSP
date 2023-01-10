package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class LogoutController {
	
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String process(HttpSession session){
		
		//HttpSession session =   req.getSession();
		
//		session.removeAttribute("authMember"); //한사람이 session에 몇개의 정보를 넣을지모를일임 일일히 삭제할수도 없는 노릇...  => 아래처럼 invalidate을 사용하면 얘가 알아서 session속성 삭제하고 강제로 만료 시켜줌 
		session.invalidate();
		
		//세션 제거 후 웰컴페이지로 돌아가기 
		return "redirect:/";
		/*if(viewName.startsWith("redirect:")) { //redirect로 할 경우  if(redirect)
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else { //forward할 경우
			req.getRequestDispatcher(viewName).forward(req, resp);
		}*/
		
		
		
	}
}
