package kr.or.ddit.login.controller;

import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String process(HttpSession session)  {
//		session.removeAttribute("authMember");
		session.invalidate();
		
		return  "redirect:/";
		
	}
}
