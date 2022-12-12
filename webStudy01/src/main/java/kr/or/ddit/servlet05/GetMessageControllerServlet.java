package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Local;

import com.sun.net.httpserver.spi.HttpServerProvider;

@WebServlet("/04/getGreetingMessage")
public class GetMessageControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청분석(line,header,body)
		String accept = req.getHeader("Accept");
		String locale = req.getParameter("locale");
		Locale clientLocale = null;
		if(locale !=null) {
//			ko,en : language tag , locale code
			clientLocale = Locale.forLanguageTag(locale);
		}else {
			clientLocale = req.getLocale(); //Accept-loanguage header로 결정됨.
		}
		String name = req.getParameter("name"); //asdfasf
		if(name == null ||name.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
//		2. 모델확보
		Object message = null;
		try {
			message = retrieveMessage(clientLocale, name);
		} catch (MissingResourceException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
//		3. 모델 공유(setAttribute)
//		{"message":"HELLO"}
//		Map<String, Object> target = Collections.singletonMap("message", message);
		req.setAttribute("message", message);
//		4. 뷰선택
		String viewName = null;
		int statusCode = HttpServletResponse.SC_OK;
		if(accept.contains("json")) {
			viewName = "/jsonView.do";
		} else if(accept.contains("xml")) {
			viewName = "/xmlView.do";
		} else if (accept.contains("plain")) {
			viewName = "/WEB-INF/views/04/plainView.jsp";
		} else {
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}
		
		if(statusCode == HttpServletResponse.SC_OK) {
//		5. 뷰로 이동
			req.getRequestDispatcher(viewName).forward(req, resp);
		} else {
			resp.sendError(statusCode);
			//나는 니가 요구하는 데이터타입을 만들어 줄 수가 없어
		}
	}

	private Object retrieveMessage(Locale clientLocale, String name) {
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName,clientLocale);
		return bundle.getString(name);
	}
}
