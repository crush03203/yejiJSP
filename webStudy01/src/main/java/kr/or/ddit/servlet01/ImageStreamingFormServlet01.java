package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//마크어노테이션 :  @WebServlet
//싱글 어노테이션 : @WebServlet("index.do")
// 멀티벨류 어노테이션 : (value="/01/imageForm.do",initParams= {@WebInitParam})
//xml에 있는 내용물을 파라미터값으로 받아서 하나로 돌려쓸수있게 만들었다 
@WebServlet(value="/01/imageForm.do")
//,initParams= {@WebInitParam(name="imageFolder", value="D:\\contents\\images")})
public class ImageStreamingFormServlet01 extends HttpServlet{
	
	private ServletContext application;  //단 한번 생성되는 싱글톤 객체 
	private String imageFolder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext(); //단 한번 생성되는 싱글톤 객체 
		imageFolder = application.getInitParameter("imageFolder");
		//imageFolder = config.getInitParameter("imageFolder");
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		
		//String imageFolder = "D:\\contents\\images";
		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles((dir, name)-> { //이미지 파일들의 확장자들을 필터링 해서 배열 안에다 넣기 
			String mime = application.getMimeType(name); //그 이미지들의 확장자의 마임타입을 체크 
			return mime != null && mime.startsWith("image/"); 
			//image로 시작되면 이미지확장자확인하고 ? 
		    // (mime != null) => hwp한글파일은 톰캣이 읽지 못하기 때문에 null로 받게 됨 
		});
		
		StringBuffer content = new StringBuffer();
		content.append("<html>\n");
		content.append("	<body>        \n");
		
		
		
		content.append(String.format("<form action='%s/imageStreaming.do'>\n", req.getContextPath()));
		content.append("<select name='image'>  \n");
		String pattern = "<option>%s</option>\n";
		for(File tmp :imageFiles) {
			content.append(String.format(pattern, tmp.getName()));
		}
		content.append("</select> \n");
		content.append("<input type='submit' value='전송' />");
		content.append("</form>");
		
		content.append("	</body>       \n");
		content.append("</html>           \n");
		
		
		//try width resource구문  : 내가 직접 close할 필요 없음 알아서 close 됨 
		try(
			//resp.getOutputStream();
			PrintWriter out = resp.getWriter();
		){
			out.println(content);
		}
	
	}

}
