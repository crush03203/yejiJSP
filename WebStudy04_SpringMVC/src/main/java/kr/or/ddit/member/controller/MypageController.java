package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Controller
public class MypageController {
	
	@Inject
	private MemberService service ; //service와의 의존관계
	
	@RequestMapping("/mypage.do")
	public String myPage(
			Model model
			,MemberVOWrapper principal
	){
			
//			MemberVOWrapper principal = (MemberVOWrapper) req.getUserPrincipal();
			//HttpSession session = req.getSession();
			//MemberVO authMember = (MemberVO)session.getAttribute("authMember");
			MemberVO authMember = principal.getRealMember();
			
			MemberVO member = service.retrieveMember(authMember.getMemId());// 한사람에 대한 모든 정보 조회후 membeㄱ에 넣기 
			
			model.addAttribute("member", member);
			
			return "member/memberView"; 
			//String viewName ="member/memberView"; //logical view name
			//5번단계 
			//new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
	
	}
	
}
