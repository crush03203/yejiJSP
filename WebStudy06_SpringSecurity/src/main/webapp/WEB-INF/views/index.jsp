<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<h4>WELCOME</h4>
<security:authorize url="/mypage.do">
	<security:authentication property="principal" var="memberVOWrapper"/>
	<security:authentication property="principal.realMember" var="authMember"/>
<%-- 	${authMember } --%>
	<img src="data:image/*;base64,${authMember.base64MemImg }" style="width: 100px;height: 100px;"/> 
	<a href="<c:url value='/mypage.do'/>">${authMember.memName }[${authMember.memRole }]님</a>
	<form name="logoutForm" action="<c:url value='/login/logout.do'/>" method="post">
		<security:csrfInput/>
	</form>
	<a href="#" class="logoutBtn">로그아웃</a>
	<script>
		$(".logoutBtn").on("click", function(event){
			event.preventDefault();
			document.logoutForm.submit();
			return false;
		});
	</script>
</security:authorize>
<security:authorize url="/member/memberInsert.do">
	<a href="<c:url value='/login/loginForm.jsp'/>">로그인</a>
	<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
</security:authorize>