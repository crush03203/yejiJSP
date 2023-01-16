package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberDelete.do")
public class MemberDeleteController{
	
	@Inject
	private MemberServiceImpl service;
	
	@PostMapping
	public String memberDelete(
			HttpServletRequest req,
			HttpSession session,
			@Validated(DeleteGroup.class) 
		    @ModelAttribute("member") MemberVO member, Errors errors
		    
			)  throws ServletException, IOException {
		
		MemberVO authMember = (MemberVO)   session.getAttribute("authMember");
		String memId = authMember.getMemId();
		String memPass = req.getParameter("memPass");
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);  //inputData에 받아온 memId 넣기 
		inputData.setMemPass(memPass);
		
		Map<String, List<String>> errorsMap = new LinkedHashMap<>();
		
		boolean valid = errors.hasErrors();
		
		String viewName = null;
		
		if(valid) { //만약 검증 성공했다? 그러면 
			ServiceResult result = service.removeMember(inputData); //service에 있는 removeMember 실행 (inputData넘겨서)
			switch (result) {
			case INVAILDPASSWORD: //인증실패 마이페이지로 이동 
				session.setAttribute("message", "비번오류"); //message("비번오류")를 세션에 담는다
				viewName = "redirect:/mypage.do";
				break;
			case FAIL:
				session.setAttribute("message", "서버오류"); //message("서버오류")를 세션에 담는다
				viewName = "redirect:/mypage.do";
				break;
			default: //OK일 경우  => 탈퇴후 로그아웃 진행시켜야됨 
				session.invalidate();
				viewName = "redirect:/";
				break;
			}
		}else {
			session.setAttribute("message", "아이디나 비밀번호 누락 "); //message("아이디나 비밀번호 누락")를 세션에 담는다
			viewName = "redirect:/mypage.do";
		}
		return viewName;
		
	}

}