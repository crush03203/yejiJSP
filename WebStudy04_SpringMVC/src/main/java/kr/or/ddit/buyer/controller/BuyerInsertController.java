package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerInsert.do")
public class BuyerInsertController{
	@Inject
	private BuyerService service;
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList() {
		return othersDAO.selectLprodList();
	}

	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	@RequestMapping
	public String prodForm(){
		return "buyer/buyerForm";
	}

	@PostMapping
	public String insertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer
		, Errors errors
		, Model model
	) throws IOException {
		String viewName = null;
		if(!errors.hasErrors()) {
			int rowcnt = service.createBuyer(buyer);
			if(rowcnt > 0) {
				viewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
			}else {
				model.addAttribute("message", "서버 오류, 쫌따 다시");
				viewName = "buyer/buyerForm";
			}
		}else {
			viewName = "buyer/buyerForm";
		}
		return viewName;
	}
}

















