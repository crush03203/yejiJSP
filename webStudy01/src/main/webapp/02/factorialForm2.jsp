<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script>
/* 
$(document).on("ready", function() {	});
$(function() {});
 */
$(document).ready(function() {
	$('form[name]').on('submit', function(event) {
		event.preventDefault(); 
		console.log(this);//thisss가 잘못 쓰이면 아예 스크립트 자체가 안되니까 상단에 event.preventDefault();로 일단 막아준다 
		console.log($(this));
		console.log($(this)[0]); //form태그가 출력됨,  console.log(this)와 같은 형태 
		console.log($(this).get(0)); //form태그가 출력됨 , console.log(this)와 같은 형태 
		
		
		$.ajax({
			url : "", //어디로 넘길것인지 
			method : "", //어떤방식으로 넘길것인지 
			data : {}, //어떤 파라미터 값을 넘길것인지 

			dataType : "", //어떤 타입으로 가져올 것인지 json, html, ...
			success : function(resp) { //요청처리에 성공했을 경우 

			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}

		});
		
		return false; //그 이벤트를 중단시켜라 (submit 안됨 )
	});
});
</script>

</head>
<body>

	<form name="facForm" action="<%=request.getContextPath() %>/02/factorial.do">
		<input type="number" name="number" />
		<input type="submit" value="전송" />
		<input type="reset" value="취소" />
		<input type="button" value="걍버튼" />
	</form>
	
	<div id="resultArea">
			
	</div>


	
</body>
</html>