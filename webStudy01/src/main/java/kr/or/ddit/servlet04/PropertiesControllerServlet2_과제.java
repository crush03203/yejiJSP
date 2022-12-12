package kr.or.ddit.servlet04;

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

import kr.or.ddit.servlet01.DescriptionServlet;


@WebServlet("/03/props/propsView2.do")
public class PropertiesControllerServlet2_과제 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String accept = req.getHeader("Accept");
		if(accept.toLowerCase().contains("json")) {
			// native(DataStore.properties) -> json : marshalling
//			{"prop1" : "value1", ...}

			
		
			
			//StringBuffer => string으로 쌓아두기 
			StringBuffer json = new StringBuffer();

			//properties 파일 읽는 방법
			Properties properties = new Properties();
			try (
					//InputStream : /kr/or/ddit/props/DataStore.properties경로에 있는 걸  읽어들여라 
					// ** Outstream :   /kr/or/ddit/props/DataStore.properties경로에 있는 걸 뱉어내라 
					InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/props/DataStore.properties");	
				){
					//InputStream을 이용해서 /kr/or/ddit/props/DataStore.properties경로에 있는 걸 properties에다가 load해라 
					properties.load(is);
					System.out.println(properties.getProperty("prop1"));
					Enumeration<Object> en = properties.keys(); //properties안에있는 key들을 Enumeration형식으로 en에 담기 
					
					json.append("[");
					String ptrn = "\"%s\":\"%s\"";
					while (en.hasMoreElements()) { //en에 있는 만큼 반복문을 실행해라 
						Object key = (Object) en.nextElement(); //nextElement() = > Enumeration에 있는 요소를 꺼내오는 것 
						Object value = properties.get(key);
						System.out.printf("%s : %s\n", key, value);
						json.append("{");
						json.append(String.format(ptrn, "key", key)); //프로퍼티 한쌍 완성
						json.append(",");
						json.append(String.format(ptrn, "value", value));//프로퍼티 한쌍 완성
						json.append("}");
						json.append(",");
					}
					json.append("]");
					int lastIndex = json.lastIndexOf(",");
					if(lastIndex!=-1)
						json.deleteCharAt(lastIndex);
						
					}
			System.out.println(json);
			
			resp.setContentType("application/json;charset=UTF-8");
			try(
					
				//inputstream(짜잘짜잘하게 하나하나씩 보내기) = reader(뭉탱이로 보내기)
				//outputstream(짜잘짜잘하게 하나하나씩 보내기) = writer(뭉탱이로 보내기)
				PrintWriter out = resp.getWriter();	
				
			){
				out.print(json);	
			}

		}else {
			String path = "/WEB-INF/views/03/propsView.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
		
	}

	private Object retrieveData() {
		// TODO Auto-generated method stub
		return null;
	}
}
