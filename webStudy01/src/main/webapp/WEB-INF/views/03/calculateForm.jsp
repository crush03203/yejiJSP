
<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사칙연산기 - background로 돌아가는</title>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/custom.js"></script>
</head>

<body>

<input type="radio" name="dataType" value="json" />JSON
<input type="radio" name="dataType" value="xml" />XML

<form method="post" id ="calForm">
	<input type="number" name="leftOp" placeholder="좌측피연산자">
	<select name="operator">
		<%
		OperatorType[] operators = OperatorType.values();
		for(OperatorType tmp : operators) {
			%>
			<option value="<%= tmp.name()%>"><%=tmp.getSign() %></option>
			<% 
		}
		%>
	</select>
	<input type="number" name="rightOp" placeholder="우측피연산자">
	<button type="submit" id="submit">=</button>
</form>

<div id="resultArea">

</div>
<script>
let resultArea = $("#resultArea");
$("#calForm").on("submit", function(event){
	event.preventDefault();
	let url = this.action;
	let method = this.method;
	let data = $(this).serializeObject();
	data.leftOp = parseInt(data.leftOp);
	data.rightOp = parseInt(data.rightOp);
	$.ajax({
		url : url,
		method : method,
		contentType : "application/json",
		data : JSON.stringify(data),
		dataType : "json",
		success : function(resp) {
			resultArea.html(resp.expression);
		},
		erro : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);

		}
	});
});

</script>




<script>
let resultArea = $('#resultArea');
let dataTypes = $("[name=dataType]");
let makeTrTag = function(name, value) {
	let tr = $("<tr>").append( //<tr></tr>만들기
					$("<td>").html(name), //<td>name</td>
					$("<td>").html(value) //<td>value</td>
			 );
	return tr;
	
}

let fn_sucesses = {
		
	json : function(resp) { //컨트롤러단에서 response 내용물들을 받아와서 
		let trTags = [];
		$.each(resp, function(name, value){ //resp 내용물이 끝날때까지 반복 
			trTags.push(makeTrTag(name, value)); //trTags 배열 안에 넣기 (resp안에 name과 value쌍인 내용물이 있겠지? 그걸 받아와서 각각 name과 value에 넣는다)
		});
		resultArea.empty();
		resultArea.append(trTags);

	},
	xml: function(comResp) { 
	}
	
}
 

let btn = $('#submit').on("click",function (event) {
	event.preventDefault();  //submit에 대한 기본 동작 없애기 
	
	let dataType = dataTypes.filter(":checked").val();
	$.ajax({
		   
	      dataType : dataType,
	      success : fn_sucesses[dataType],
	      error : function(jqXHR, status, error) {
	         console.log(jqXHR);
	         console.log(status);
	         console.log(error);
	      }  
	});
	
	
});


	

</script>







</body>
</html>






