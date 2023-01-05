<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  buffer="10kb"%>
    
 <!--  buffer="10kb  : 
 <jsp:include page="/includee/preScript.jsp" flush="false" /> 같은 인클루드 링크들 내용들이 많을 수도  있으니까 
 그걸 방지하기 위해서 버퍼를  충분히 준비 해둔다. 
 
 
  -->   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" flush="false" /> <!--preScript여기에 예외가 발생하게 되도 flush하지 말고 띄워라   -->
</head>
<body>
<div class="container">


	<%-- <jsp:include page='<%=(String)(request.getAttribute("contentPage")) %>'></jsp:include> --%>
	<jsp:include page='${contentPage}'></jsp:include>



이거 가장 기본으로 사용한데용
프레임워크(4개정도가 가장 기본)

파일즈

스프링

hibernate validator

마이바이티스



</div>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>