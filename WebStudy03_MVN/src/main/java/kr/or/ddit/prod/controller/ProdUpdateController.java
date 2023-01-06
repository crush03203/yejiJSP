package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdUpdateController {

	// 서비스와 의존관계 생성
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	private ProdDAO prodDAO = new ProdDAOImpl();

//	2.이게 실행된다
	private void addAttribute(HttpServletRequest req) {
		//"prod"값은 prodform에서 생성 해주기 위한 것 
		req.setAttribute("prod", prodDAO.selectProd(req.getParameter("what")));
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null)); // "전체" 거래처 조회
	}
	
	// req 요청을 받는 것  name="what" 에  value="${prod.prodId }"다
  @RequestMapping("/prod/prodUpdate.do") //GET 
  public String process(HttpSession session, HttpServletRequest req)  {
     addAttribute(req); // 1 이걸 실행되면
     return "prod/prodForm"; //실행이 끝나면 리턴해줄 값
  }
  
	@RequestMapping(value = "/prod/prodUpdate.do", method = RequestMethod.POST)
	public String updateProd(
			HttpSession session, HttpServletRequest req, 
			@ModelAttribute("prod") ProdVO prod,
			@RequestPart("prodImage") MultipartFile prodImage

	) throws IOException {
		addAttribute(req);
		
		prod.setProdImage(prodImage);
		
		String saveFileName = prod.getProdImg();
		
		if (saveFileName != null) {
//			1. 저장
			String saveFolderURL = "/resources/prodImages";
			ServletContext application = req.getServletContext();
			String saveFolderPath = application.getRealPath(saveFolderURL);
			File saveFolder = new File(saveFolderPath);
			if (!saveFolder.exists()) { // 얘가 실제로 있나 없나 확인
				saveFolder.mkdirs();
			}
//            2. METADATA 추출
			prodImage.transferTo(new File(saveFolder, saveFileName));

//            3.DB 저장 : prodImg 만들기 

		}
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);

		String viewName = null;
		if (valid) {
			ServiceResult result = service.modifyProd(prod);
			if (ServiceResult.OK == result) {
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
			} else {
				req.setAttribute("message", "서버오류 조금있다가 다시");
				viewName = "prod/prodForm";
			}
		} else { // 검증통과못했을 경우
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}
