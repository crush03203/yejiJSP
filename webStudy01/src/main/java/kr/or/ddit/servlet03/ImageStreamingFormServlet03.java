package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 현재 컨트롤러가 받아야하는 요청의 종류
	1. UI 제공 요청
	2. 데이터 제공요청 
 *
 *
 */
@WebServlet("/03/imageForm.do") //응답은 여기서 하고   (model 2방식)
public class ImageStreamingFormServlet03 extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//application은 이미 우리가 호출할 팔요 없음 톰캣 톨면서 이미 
		//application = getServletContext(); //단 한번 생성되는 싱글톤 객체 
		String imageFolder = getServletContext().getInitParameter("imageFolder");

		//resp.setContentType("text/html;charset=UTF-8");
		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles((dir, name)-> { //이미지 파일들의 확장자들을 필터링 해서 배열 안에다 넣기 
			String mime = getServletContext().getMimeType(name); //그 이미지들의 확장자의 마임타입을 체크 
			return mime != null && mime.startsWith("image/"); 
		});

		
		//req.setAttribute("imageFiles", imageFiles); //김치찌개를 setAttribute에 담아두고 jsp로 forword형식으로 갖다주기

		String accept = req.getHeader("Accept");
		if(accept.contains("json")) { //응답이 json으로 나갈 경우 
			//2. 데이터 제공요청 
			//Marchalling(마샬링)
			String json = marchalling(imageFiles);
			resp.setContentType("application/json"); //컨텐츠 타입을 정해서 넘길준비
			try(
				PrintWriter out = resp.getWriter(); //String이라서 컨텐츠를 담아서 넘길준비
			){
				out.print(json);
			}
	 
		}else { //json으로 나가지 않을 경우 (jsp로 출력)
			//1.UI 제공 요청 
			String viewName = "/WEB-INF/views/02/imageForm_ajax.jsp"; //보여주는 건 여기서 보여주고  (model 2방식)
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}

	private String marchalling(File[] imageFiles) {
		StringBuffer json= new StringBuffer();
		json.append("[");
		String ptrn = "\"%s\":\"%s\"";
		
		for(File tmp:imageFiles) {
			String name = tmp.getName(); //파일이름
			String mime = getServletContext().getMimeType(name);
			
			json.append("{");
			json.append(String.format(ptrn, "name", name)); //프로퍼티 한쌍 완성
			json.append(",");
			json.append(String.format(ptrn, "mime", mime));//프로퍼티 한쌍 완성
			json.append("}");
			json.append(",");
			//{name, mime}
		}
		json.append("]");
		int lastIndex = json.lastIndexOf(",");
		if(lastIndex!=-1)
			json.deleteCharAt(lastIndex);
		return json.toString();
	}
	

}
