<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl</title>
</head>
<body>
<h4>Cache 제어</h4>
<pre>
	cache 란? 시스템 내부에서 발생하는 속도 저하를 커버하기 위한 임시 저장 데이터.
	Pragma(1.0), Cache-Control(1.1) : 캐싱 정책 설정용
		no-cache(O 저장은 하는데 검증을 거친다) : 캐시 데이터 사용전 확인 절차를 거치도록함.
		no-store(X 아예 저장하지 않는다)
		must-revalidate : 시한이 만료된 캐시 데이터 사용전 확인 절차를 거치도록함.
		public(캐싱해라) : 클라이언트, 프록시 서버에 캐싱 
		private(캐싱해라) : 클라이언트 캐싱
		ex) public;maxages=milliseonds (시간을 설정)
	Expires : 캐싱 데이터 만료 시한 설정용 (구체적인 날짜)
		<%
			response.setHeader("Pragma", "no-store"); //1.0
			response.setHeader("Cache-Control", "no-store"); //1.1
			response.addHeader("Pragma", "no-cache"); //1.0
			response.addHeader("Cache-Control", "no-cache"); //1.1
			response.setDateHeader("Expires", 0); //70-1-1, 0:0:0
		%>
	
</pre>
</body>
</html>