<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
/*comment */
</style>


</head>
<body>

	<h4>JSP spec</h4>
	<pre>
	: 서블릿 스펙에서 파생된 하위스펙, 템플릿 기반의 스크립트 형태를 가진 스펙.
	
	JSP 소스 표준 구성요소
	1. 정적 텍스트 : 일반 텍스트, HTML, JAVASCRIPT, CSS
	2. backend script code 
		1) scriptlet(스크립틀릿) : 보통 제일 상단에 위치 		 
			<% //java code 지역코드 : 메소드 안에서 사용되는 코드이므로 메소드를 쓸수없음  
				String data = "데이터";
				Date now = new Date();
				//private void test(){}
			%>
		java문법 
		
 		2) directive: 보통 제일 상단에 위치 		  
 			<%--<%@ page import="java.util.Date" %>--%> <!-- 전처리 고문 : 부가설정  --> 
 			: JSP 페이지에 대한 부가설정이나 전처리 구분에 사용되며, 지시자의 이름과 속성들의 형태로 사용됨 
 			page(required) : 페이지에 대한 환경설정. 
 			include(optional): 정적 include
 			taglib(optional) : 커스텀 태그 로딩. <%-- ex.<c.foreach>... --%>
		
		3) expression(표현식) : <%=data %>, <%= now %> <!-- 표현식  -->
		값을 화면에 출력 
		
		4) declaraion : 보통 제일 상단에 위치 		    
		 <%! 
		 	//java code: 전역코드 : 메소드를 안에 넣을 수 있음 
		 	String variable; 
			private void test(){}
		 %>  
		
		선언문: 전역변수 + 전역메소드 정의	
			
		5) comment(주석) : <%--  --%>
			- client side comment : HTML, JS, CSS
			주석이 응답데이터에 포함됨 f12페이지 소스보기 누르면 보임 
			<!-- comment -->
			- servier side comment : java, jsp
			: 주석이 처리되는 시점이 서버에서임 f12 페이지 소스보기 눌러도 안보임 
			[서버사이드 주석을 써야하는 이유]
			1) 보안, 2) 네트워크에 보내는 용량때문에  
			<% //java commnet  %>
			<%-- jsp comment --%>
			
			
			
	 3. action tag
	 4. EL(표현언어)
	 5. JSTL
	 
			
			
	</pre>
	<script>
// 	comment
		console.log("body 랜더링 완료 ");
	</script>


</body>
</html>