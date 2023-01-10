<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	DB에 저장한 데이터 
	textPart : ${textPart }
	numPart : ${numPart }
	datePart : ${datePart }
	file metadata : ${fileMetadata }

	<!-- 일단 저장되어있는  session을 일단 쫙 clear  -->
	<c:remove var="textPart"/>
	<c:remove var="numPart"/>
	<c:remove var="datePart"/>
</pre>


<c:forEach items="${fileMetadata }" var="md"> <!--md :  여기에 한건의 메타데이터 들어있음   -->
	<img src="<c:url value='${md }' />" /> <br/>
</c:forEach>
<c:remove var="fileMetadata"/>
	
<!--  이미지가 아닌 놈은 저장안돼야함  이미지가 출력되어야함  -->	
	
	
	
<form method="post" enctype="multipart/form-data" 
				action="${pageContext.request.contextPath }/file/upload.do">
	<ul>
		<li>
			문자데이터: <input type="text" name="textPart" />
		</li>
		<li>
			숫자(?)데이터: <input type="number" name="numPart" />
		</li>
		<li>
			날짜(?)데이터: <input type="date" name="datePart" />
		</li>
		
		
		<li>
			파일 데이터: <input type="file" name="filePart1" accept="image/*" /><!--accept="image/*"=> 이미지 파일만 가능  -->
		</li>	
		<li>
			파일 데이터: <input type="file" name="filePart2" />
		</li>	
		
		<li>
			<input type="submit" value="업로드" />
		</li>	
	
	</ul>
</form>




</body>
</html>