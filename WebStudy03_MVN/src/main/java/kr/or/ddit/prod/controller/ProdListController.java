package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.ListUI;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
public class ProdListController  { //implements AbstractController 컨트롤러의 맨 마지막은 viewName이동해야함
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null)); //"전체" 거래처 조회 
	}
	
	
	//ui에 대한 요청은 여기서 =>  ui만 필요한 경우  => 그냥 뷰 이동만 하면됨 
	private String listUI(HttpServletRequest req) {
		addAttribute(req);
		return "prod/prodList";
	}
	//데이터에 대한 요청은 여기서 => 데이터만 필요한 경우 => 마샬링 필요 
	//@RequestParam(value="page",required=false, defaultValue)  => page는 필수파라미터가 아니다 (required=false)
	private String listData(
			int currentPage
			,HttpServletRequest req
	) throws ServletException  {
//		String pageParam = req.getParameter("page");
		ProdVO detailCondition = new ProdVO();
		req.setAttribute("detailCondition", detailCondition);
/*		detailCondition.setProdLgu(req.getParameter("prodLgu"));
		detailCondition.setProdBuyer(req.getParameter("prodBuyer"));
		detailCondition.setProdName(req.getParameter("prodName"));
*/
		try {
			BeanUtils.populate(detailCondition, req.getParameterMap());//검색조건이 늘어나더라도 넘어오는 파라미터 명과 set값이 같다면 beanUtils로 쓰면 편하게 사용할 숭 ㅣㅆ음 
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		} 
		
		
/*		int currentPage = 1; //페이지 파라미터가 없다면 1페이지
		if(StringUtils.isNumeric(pageParam)) { //파라미터를 숫자로 변환 
			currentPage = Integer.parseInt(pageParam);
		}*/
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition); //페이징에 검색조건 추가(상세 검색 포함되어있음 )
		
		service.retrieveProdList(pagingVO);
		req.setAttribute("pagingVO", pagingVO);
		
		return "forward:/jsonView.do";
	}
	

	@RequestMapping("/prod/prodList.do") //single value annotation
	public String prodList(
			@RequestParam(value="page",required=false, defaultValue="1") int currentPage
			,HttpServletRequest req
	)  throws ServletException {

		//req.setCharacterEncoding("UTF-8");  이미 프론트가 가지고있기 때문에필요없음 
		
		
		String accept = req.getHeader("Accept");
		String viewName = null;
		if(accept.contains("json")) {
			viewName = listData(currentPage, req); 
		}else {
			viewName =  listUI(req);
		}
		return viewName; 

		//이미 프론트가 가지고있기 때문에필요없음 
		//new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
		
	
	}	
}
