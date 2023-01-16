package kr.or.ddit.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller // front뒤에서 동작하는 command handler가 됨
public class MemberListController { // 완벽한 POJO가 됐음

	private final MemberService service;

	@RequestMapping("/member/memberList.do") // 메소드가 get으로 숨어있음
	public ModelAndView memberList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			,@ModelAttribute SearchVO simpleConition
			) {

		PagingVO<MemberVO> pagingVO = new PagingVO<>(4, 2); // 4개와 2개 페이지만 보여줌
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleConition); // 검색
		
		
		ModelAndView mav = new ModelAndView();

		// 2 모델확보 - 모델레이어 사용(service 가져와서 사용)
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		mav.addObject("pagingVO", pagingVO);

		log.info("paging data : {}", pagingVO);
		
		mav.setViewName("member/memberList");

		return mav;
	}
}
