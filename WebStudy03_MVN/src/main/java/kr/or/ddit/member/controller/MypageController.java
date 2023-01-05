package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Controller
public class MypageController {
	
	private MemberService service = new MemberServiceImpl(); //service와의 의존관계
	
	@RequestMapping("/mypage.do")
	public String myPage(
			HttpServletRequest req ,
			MemberVOWrapper principal
	){
			
//			MemberVOWrapper principal = (MemberVOWrapper) req.getUserPrincipal();
			//HttpSession session = req.getSession();
			//MemberVO authMember = (MemberVO)session.getAttribute("authMember");
			MemberVO authMember = principal.getRealMember();
			
			MemberVO member = service.retrieveMember(authMember.getMemId());// 한사람에 대한 모든 정보 조회후 membeㄱ에 넣기 
			
			req.setAttribute("member", member);
			
			return "member/memberView"; 
			//String viewName ="member/memberView"; //logical view name
			//5번단계 
			//new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
	
	}
	
}
