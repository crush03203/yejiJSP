<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	//_JSPService(request, response)
 	String numParam = request.getParameter("number");
 	if(numParam != null && !numParam.matches("\\d{1,2}")){
 		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
 		return;
 	}
 	// \\d              [0-9]
 	// *                0번 이상의 반복 
 	// +                1번 이상의 반복 
 	// {1,2}            한글자에서 두글자까지만 받겠다고 제한
 	//SC_BAD_REQUEST  : 400에러  
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>팩토리얼 연산 처리 </h4>


number = 10; 
<form>
	<input type="number" name="number" value="<%= Objects.toString(numParam, "") %>"  onchange="this.form.submit()" />
</form>

<%
if(numParam != null) {
	//jsp에서는 전역변수 될수있으면 쓰면안됨 
	int input = Integer.parseInt(numParam); 
	String pattern = "%d! = %d";
	int result = fact(input);
	String expr = String.format(pattern, input, result); //첫번째 파라미터로 input넘기고 두번째 파라미터로 result 넘긴다.
	
%>
<%=expr %>
<%
}
%>



<%! 
	//나의 외부에서 사용하겠다 static 
	// 해당 클래스부터 접근해야되는데 jsp에서는 무용지물 
	//public, staitc 전부 jsp 안쪽에서는 무용지물 
	private int fact(int n) {
		
		if(n<0)
			throw new IllegalArgumentException("음수는 연산 불가");
		if (n <= 1)
			return n;
		else 
			return fact(n-1) * n;
	}

%>





</body>
</html>