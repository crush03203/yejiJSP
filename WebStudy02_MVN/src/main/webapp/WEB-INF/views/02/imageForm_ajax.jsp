<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ImageStreamingFormServlet03  -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jpeg-red, png-green, gif-blue -->
<style>
.red {
	background-color: red;
}

.green {
	background-color: green;
}

.blue {
	background-color: blue
}
</style>



<script
	src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<form name="imgForm"
		action='<%=request.getContextPath()%>/imageStreaming.do'>
		<select name='image'>

		</select> <input type='submit' value='전송' />
	</form>
	<div id="imgArea"></div>

	<!-- 
		처리할 이벤트의 종류
		img태그를 동적으로 처리할 것 
	 -->


	<script>

	
	//select 태그 
		const DIVTAG = $("#imgArea");
		const SELECTTAG = $("[name='image']").on("change", function(event){
			let option = $(this).find("option:selected");
			let mime = option.attr("class");
			let clzName = matchedClass(mime);
			
			//$(this).removeClass(['red','green','blue']); //이조건에 해당하는 애들이 있으면 싹 클래스 지움 
			$(this).removeClass(colors);
			$(this).addClass(clzName);
			
			let srcURL = document.imgForm.action; 
			let queryString = $(document.imgForm).serialize();
			let src = "%U?%P".replace("%U", srcURL).replace("%P", queryString); //"%U"을 찾아서 srcURL로 변경, "%P"를 찾아서 queryString으로 변경 
			let img = $("<img>").attr("src", src);
			DIVTAG.html(img);
			<%-- $("#catImage").attr('src',"<%= request.getContextPath() %>/imageStreaming.do?image=" + $(this).val()); --%>
			
		}); 
		const changeCondition = {
			jpeg : "red",
			png : "green",
			gif: "blue"
		}
		
		const colors = [];
		/* $.each(changeCondition, function(index, element) {//changeCondition을 반복의 대상으로 삼았음 
			console.log(index + "," + element);
			//(index)jpeg,(element)red
		});   */
		 $.each(changeCondition, function(prop, propValue) {
			console.log(prop + "," + propValue);
			colors.push(propValue);
		});  
		
		//자바스크립트에서는 함수도 객체가 될수있다. 
		let matchedClass = function(mime) {
			let clzName = "";
			for(let prop in changeCondition) {
				//console.log(prop);
				let idx = mime.indexOf(prop) //png가 포함되어있는지, jpeg가 포함되어있는지 / 포함되어있으면 0보다 크거나 같다 
				if(idx >= 0) {//파일 찾았을 경우
					clzName = changeCondition[prop]; // changeCondition안에 key와 value값으로 들어있는 값 전체를 가져옴 
					break; //반복문 중단 
				}
			} 
			return clzName;
		}


		$.ajax({
			dataType : "json", //어떤 타입으로 가져올 것인지 json, html, ...
			success : function(resp) { //json의 언마샬링이 끝난 자바스크립트 객체 (resp = >배열 )
				console.log(resp.length);
				
				let options = [];
				$.each(resp, function(index, file) { //file=> 파일 한개의 정보 
					//$("<option>") : 새로운 option태그를 만들어라 
					//$("option") : 이미 존재하는 option태그 선택 
					let option = $("<option>")
								.addClass(file.mime)
								.html(file.name);
					options.push(option);

					
				});
				SELECTTAG.append(options);
				<c:if test="${not empty cookie['imageCookie']}">
					SELECTTAG.val("${cookie['imageCookie']['value']}"); 
				// 	쿠키가 있을 때 실행되어야함
					SELECTTAG.trigger('change');
				</c:if>
				
				
				
				// 1. select 이벤트
				// 2. 컨트롤러에서 마임타입 들고오기 
				// 3. 해당 마임타입별로 클래스 추가하기 
				/* $("select").on("change", function(){
					if($('select option:selected').attr('class') == "image/jpeg"){
						$(this).attr("class", "image/jpeg red")
					}else if($('select option:selected').attr('class') == 'image/png') {
						$(this).attr("class", "image/jpeg green")
					}else if($('select option:selected').attr('class') == 'image/gif'){
						$(this).attr("class", "image/gif blue")
					}else {
						
					}
				}); */
			
				
				/* for(var i = 0; i <= resp.length; i++) {
					$("select").append("<option>" + resp[i].name +  "</option>");
				} */
				
				
			
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}

		});
	</script>





</body>
</html>