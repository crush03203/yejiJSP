<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/> 
<!-- 	메시지를 지우는데 스코프 방식으로 지워라 -->
</c:if>
</head>

<body>
	<form method="post" action="<c:url value='/login/loginProcess.do'/>">
		<ul>
			<li>
				<input type="text" name="memId" placeholder="아이디" value="${validId}"/>
				<input type="checkbox" name="saveId" />아이디 기억하기
<!-- 1. 얘를 체크한 상태에서 복원 기억시간은 최대 5일까지 아이디를 기억시키게하기 -->
<!-- 2. 체크박스를 체크하지 않고 로그인 한 경우 다음 로그인때 기존 쿠키까지 지워져야함 -->

				<c:remove var="validId" scope="session"/>
			</li>
			<li>
				<input type="password" name="memPass" placeholder="비밀번호"/>
				<input type="submit" value="로그인">
			</li>
		</ul>
	
	</form>
</body>
</html>