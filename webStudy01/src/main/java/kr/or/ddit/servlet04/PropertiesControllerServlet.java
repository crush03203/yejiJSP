package kr.or.ddit.servlet04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.servlet01.DescriptionServlet;
import kr.or.ddit.servlet04.service.PropertiesService;
import kr.or.ddit.servlet04.service.PropertiesServiceImpl;

@WebServlet("/03/props/propsView.do")
public class PropertiesControllerServlet extends HttpServlet {

	private PropertiesService service = new PropertiesServiceImpl(); // 컨트롤러가 servlet04 > service >
																		// PropertiesService.java 서비스를 사용할수있도록가져옴

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청분석
		String accept = req.getHeader("Accept"); // */* html json xml ...

		// 모델 확보
		Object target = service.retrieveData();
		// 모델 공유(다른 것들이 target을 가져다 쓸수 있도록 준비 )
		req.setAttribute("target", target); // json, xml 각각의 마샬링메소드로 보내기 위한 코드

		// 뷰선택
		String path = null;

		if (accept.startsWith("*/*") || accept.toLowerCase().contains("html")) { // request Header에 */*가 들어오거나 html 들어오면

			path = "/WEB-INF/views/03/propsView.jsp";

		} else if (accept.toLowerCase().contains("json")) {

			path = "/jsonView.do"; // service에서 받아온 내용이 json이다? 그럼 일단 마샬링을 통해서 번역 필요 ( MarchallingJsonViewServlet 에
									// 있는 서비스로 이동하는 소스)

		} else if (accept.toLowerCase().contains("xml")) {

			path = "/xmlView.do"; // MarchallingXmlViewServlet 에 있는 서비스로 이동하는 소스

		}

		// 뷰이동
		req.getRequestDispatcher(path).forward(req, resp);

	}

}
