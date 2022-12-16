<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> out : JSpWriter</h4>
<pre>
	출력 버퍼 관리자.
	현재 버퍼의 크기 : <%=out.getBufferSize()%>
	현재 버퍼의 잔여 크기 : <%= out.getRemaining() %> //왜 기본 보다 작을까?
	auto flush 지원 여부 : <%=out.isAutoFlush() %>
	<%
		for(int i = 1; i<=100; i++) {
			out.println(i+"번째 반복");
			if(out.getRemaining()<50)
				out.flush();
			if(i==100)
// 				throw new RuntimeException("강제 발생 예외");
// 				request.getRequestDispatcher("/02/standard.jsp").forward(request, response);
// 				request.getRequestDispatcher("/02/standard.jsp").include(request, response);
				pageContext.include("/02/standard.jsp", false); //false로 해야 안전?
		}
	%>
</pre>

</body>
</html>