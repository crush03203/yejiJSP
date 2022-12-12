package kr.or.ddit.servlet04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet04.service.CalculateService;
import kr.or.ddit.servlet04.service.CalculateServiceImpl;
import kr.or.ddit.servlet04.service.PropertiesService;
import kr.or.ddit.servlet04.service.PropertiesServiceImpl;

@WebServlet("/04/calcuate")
public class CalculateServlet extends HttpServlet {

		//이 서블릿에서 service를 이용할 수 있게 가져옴 
		private CalculateService service = new CalculateServiceImpl();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String path = "/WEB-INF/views/03/calculateForm.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			//1. 요청분석 
			String accept  = req.getHeader("Accept"); //request Header 정보받아오기 / 들어오는 값 :  */* html json xml ... 

			//2. 모델 확보 
			int a = Integer.parseInt(req.getParameter("leftOp").trim());
			int b = Integer.parseInt(req.getParameter("rightOp").trim());
			String operator = req.getParameter("operator");
			

			Object target = null;
			if(operator.equals("PLUS")) { //operator가 + 면 
				target = service.plus(a, b);
			}else if(operator.equals("MINUS")) {
				target = service.minus(a, b);
			}else if(operator.equals("MULTIPLY")) {
				target = service.multiply(a, b);
			}else if(operator.equals("DIVIDE")) {
				target = service.divice(a, b);
			}

			//3. 모델공유
			req.setAttribute("target", target); //json, xml 각각의 마샬링메소드로 보내기 위한 코드	

			//4. 뷰선택 
			String path = null;
			if(accept.startsWith("*/*") || accept.toLowerCase().contains("html") ) {// request Header에   */*가 들어오거나 html 들어오면 
				path = "/WEB-INF/views/03/calculateForm.jsp";
			}else if(accept.toLowerCase().contains("json")) {
				path = "/jsonView.do"; //MarchallingJsonViewServlet.java
			}else if(accept.toLowerCase().contains("xml")) {
				path = "/xmlView.do";//MarchallingXmlViewServlet.java
			}
			
			
			//5. 뷰이동(뷰에 뿌려주기)
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		}
		

}
