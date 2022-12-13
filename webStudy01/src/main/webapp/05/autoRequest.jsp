<%@page import="java.util.Locale"%>
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
<style type="text/css">
.disabled {
	display: none;
}
</style>
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
	<button class="controlBtn" data-control-type="START">시작</button>
	<button class="controlBtn disabled" data-control-type="STOP">멈춤</button>
	<input type="radio" name="dataType" data-data-type="json" />JSON
	<input type="radio" name="dataType" data-data-type="text" />PLAIN
	<input type="radio" name="Locale" value="<%=Locale.KOREAN.toLanguageTag() %>" checked />한국어
	<input type="radio" name="Locale" value="<%=Locale.ENGLISH.toLanguageTag() %>" checked />영어
	<input type="radio" name="Locale" value="<%=Locale.JAPANESE.toLanguageTag() %>" checked />일어
	<script>
		// 	setTimeout(() => {
		// 		location.reload();
		// 	}, 1000);

		let timeArea = $("#timeArea");
		let dataTypes = $('[name="dataType"]');
		let locales = $('[name="Locale"]');
		let successes = {
			json : function(resp) {
				timeArea.html(resp.now)
			},
			text : function(plain) {
				timeArea.html(plain)
			},
		}
		let sendRequest = function() {
			//2단계 : dataType 라디오 버튼의 선택 조건에 따라 비동기 요청 헤더(Accept)설정
			// 			--> dataType에 따라 success함수의 형태가 달라짐.

			let dataType = dataTypes.filter(":checked").data('dataType');
			if(!dataType) {
				dataType = "json";
				dataTypes.filter("[data-data-type=json]").prop("checked",true);
			}
			
			//3단계 : locale 라디오버튼의 선택 값에 따라 비동기 요청의 locale 파라미터 값 결정됨.
			let locale = locales.filter(":checked").val();
			let data = {}
			if(locale) {
				data.locale=locale 
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/05/getServerTime",
				data : data,
				dataType : dataType,
				success : successes[dataType],
				erro : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
		}

		// 		1단계 : 컨트롤 버튼에 대한 클릭이벤트 처리
		let controlBtns = $(".controlBtn").on("click", function() {
			// 			$(this).prop("disabled",true);
			controlBtns.toggleClass("disabled");
			let controlType = $(this).data("controlType");
			if (controlType == "START") {
				// 컨트롤버튼 타입 START면
				// 시계 작동
				let jobId = setInterval(sendRequest, 1000);
				timeArea.data("jobId", jobId);

			} else {
				//컨트롤버튼 타입 STOP면
				// 시계 멈춤
				let jobId = timeArea.data('jobId');
				if (jobId) {
					clearInterval(jobId);
					timeArea.data("jobId", null);
				}
			}
		});

	
		
	</script>
</body>
</html>