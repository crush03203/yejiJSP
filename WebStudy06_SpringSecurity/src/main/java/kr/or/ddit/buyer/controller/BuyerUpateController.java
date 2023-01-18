package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerUpdate.do")
public class BuyerUpateController{
	@Inject
	private BuyerService service;
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList() {
		return othersDAO.selectLprodList();
	}
	
	@RequestMapping
	public String prodForm(@RequestParam("what")String buyerId, Model model){
		
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
		
		return "buyer/buyerForm";
	}

	@PostMapping
	public String insertProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerVO buyer
		, BindingResult errors
		, HttpServletRequest req
	) throws IOException {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			int rowcnt = service.modifyBuyer(buyer);
			if(rowcnt > 0) {
				viewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
			}else {
				req.setAttribute("message", "서버 오류, 쫌따 다시");
				viewName = "buyer/buyerForm";
			}
		}else {
			viewName = "buyer/buyerForm";
		}
		return viewName;
	}
}

















