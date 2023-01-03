package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1.요청 분석 검증 누구라는 정보가 필요
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String memId = authMember.getMemId();
		String memPass = req.getParameter("memPass");
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = ValidationUtils.validate(inputData, errors, DeleteGroup.class);
		
		String viewName = null;
	      if(valid) { //만약 검증 성공했다? 그러면 
	         ServiceResult result = service.removeMember(inputData); //service에 있는 removeMember 실행 (inputData넘겨서)
	         switch (result) {
	         case INVALIDPASSWORD: //인증실패 마이페이지로 이동 
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
	      //5
	      new InternalResourceViewResolver().resolveView(viewName, req, resp);

	}
}
