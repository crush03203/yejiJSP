<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<h4>WELCOME</h4>

<h4>
${pageContext.request.userPrincipal}
</h4>


<security:authorize url="/mypage">

	<security:authentication property="principal" var="authMember"/>
	<a href="${pageContext.request.contextPath }/mypage">${authMember.realMember.memName }</a>
	<form name="logoutForm" method="post" action="${pageContext.request.contextPath }/login/logout">
		<security:csrfInput/>
	</form>
	<a href="#" onclick="document.logoutForm.submit();">로그아웃</a>
</security:authorize>
<security:authorize access="isAnonymous()">
	<a href="${pageContext.request.contextPath }/login/loginForm">로그인</a>
</security:authorize>
