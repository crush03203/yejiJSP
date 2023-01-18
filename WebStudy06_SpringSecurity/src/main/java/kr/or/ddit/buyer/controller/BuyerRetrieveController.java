package kr.or.ddit.buyer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/buyer")
public class BuyerRetrieveController{
	private final BuyerService service;
	
	@RequestMapping("buyerList.do")
	public String buyerList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("simpleCondition") SearchVO simpleCondition
			, Model model
	){
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(4,2); 
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		
		service.retrieveBuyerList(pagingVO);
		
		model.addAttribute("pagingVO" , pagingVO);
		
		log.info("paging data : {}", pagingVO);
		
		return "buyer/buyerList";
	}
	
	@RequestMapping("buyerView.do")
	public String buyerView(
		@RequestParam("what") String buyerId
		, Model model
	) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
		return "buyer/buyerView";
	}
}











