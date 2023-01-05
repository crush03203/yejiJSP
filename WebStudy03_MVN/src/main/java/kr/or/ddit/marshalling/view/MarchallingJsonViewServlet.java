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





@WebServlet("/jsonView.do")
public class MarchallingJsonViewServlet extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//		Object target =  req.getAttribute("target"); // 범용성이 없음 -> target이라는 이름으로만 전달받고 전달주겠다 // 위에 setAttribute내용을 가져와서 target에 넣어주() 

			//위 소스와는 달리 어떤 이름으로 받더라도 마샬링이 가능함 -> 범용성있음
			Enumeration<String>  names = req.getAttributeNames();
			//getAttribute :  하나값만 받아오는 것
			//getAttributeNames : 하나만 받아도 되는데 여러값 받아올수도 있음 =>Enumeration
			Map<String, Object> target = new HashMap<>();
			while (names.hasMoreElements()) { //name, value값을 받아와서 있는만큼 돌린다
				String name = (String) names.nextElement();
				Object value =  req.getAttribute(name);
				target.put(name, value);
			}

			
			
			
			
			
			ObjectMapper mapper = new ObjectMapper();
	//		1. marchalling
	//		String json = mapper.writeValueAsString(target); //마샬링
			resp.setContentType("application/json;charset=UTF-8");
	
			try(
				PrintWriter out = resp.getWriter();	//jsp로 보내주는 역할 
			){
	//			out.print(json);	 //직렬화 (for 컴퓨터가 기록하기위해서 )
				mapper.writeValue(out,target); //마샬링, 직렬화 한번에 쫙 해준거 
			}
	
	
	}
	
	
}