package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.SeekableByteChannel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");

		MemberVO member = service.retrieveMember(authMember.getMemId());

		req.setAttribute("member", member);

		String viewName = "member/memberForm";

		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청 분석
		req.setCharacterEncoding("UTF-8");

		MemberVO member = new MemberVO();
		req.setAttribute("member",member);

		Map<String, String[]> parameterMap = req.getParameterMap();

		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		String viewName = null;
		
	      
	      //검증하기 
	      Map<String, List<String>> errors = new LinkedHashMap<>();
	      req.setAttribute("errors", errors); // 세팅한 errors jsp로 공유하기 위해서 세팅해주기 
	      boolean valid = ValidationUtils.validate(member, errors, UpdateGroup.class);
	      if(valid) {
	         ServiceResult result = service.modifyMember(member);//사용자가 작성한 모든 값들 들어가있음
	         switch (result) {
	         case INVALIDPASSWORD: //비번오류가 났다면
	            req.setAttribute("message", "비밀번호 오류");
	            viewName = "member/memberForm";
	            break;
	         case FAIL: //수정실패했다면(서버에 문제가 생겼을 경우)
	            req.setAttribute("message", "서버오류, 조금있다 다시하시오 ");
	            viewName = "member/memberForm";
	            break;
	         default: //수정성공했다면 (오케이) 
	            viewName = "redirect:/mypage.do";
	            break;
	         }
	      }else {
	         viewName = "member/memberForm";
	      }
	      
		
		
		ServiceResult result = service.modifyMember(member);
		switch (result) {
		case INVALIDPASSWORD:
			req.setAttribute("message", "비번 오류");
			viewName = "member/memberForm";
			break;
		case FAIL:
			req.setAttribute("message", "서버에 문제 있음. 쫌따 다시 하셈.");
			viewName = "member/memberForm";
			break;

		default:
			viewName = "redirect:/mypage.do";
			break;
		}

		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
	}
}
