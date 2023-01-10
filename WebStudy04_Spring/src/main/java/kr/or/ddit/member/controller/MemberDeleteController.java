package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	
	private MemberServiceImpl service = new MemberServiceImpl(); //의존관계 + 결합력 생김  
	
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String memberDelete(
			HttpServletRequest req,
			HttpSession session
	) throws ServletException, IOException {
		
		//1. 
		//HttpSession session =  req.getSession(); //누구를 삭제할지 일단 알아야되니까 세션에서 사용자 정보 꺼내기
		MemberVO authMember = (MemberVO)   session.getAttribute("authMember");
		//req.getUserPrincipal(); //실제로 principal 객체를 만든적 없기 때문에 여기서 돌아오는 값은 null
		String memId = authMember.getMemId();
		String memPass = req.getParameter("memPass");
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);  //inputData에 받아온 memId 넣기 
		inputData.setMemPass(memPass);
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = ValidationUtils.validate(inputData, errors, DeleteGroup.class);
		
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
		//5
		//new InternalResourceViewResolver().resolveView(viewName, req, resp);
		
		
	}
}
