package kr.or.ddit.prod.controller;
//첫번째 파일 업로드 메타데이터로 저장
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController  {
   
   
   private ProdService service = new ProdServiceImpl();

   private OthersDAO othersDAO = new OthersDAOImpl();
   
   private void addAttribute(HttpServletRequest req) {
      req.setAttribute("lprodList", othersDAO.selectLprodList());
      req.setAttribute("buyerList", othersDAO.selectBuyerList(null)); //"전체" 거래처 조회 
   }
   
   
   
   @RequestMapping("/prod/prodInsert.do")
   public String process(HttpServletRequest req)  {
      addAttribute(req);
      return "prod/prodForm";
   }

   
   
   
   @RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
   public String insertProcess(
         HttpServletRequest req
         ,@ModelAttribute("prod") ProdVO prod //모든 ProdVO정보를 한번에 받을수있음 =>ProdVO이거를 받으려면 @ModelAttribute로 받을수있음 
         ,@RequestPart("prodImage")MultipartFile prodImage
		   ) throws IOException {
      
      addAttribute(req);
      
//      if(req instanceof  MultipartHttpServletRequest) {
//         MultipartHttpServletRequest wrapperReq =  (MultipartHttpServletRequest) req;
//         //prodImage -> prodImg
//         MultipartFile prodImage = wrapperReq.getFile("prodImage"); //<input type="file" name="prodImg" accept="image/*" />
         if(prodImage != null && !prodImage.isEmpty()) { //prodImage있으면 실행 
        	 //prodImage 클라이언트가 보낼때 이밎
        	 //prodImg : DB용
//            1. 저장
            String saveFolderURL = "/resources/prodImages"; 
            ServletContext application = req.getServletContext();
            String saveFolderPath = application.getRealPath(saveFolderURL);
            File saveFolder = new File(saveFolderPath);
            if(!saveFolder.exists()) { //얘가 실제로 있나 없나 확인
               saveFolder.mkdirs();
            }
            
//            2. METADATA 추출
            String saveFileName = UUID.randomUUID().toString();
            prodImage.transferTo(new File(saveFolder, saveFileName));

            
//            3.DB 저장 : prodImg 만들기 
            prod.setProdImg(saveFileName);
            
         }   
//      }
      
      
      //errors에 대한 map 만들기 => map의 type은 string, value는 List<String>
      Map<String, List<String>> errors = new LinkedHashMap<>();
      req.setAttribute("errors", errors); 
      boolean valid =  ValidationUtils.validate(prod, errors, InsertGroup.class);
      
      String viewName = null;
      if(valid) {
         ServiceResult result =  service.createProd(prod);
         if(ServiceResult.OK == result) {
            viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
         }else {
            req.setAttribute("message", "서버오류 조금있다가 다시" );
            viewName = "prod/prodForm";
         }
      }else { //검증통과못했을 경우
         viewName = "prod/prodForm";
      }
      return viewName;
      
   
   }
   
}