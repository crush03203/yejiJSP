<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>500 에러 페이지</h4>
<pre>
	<%
		ErrorData ed = pageContext.getErrorData();
		out.println(ed.getStatusCode());
		out.println(ed.getRequestURI());
		out.println(ed.getThrowable());
	%>
</pre>
</body>
</html>