<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestDesc.jsp</title>
</head>
<body>


<h4>request (HttpServletRequest)</h4>
<form method=""></form>
<pre>
	Http의 요청 패키징 방식
	:자원에 대한 식별  + 자원에 대한 행위정보를 기본으로 함
	
	1. Request Line : URI, http(request) Method
		request Method : 행위정보, 요청의 의도(목적)
		=> get, post 방식만 공통적으로 모든 서버들이 지원하고 나머지는  서버환경에 따라 달라짐 
		
		POST(C)
		GET(R)
		PUT/PATCH(U) - 보통 PUT안에 PATCH 까지 들어가있음
			PATCH : 일부내용 한개만 수정 
			PUT : 무조건 10개를 한꺼번에 수정
		DELETE(D)  
		HEAD : 응답데이터의 패키징 구조(LINE + HEADER)
		OPTIONS : "현재 서버가 특정 메소드를 지원하는지" 여부를 확인하기 위한 사전요청(preFlight request)에 사용
		TRACE: 서버 디버깅 용도로 제한적으로 사용  ('클라이언트'가 '서버'를 디버깅 하고싶을 때 사용) 
		
		ex) 
		[case1]
		/member/memberInsert.do

		[case2] Restful URI ( 자원식별과 행위를 분리하자 ) - JSON/XML로 자원표현
		/member GET  => select
		/member/a001 GET 
		/member/a001 PUT 
		/member/a001 DELETE
		/member POST => 회원가입, 한사람의 정보를 추가
		
		<%
			String requestURI = request.getRequestURI();
			StringBuffer requestURL = request.getRequestURL();
			String method =  request.getMethod();
		%>
		requestURI : <%= requestURI %>
		requestURL : <%= requestURL %>
		request method : <%= method %>
		
		
	2. Request Header : 클라이언트에 대한 부가정보(meta data)
	: 이름 - 값의 쌍으로 구성된 "문자" 데이터
	
	<%
		String userAgent = request.getHeader("user-Agent");
		out.println(userAgent);
	
	%>

	
	3. Request Body(optional) : POST, PUT
						: 클라이언트가 서버로 보내는 컨텐츠 영역(Content-Body, Message-Body)
						
	<%= request.getInputStream().available() %>
		
	
	
	
</pre>


<table border="1px">

	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	
	</thead>
	
	<tbody>
	
		<%

		Enumeration<String> em = request.getHeaderNames();
		while(em.hasMoreElements()) {
			String hd = em.nextElement();
		
		%>
		<tr>
			<td><%=hd %></td>
			<td><%= request.getHeader(hd)  %></td>
		</tr>
		
		<%
		}
		%>
	</tbody>


</table>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>
</html>