package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 상세 조회시, 해당 거래처의 모든 정보 함께 조회함. 상품 상세 조회시, 구매자 리스트(회원 아이디, 회원명, 휴대폰, 이메이르
 * 마일리지) 함께 조회 분류명도 함께 조회함. 회원정보 5개 테이블 조인하는 것/ 주데이터만 바뀐 것
 */
@WebServlet("/prod/prodView.do")
public class ProdViewControllerServlet extends HttpServlet {
//	컨트롤러와 서비스의 의존관계 생성 
	private ProdService service = new ProdServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//	1. 요청분석/검증
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400);
			return;
		}
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
	
		//	4. 뷰 선택 

		String viewName = "prod/prodView";
		// 5. 뷰 이동
		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
		
		//	아래 코드를 위에 한 줄로 바꿔줌
		//	if(viewName.startsWith("redirect:")) {
		//		viewName = viewName.substring("redirect:".length());
		//		resp.sendRedirect(req.getContextPath() + viewName);
		//	}else {
		//		req.getRequestDispatcher(viewName).forward(req, resp);
		//	}

	}

}
