package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileUploadController {

	@RequestMapping(value="/file/upload.do", method=RequestMethod.POST)
//	@PostMapping("/file/upload.do")
	public String upload(HttpServletRequest req, HttpSession session) throws IOException, ServletException {
		String textPart =  req.getParameter("textPart");
		String numPart = req.getParameter("numPart");
		String datePart = req.getParameter("datePart");
		
		log.info("textPart: {}", textPart);
		log.info("numPart: {}", numPart);
		log.info("datePart: {}", datePart);
		
		session.setAttribute("textPart", textPart);
		session.setAttribute("numPart", numPart);
		session.setAttribute("datePart", datePart);
		
		String saveFolderURL = "/resources/prodImages"; 
		ServletContext application = req.getServletContext();
		String saveFolderPath = application.getRealPath(saveFolderURL);
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) { //얘가 실제로 있나 없나 확인
			saveFolder.mkdirs();
		}
		
	  List<String> metadata =  req.getParts().stream()
			  		//p.getContentType().startsWith("image/") => 이미지가 아닌 놈은 걸러냄
					  .filter((p)->p.getContentType() != null && p.getContentType().startsWith("image/")) //p: 파일파트 하나씩의 정보를 가지고있음 /  업로드 되는 파일의 정보를 담고있음 /       문자파트는 걸러내짐  콘솔창 결과 두개 => 이거 쓰기 전에는 콘솔창 결과 5개였음 
					  .map((p)->{ //map을 사용하면 그 안에 있는 element type을 바꿀수있음 
						  
						  //원본 파일명
						  String originalFilename =  p.getSubmittedFileName(); //원본 파일명
						 
						  //저장파일명
						  //원본파일명으로는 저장하지 않아야한다. => 이유는? 서로 다른 사용자가 같은 파일을 업로드 하게 되면 같은 파일명을 쓰게 될 경우 덮어씌워지게 됨 / 디도스공격때문에 
						  String saveFilename = UUID.randomUUID().toString(); //겹치지 않는 16진수의 긴 파일명이 만들어짐
					  
						  //실제로 저장 -  saveFilename이라는 이름으로 saveFolderPath에 저장 
						  File saveFile = new File(saveFolder, saveFilename);

						  try {
							p.write(saveFile.getCanonicalPath());//저장경로를 포함하고 있는 퀄리파이드네임 
							String saveFileURL = saveFolderURL + "/" + saveFilename; ///resources/prodImages/sdfsdfsdg
							return saveFileURL;//문자열 반환됨 
						  } catch (IOException e) {
							throw new RuntimeException(e);
						  } 
					  
					  }).collect(Collectors.toList());
	  
	  //DB에 저장했따고 가정하고 metadata을 session에 저장
	  session.setAttribute("fileMetadata", metadata);
	  
	  
	  
		
		return "redirect:/fileupload/uploadForm.jsp";
	}
	
	
	
	
	
}
