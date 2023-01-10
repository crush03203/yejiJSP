package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@Controller
public class MemberViewController  {
	
	
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberView.do")
	public String process(
			@RequestParam(value="who",required=true) String memId
			,HttpServletRequest req
	) throws IOException {
		
		
		//1.
//		String memId = req.getParameter("who");
//		if(StringUtils.isBlank(memId)) { //누구를 상세조회 할것인지가 빠져있음 - 정상처리 불가
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);// 400에러
//			return null; 
//		}
		
		//2. 
		MemberVO member = service.retrieveMember(memId);
		
		//3. 
		req.setAttribute("member", member);
		
		//4.
		String viewName = "member/memberView";
		return viewName;
		
	}
	

}
