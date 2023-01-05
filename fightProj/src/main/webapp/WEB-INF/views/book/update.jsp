<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<title>책 수정하기</title>
</head>
<body>
<h1>책 수정</h1>
<!-- 폼 태그. post 방식으로 /create URL을 톰켓서버에 요청 
URL : http://localhost/create?title=개똥이모험&category=소설&price=10000
-->
<form action="/update" method="Post">
<!-- 폼 데이터 -->
	<input type="text" name="bookId" value="${data.bookId}"/>
	<p>제목 : <input type="text" name="title" value="${data.title}"/>
	<p>카테고리 : <input type="text" name="category" value="${data.category}"/>
	<p>가격 : <input type="text" name="price" value="${data.price}" />
	<p>
		<input type="submit" value="저장" />
		<input type="button" value="목록" />
	</p>
</form>
</body>
</html>