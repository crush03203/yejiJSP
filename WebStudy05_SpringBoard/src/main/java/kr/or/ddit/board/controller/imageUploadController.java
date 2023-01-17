package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class imageUploadController {
	
	@Value("#{appInfo.imageFolder}")
	private File imageFolder;
	@Value("#{appInfo.imageFolder}")
	private String imageFolderURL;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("이미지 저장 경로 : {} ", imageFolder.getCanonicalPath());
		if(!imageFolder.exists())
			imageFolder.mkdirs();
	}
	
	@RequestMapping(value="/board/boardImage.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  Map<String, Object> uploadImage(@RequestPart MultipartFile upload, HttpServletRequest req) throws IllegalStateException, IOException {
//		1. 업로드 : 2진데이터 저장(web resource)
		String saveName = UUID.randomUUID().toString();
		upload.transferTo(new File(imageFolder,saveName));
//		2. JSON 생성(filename, uploaded, url)
//		2-1 마샬링 첫번째
		Map<String, Object> marshallintTarget = new HashMap<>();
		marshallintTarget.put("filename", upload.getOriginalFilename());
		marshallintTarget.put("uploaded",1);
		String url = req.getContextPath() + imageFolderURL + "/" + saveName;
		marshallintTarget.put("url",url);
		return marshallintTarget;
	}
}
