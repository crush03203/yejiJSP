package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;


import java.util.*;


//클라이언트가 접근할 수 없는 폴더에 있는 이미지를 서비스 하기 위해 매개역할을 하는 클래스 
public class ImageStreamingServlet extends HttpServlet {
	
	
	private String imageFolder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//imageFolder = config.getInitParameter("imageFolder"); //web.xml에 등록되어있음 
		application = getServletContext();
		imageFolder = application.getInitParameter("imageFolder");
		System.out.printf("받은 파라미터 : %s\n", imageFolder);
	}

	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
						throws IOException, ServletException 
	{
		
		//가장먼저 생성되고 가장 오래 살아남는 객체, 가장 범위가 넓은 저장소  => ServletContext 
		// "톰캣이랑 커뮤니케이션 하기 위해" 서블릿컨텍스트 받아오는 것 (톰캣에 있는 정보를 끌어오기 위한것 )
		//ServletContext application = getServletContext();
		
		//응답데이터에 이미지 컨텐츠를 포함시키기 위해 
		 //servers>web.xml에 있음 
		String imageName = req.getParameter("image"); 
		if(imageName == null || imageName.isEmpty()) { //파라미터가 존재하지 않거나 whitespace로 파라미터가 넘어왔을 경우
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST); //에러메시지 클라이언트한테 400오류화면 보내주기   
			return; //일단 멈추고 아래 코드는 더이상 실행하지 않는다. 
		}

		String mimeType = application.getMimeType(imageName);
		resp.setContentType(mimeType);
		File imageFile = new File(imageFolder,imageName);
		
		if(!imageFile.exists()) { //파라미터값 이이상한게 들어왔을 경우  ?image=sdfsdfsdf 없는 이미지가 들어왔을 경우 
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); //에러메시지 클라이언트한테 404오류화면 보내주기   
			return; //일단 멈추고 아래 코드는 더이상 실행하지 않는다. 
		}
		
		
//		이시점에서 쿠키를 리스판스에서 숨겨야함
		Cookie imageCookie = new Cookie("imageCookie", imageName);
		imageCookie.setPath(req.getContextPath());
		imageCookie.setMaxAge(60*60*24*3);
		resp.addCookie(imageCookie);
		
		
		FileInputStream fis=null;
		OutputStream os=null;
		try {
			fis = new FileInputStream(imageFile);
			os = resp.getOutputStream();
			
			//이미지 끝날 때까지 반복
			int tmp = -1;
			while((tmp = fis.read()) != -1){
				os.write(tmp);
			}
		} finally {
			if(fis!=null)	
				fis.close();
			if(os !=null)
				os.close();
		}
		
		
		
		
		
		

		
		
	}

}