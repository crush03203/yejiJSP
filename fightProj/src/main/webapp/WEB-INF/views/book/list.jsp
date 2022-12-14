<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- prefix="c" : 접두어-->
<!DOCTYPE html>
<html>
<head>
<title>책 등록하기</title>
</head>
<body>
	<!-- BookController.java의 mav.addObject("data",bookVOList); 코드 에서 온 것 -->

	<h1>책 목록보기</h1>
	<p>
	<!-- / : rootContext는 /WEB-INF/views
		uri : /list?keyword= 검색어
	 -->
	<form id="frm" name="frm" action="/list" method="get">
		<input type="text" name="keyword" id="keyword" value="" placeholder="검색어를 입력하세요" /> 
		<input type="submit" value="검색 " />
		<button type="submit"> 검색 </button>
	</form>
	</p>
	<table border="1">
		<thead>
			<tr>
				<th>제목</th>
				<th>카테고리</th>
				<th>가격</th>
			</tr>
		</thead>
		<tbody>
			<!-- 안에있는 <tr>태그가 반복되는 것 -->
			<!-- data: List<BookVO> -->
			<c:forEach var="bookVO" items="${data }">
				<tr>
					<td>${bookVO.title }</td>
					<td>${bookVO.category }</td>
					<td>${bookVO.price }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>