<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/sessionDesc.jsp</title>
</head>
<body>
<h4>session(HttpSession)</h4>
<h4 id ="timeArea"></h4>
<pre>
	(웹)세션이란?
		: 어플리케이션 서버를 사용하기 시작한 순간부터 사용 종료까지의 기한.
		
		시작(생성) : 클라이언트의 최초 요청(재전송되는 아이디가 없는 요청) 발생. -> 식별자가 부여된 세션이 새로 생성.
					-> 세션 아이디가 요청에 대한 응답이 전송될때 응답헤더에 포함되어 클라이언트로 전송.
			세션 아이디 : <%=session.getId() %>
			세션 생성 시점 : <%=new Date(session.getCreationTime()) %>
			마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
			timeout : <%=session.getMaxInactiveInterval() %>s
			
			유지(tracking mode) : 세션 식별자(세션 아이디) 재전송 구조.
			1) COOKIE
			2) URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">URL 트래킹모드</a>
			3) SSL(Secure Socket Layer)
		
		종료(만료)
			1) 세션의 아이디가 재전송되지 않을 때.  ex) 세션과 관련된 쿠키 삭제
			2) 브라우저가 종료될 때 
			3) session timeout 이내에 새로운 요청을 통해 아이디가 재전송되지 않을 때.
			4) session invalidation(명시적인 로그아웃)
</pre>
<script type="text/javascript">
	$("#timeArea").sessionTimer(120);
</script>
</body>
</html>