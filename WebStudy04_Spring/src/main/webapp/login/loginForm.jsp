<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>


<!-- 뒷단에서 날라온 " message"를  session에서 꺼내옴 => 뒷단에서 검증 통과 못할경우 message에   "아이디나 비밀번호 누락"이라는 내용이 채워짐 -->
<c:if test="${not empty message }"> <!-- 뒷단에서 받아온 message 값이 비어있지 않다면 검증이 실패했다는 증거니까 alert 창 실행    -->
	<script>
		alert("${message}");
	</script>
	
	<!--  위 내용 실행후 session은 삭제하는 작업 필요  -->
	<c:remove var="message" scope="session" /> <!-- flash Attribute 방식  : session/application은 안지워지므로 remove해줘야됨    -->
</c:if>


</head>




<body>

<!-- 
LoginProcessControllerServlet
MemberVO

index.jsp
LogoutControllerServlet
layout.jsp
IndexControllerServlet
 -->




<!--  파라미터에 아이디 비밀번호 노출시키지 않게 하기 위해서 post 방식   -->
<form method="post" action="<c:url value='/login/loginProcess.do'/>">
	<ul>
	
		<!--  param.memId  : 파라미터 중 memId 받아서 value값에넣기  -->
		<li>
			<%-- ${cookie['saveId'] } --%><!-- 뒷단에서 saveId의 쿠키 객체 받아옴  --> <!-- private Map<String, Cookie> cookieMap;  -->
			<c:set var="saveId" value="${cookie['saveId']['value'] }" /> <!--  value가 있으면  savedId 안에 value값 들어있음 없으면 공백 -->
			<input type="text" name="memId" placeholder="아이디" value="${not empty validId ? validId : saveId }"    />  <!-- validId이 있으면 validId   -->
			<input type="checkbox" name="saveId" ${not empty savedId ? "checked" : '' }   />아이디기억하기<!-- 체크했을 경우 saveId이 뒷단으로 넘어감 체크 안했을 땐 공백 넘어감   -->
			

			<!-- 
			문제
			최대 5일까지 아이디 기억하기 
			체크하지 않고 로그인했을 경우 기존에 저장되어있는 쿠키까지 삭제 할것
			 -->
			<c:remove var="validId" scope="session" />
		</li>
		<li>
			<input type="password" name="memPass" placeholder="비밀번호" />
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>








</body>
</html>