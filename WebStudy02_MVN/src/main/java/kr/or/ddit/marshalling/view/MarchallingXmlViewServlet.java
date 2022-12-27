package kr.or.ddit.marshalling.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@WebServlet("/xmlView.do")
public class MarchallingXmlViewServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//			Object target =  req.getAttribute("target"); //위에 setAttribute내용을 가져와서 target에 넣어주 ㅁ
		Enumeration<String> names = req.getAttributeNames();
		Map<String, Object> target = new HashMap<>();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Object value = req.getAttribute(name);
			target.put(name,value);
		}
		ObjectMapper mapper = new XmlMapper();
		// 1. marchalling
		// String json = mapper.writeValueAsString(target); //마샬링
		resp.setContentType("application/xml;charset=UTF-8");

		try (PrintWriter out = resp.getWriter();) {
			// out.print(json); //직렬화
			mapper.writeValue(out, target); // 마샬링, 직렬화 한번에 쫙 해준거
		}

	}

}
