package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//범용적으로 쓸수 있는 서블릿 
//응답메시지의 "형태"는 tmpl에서 결정하고, "실제 내용"은 서블릿에서 실행됨 
//@WebServlet("/01/sample.tmpl")
@WebServlet("*.tmpl") //tmpl확장자를 가지는 모든 파일들은 해당 url로 출력됨 
public class TmplServlet extends HttpServlet {
	
	private ServletContext application; //싱글톤 딱 한번만 잡으면 됨 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		
		//이거 한줄만 남기고 아래에 따로 메소드 추가해서 실행  private StringBuffer readTemplate
		StringBuffer tmplSrc = readTemplate(req, resp); //tmplSrc안에는 #now#도 들어있음 
		if(tmplSrc == null) {//tmplSrc이 null이면 에러메시지 출력 
			System.err.println("템플릿소스를 읽을 수 없음");
			return;
		}
//		Map<String, Object> scope = new HashMap<>();
//		scope.put("now", new Date());
		
		
		//아래 메소드 추가해서 실행 private String evaluateVariables
		String html = evaluateVariables(tmplSrc, req); //치환작업 

		PrintWriter out = resp.getWriter();
		out.println(html);
	
	}


	
	//#now# 작업 
	private String evaluateVariables(StringBuffer tmplSrc, HttpServletRequest scope) {	
		//String evalPattern  = "#now#"; 
		String evalPattern  = "#([a-zA-Z0-9_]+)#";//정규표현식 사용 대소문자구별않고+ 숫자 포함하여+언더바까지 사용하여+ 한문자이상 => (그룹인덱스한개)
		Pattern regex =  Pattern.compile(evalPattern); //evalPattern정규식을 pattern(자바에서 정규식을 다루는 라이브러리)에 넘겨줌 
		Matcher matcher = regex.matcher(tmplSrc);
		StringBuffer finalHtml = new StringBuffer();
		while(matcher.find()){
			String varName = matcher.group(1); //그룹인덱스1 = ([a-zA-Z0-9_]+) => varName이 now라는 내용을 가지게 됨 
			Object value = scope.getAttribute(varName); //앞으로 치환해야할 데이터 
			//matcher.appendReplacement(finalHtml, value.toString()); //치환할 값을 찾았을 경우 finalHtml를 value.toString()의 값으로 바꿔라 
			matcher.appendReplacement(finalHtml, Objects.toString(value, "")); //#now2#일 경우 null포인트익셉션으로 잡히게 되는데 이렇게 ""로 지정해 줄 경우 널 대신에 공백으로 교체됨 
		}
		
		matcher.appendTail(finalHtml); // #now# 뒤부분 남은 소스들 = > 치환하고 남아있는 데이터를 여기에 넣어줌 
		return finalHtml.toString();
	}


	private StringBuffer readTemplate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tmplPath = req.getServletPath();
		String realPath = application.getRealPath(tmplPath); //논리주소(@WebServlet("/01/sample.tmpl")를 넘기면 진짜 주소(톰캣의 위치D:\A_TeachingMaterial\6.JSP_Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\webStudy01\01)를 받을수 있음 
		File tmplFile = new File(realPath);
		if(!tmplFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, tmplPath+"를 찾을 수 없음");
			return null;
		}
		
		FileReader reader = new FileReader(tmplFile); //입력 스트림 하나가 개방된 것임 한번에 한문자밖에 못 읽어들임
		BufferedReader br = new BufferedReader(reader); //한번에 한줄 전체를 읽어들일수 있음 
		
		String temp = null; 
		StringBuffer tmplSrc = new StringBuffer(); //소스 읽기 =>     읽어들인 것들을 stringbuffered에다가 저장해놓음 
		while((temp=br.readLine()) != null) { //읽어들이는 한줄을 temp에 쌓ㅇ놓을 수 있음 
			tmplSrc.append(temp+"\n");
		}
		
		return tmplSrc; //tmplSrc 반환 
			
	}
	
	
	
	
	
}
