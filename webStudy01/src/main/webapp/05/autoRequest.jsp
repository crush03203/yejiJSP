<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> -->
<!-- 5초뒤에 네이버로 이동 됨  -->
<title>05/autoRequest</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<h4>Refresh 헤더 활용</h4>
	<%--
	// Refresh 동기요청 구조 /화면 전체에 lock을 걸어서 클라이언트의 상태를 유지해줄 수 없다
	response.setIntHeader("Refresh", 1);
	그러나 <meta http-equiv="Refresh" content="1">이걸 넣어주면  달라짐
--%>
	<pre>
	서버의 갱신 데이터 확보(동기 요청 구조)
	1. Refresh response header
	2. meta tag
	3. reload
	</pre>
	
	<h4>
		현재 서버의 시간 : <span id="timeArea"></span>
	</h4>
	
<!-- 	<input type="" placeholder="기록필드" /> -->
<button class = "controlBtn" data-control-type="START">시작</button>
<button class = "controlBtn" data-control-type="STOP">멈춤</button>
<input type="radio" name="dataType" data-data-type="json"/>JSON
<input type="radio" name="dataType" data-data-type="text"/>PLAIN
<input type="radio" name = "Locale" value="ko" checked/>한국어
<input type="radio" name = "Locale" value="en" checked/>영어
<input type="radio" name = "Locale" value="jp" checked/>일어
	<script>
		// 	setTimeout(() => {
		// 		location.reload();
		// 	}, 1000);
		let timeArea = $("#timeArea");
		let sendRequest = function() {

		}
		$.ajax({
			url : "${pageContext.request.contextPath}/05/getServerTime",
			dataType : "json",
			success : function(resp) {
				timeArea.html(resp.now)
			},
			erro : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);

			}
		});

		setInterval(sendRequset, 100);
	</script>
</body>
</html>