<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!--ImageStreamingFormServlet01  -->
<!--  ImageStreamingFormServlet03   -->

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		//application은 이미 우리가 호출할 팔요 없음 톰캣 톨면서 이미 
		//application = getServletContext(); //단 한번 생성되는 싱글톤 객체 
		String imageFolder = application.getInitParameter("imageFolder");

		//resp.setContentType("text/html;charset=UTF-8");
		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles((dir, name)-> { //이미지 파일들의 확장자들을 필터링 해서 배열 안에다 넣기 
			String mime = application.getMimeType(name); //그 이미지들의 확장자의 마임타입을 체크 
			return mime != null && mime.startsWith("image/"); 
		});

		//try width resource구문  : jsp에서는 이미 html로 쓰면 이미 쓰여지기 때문에 필요없음 
		/* try(
			//resp.getOutputStream();
			PrintWriter out = resp.getWriter();
		){
			out.println(content);
		} */

	%>


		

	<form action='<%=request.getContextPath()%>/imageStreaming.do'>
	<select name='image'> 
	<%
		String pattern = "<option>%s</option>\n";
		for(File tmp :imageFiles) {
	%>
	
		<%= String.format(pattern, tmp.getName()) %>
				
	<%
		}
	%>
	</select> 
	<input type='submit' value='전송' />
	</form>




   
</body>
</html>