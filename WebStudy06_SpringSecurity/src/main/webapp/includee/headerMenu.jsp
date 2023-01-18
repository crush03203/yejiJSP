<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap w-100 p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="${pageContext.request.contextPath }">WebStudy04</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <ul class="nav px-3 col">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">자유게시판</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/member/memberList.do">회원관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/prod">상품관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/buyer/buyerList.do">거래처관리</a>
    </li>
  </ul>
  
  
  <ul class="nav px-3 col-2">
  <c:set var="principal" value="${pageContext.request.userPrincipal }"/>
  <c:choose>
	<c:when test="${not empty principal }">
		<li class="nav-item text-nowrap">
      		<a class="nav-link" href="<c:url value='/mypage.do'/>">MyPage</a>
   		</li>
		<li class="nav-item text-nowrap">
			<form id="logoutForm" action="<c:url value='/login/logout.do'/>" method="post"></form>
      		<a class="nav-link logoutBtn" href="#">Sign out</a>
   		</li>
		<script>
			$(".logoutBtn").on("click", function(event){
				event.preventDefault();
				$("#logoutForm").submit();
				return false;
			});
		</script>
	</c:when>
	<c:otherwise>
		 <li class="nav-item text-nowrap">
	       <a class="nav-link" href="${pageContext.request.contextPath }/login/loginForm.jsp">Sign in</a>
	     </li>
		 <li class="nav-item text-nowrap">
	       <a class="nav-link" href="<c:url value='/member/memberInsert.do'/>">Regist</a>
	     </li>
	</c:otherwise>
	</c:choose>
  </ul>
</nav>