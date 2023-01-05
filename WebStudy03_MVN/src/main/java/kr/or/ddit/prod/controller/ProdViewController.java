package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ctc.wstx.util.StringUtil;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;


/**
 * 상품상세조회시, 해당 거래처의 모든 정보 함께 조회함.  
 * 상품상세조회시, 구매자 리스트(회원아이디, 회원명, 휴대폰, 이메일, 마일리지) 함께 조회.
 * 분류명도 함께 조회함 - 다섯개 테이블 조인  
 * 
 */

@Controller
public class ProdViewController{
		
	private ProdService service = new ProdServiceImpl();

	@RequestMapping("/prod/prodView.do")
	public String doGet(
			@RequestParam("what") String prodId,
			HttpServletRequest req
	){

		//1 
/*		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {//필수파라미터가 안넘어왔다면 
			resp.sendError(400);
			return;
		}*/
		
		//뒷단에서 raw data를 받아서 information(김치찌개)으로 만듦
		ProdVO prod = service.retrieveProd(prodId);
		
		//공유
		req.setAttribute("prod", prod);

		
		//4.
		//String viewName = "prod/prodView"; //logical view name
		return "prod/prodView";
		//5.
		//new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);

	
	}
	
	
	
}
