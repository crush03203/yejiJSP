package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;

/**
 * backend controller(= command handler) -> POJO(Plain Old Java Object)
 */


@Controller
public class MemberInsertController {
	
	private MemberService service = new MemberServiceImpl();

	@RequestMapping("/member/memberInsert.do")
	public String memberForm() {
		return "member/memberForm";
	}
	
	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST) //이 url로 받은 요청중에서 post만 받을 수 있다 
	public String memberInsert(
			HttpServletRequest req
			,@ModelAttribute("member") MemberVO member 
			,@RequestPart(value="memImage", required=false) MultipartFile memImage
	) throws ServletException, IOException {
		//목표)--memberInsert 할 때 회원 프로필 첨부하기
		// client에게 memImages로 가져와서
		
//		if(req instanceof MultipartHttpServletRequest) {
//			MultipartFile memImage = ((MultipartHttpServletRequest) req).getFile("memImage");
			member.setMemImage(memImage);
			
//				String saveFolderURL = "/resources/memImage";
//			ServletContext application = req.getServletContext();
//			String saveFolderPath = application.getRealPath(saveFolderURL);
//			File saveFolder = new File(saveFolderPath);
//			if(!saveFolder.exists()) {
//				saveFolder.mkdirs();
//			}
////		  2. METADATA 추출
//			String saveFileName = UUID.randomUUID().toString();
//			memImage.transferTo(new File(saveFolder, saveFileName));
//			
////          3.DB 저장 : prodImg 만들기 
//			member.setMemImg(saveFileName);
//		}
		
		//DB 저장할 땐 memImage로 저장한다
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors); 
		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);
		String viewName = null;

		/////////////////////////////////////////
		if(valid) {
			ServiceResult result =  service.createMember(member);
			switch (result) {
			case PKDUPLICATED: 
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;  
			case FAIL: 
				req.setAttribute("message", "서버에 문제 있음. 조금있다하시오");
				viewName = "member/memberForm";
				break;
			default:
				viewName = "redirect:/"; 
				break;
			}
		}else {
			viewName = "member/memberForm";
		}
		
		return viewName;

	}
	
	
}
