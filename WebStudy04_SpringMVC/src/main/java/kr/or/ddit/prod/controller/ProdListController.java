package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.ui.PaginationRenderer;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod")
public class ProdListController{
	
	@Resource(name="bootstrapPaginationRender")
	private PaginationRenderer renderer;
	
	@Inject
	private ProdService service;
	
	@Inject
	private OthersDAO othersDAO;
	
	
	//스크롤 내리는 분류부분
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList() {
		return othersDAO.selectLprodList();
	}
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList() {
		return othersDAO.selectBuyerList(null);
	}
	
	
	
	@GetMapping //동기요청 처리
	public String listUI() {
		return "prod/prodList";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //비동기요청 처리
	public String listData(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("detailCondition") ProdVO detailCondition
		, Model model
	) throws ServletException {
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		
		service.retrieveProdList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("pagingHTML", renderer.renderPagination(pagingVO)); //이 안에서 html 소스가 만들어짐
		
		return "jsonView";
		
	}
	
}















