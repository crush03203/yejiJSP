package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * backend controller(= command handler) -> POJO(Plain Old Java Object)
 */
@Slf4j
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController {
	
	@Inject
	private MemberService service;
	
	@ModelAttribute("member") //핸들러 메소드를 호출하기 전에 이걸 먼저 호출한다 모든 메소드에서 이걸 무조건 한번은 실행한다get, post 둘다 
	public MemberVO member() { //2번 3번씩 만들지 않고 객체를 재활용할 수 있다 
		log.info("@ModelAttribute 메소드 실행-> member 속성 생성");
		return new MemberVO();
	}

	@GetMapping
	public String memberForm() {
		return "member/memberForm";
	}

//	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST) //이 url로 받은 요청중에서 post만 받을 수 있다 
	@PostMapping
	public String memberInsert(HttpServletRequest req,
			@Validated(InsertGroup.class) 
		    @ModelAttribute("member") MemberVO member, Errors errors)
			throws ServletException, IOException {

		boolean valid = !errors.hasErrors();

		String viewName = null;

		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case FAIL:
				req.setAttribute("message", "서버에 문제 있음. 조금있다하시오");
				viewName = "member/memberForm";
				break;
			default:
				viewName = "redirect:/";
				break;
			}
		} else {
			viewName = "member/memberForm";
		}

		return viewName;

	}
}
