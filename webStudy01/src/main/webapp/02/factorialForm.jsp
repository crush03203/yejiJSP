<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
  <!-- factorial servelet.java   --> 
   
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
	
	let resultArea = $('#resultArea'); //딱 한번 실행됨 submit메소드 안에 넣어놓으면 submit할때마다 계속 찾아서 부화걸림 
	
	$('form[name]').on('submit', function(event) {
		event.preventDefault(); 
		console.log("1 : " + this);//thisss가 잘못 쓰이면 아예 스크립트 자체가 안되니까 상단에 event.preventDefault();로 일단 막아준다 
		console.log("2 : " + $(this));
		console.log("3 : " + $(this)[0]); //form태그가 출력됨,  console.log(this)와 같은 형태 
		console.log("4 : " + $(this).get(0)); //form태그가 출력됨 , console.log(this)와 같은 형태 
		
		
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		//url : http://localhost/webStudy01/02/factorialForm.jsp
		console.log("url : " + url);
		//method : get
		console.log("method : " + method);
		//data : number=1
		console.log("data : " + data);
	
		$.ajax({
			url : url, 
			method : method, 
			data : data, 
			dataType : "html",
			success : function(resp) { //요청처리에 성공했을 경우 
				resultArea.html(resp);
			},
			error : function(jqXHR, status, error) {
				//console.log(jqXHR);
				//console.log(status);
				//console.log(error);
				//alert("실패");
			}

		});
		
		return false; //그 이벤트를 중단시켜라 (submit 안됨 )
	});
});
</script>

</head>
<body>

	<form name="facForm" action="<%=request.getContextPath()%>/02/factorial.do">
		<input type="number" name="number"  />
		<input type="submit" value="전송" />
		<input type="reset" value="취소" />
		<input type="button" value="걍버튼" />
	</form>
	
	<div id="resultArea">
			
	</div>


	
</body>
</html>