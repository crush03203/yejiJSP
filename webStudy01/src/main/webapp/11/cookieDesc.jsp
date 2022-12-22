<%@page import="java.net.URLEncoder"%>
<%@page import="kr.or.ddit.commons.wrapper.CookieHttpServletRequestWrapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/cookie</title>
</head>
<body>
	<h4>Cookie</h4>
	<pre>
	session(server) vs cookie(client)
		: http의 stateless 특성으로 인해 커뮤니케이션 정보가 유지되지 않는 경우, 사용하는 저장소의 개념.
		
	쿠키 사용 단계
	1. 쿠키 객체 생성(name, value)
	2. 응답(헤더, set-cookie)에 포함시켜 전송
<% 
		Cookie firstCookie = new Cookie("firstCookie", "firstValue");
		response.addCookie(firstCookie);
		String koreanValue = URLEncoder.encode("한글값","UTF-8");
		Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);
		response.addCookie(koreanCookie);
		
		//도메인의 레벨구조 
// 		Cookie domainCookie = new Cookie("doamineCookie", "domain cookie value");
// 		domainCookie.setDomain("localhost");
// 		response.addCookie(domainCookie);

		Cookie otherPathCookie = new Cookie("otherPathCookie","otherPathCookie");
		otherPathCookie.setPath(request.getContextPath()+"12");
		response.addCookie(otherPathCookie);
		
		Cookie longLiveCookie = new Cookie("longLiveCookie","longLive");
		longLiveCookie.setPath("/"); 
		longLiveCookie.setMaxAge(60*60*24*7); //구체적인 쿠키 만료 시점
		response.addCookie(longLiveCookie);
%>
	---------서버의 역할--------
	
	3. 브라우저가 자기 저장소에 저장
	4. 다음번 요청(헤더, cookie)을 통해 재전송
	---------브라워저 역할-------
	
	5. 요청에 포함된 쿠키를 통해 상태를 복원.
	<a href="viewCookie.jsp">동일 경로에서 쿠키확인</a>
	<a href="../12/viewCookie.jsp">다른 경로에서 쿠키확인</a>
	<%--
		String findedValue =  new CookieHttpServletRequestWrapper(request).getCookieValue("koreanCookie");
		out.println("쿠키값 : " + findedValue);
	--%>
 	** 쿠키 속성들
 	필수 속성
 		name : 식별자
 		value : String(타입), url encoded value
 	부가속성
 		domain(host) : 다음번 요청에 포함시켜 재전송할지 여부를 결정하는 조건.
 						ex) .naver.com, www.naver.com
 		path :  다음번 요청에 포함시켜 재전송할지 여부를 결정하는 조건.
 			path 설정이 명시되지 않은 경우, 쿠키 생성 경로가 반경됨. 
 		** maxAge : 쿠키의 만료 시한. 기본값 : 세션 만료 시한. 
 					ex) -1 : 브라우저가 꺼지면 쿠키를 바로 삭제해라 
 						 0 : 기존에 쿠키가 있다면 지워라(삭제해라) (name, value...등 모든 속성이 동일한 쿠키인 경우 삭제);
 		secure...
</pre>
</body>
</html>







