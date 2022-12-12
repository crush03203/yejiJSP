<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body>


<!-- <img src="./resources/images/cat1.jpg" />
<img src="../resources/images/cat1.jpg" />
<img src="../../resources/images/cat1.jpg" />
 -->
<%-- <img src="<%= request.getContextPath() %>/resources/images/cat1.jpg" />--%>



<h4> properties 파일 뷰어 </h4>
<table border="1px">
	<thead>
		<tr>
			<th>Key</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody id="t_body">
	
	</tbody>
</table>

<!-- Properties Controller Servlet  -->
<script>
	$.ajax({
		dataType : "json", 
		success : function(resp) { 
			console.log(resp)
			var html = "";
			
			//resp속에 내용물 있는 거 전부 토해내기 
			$.each(resp,function(index, file){
				let list = resp[index];
				html += `
					<tr>
						<td>\${list.key}</td>
						<td>\${list.value}</td>
					</tr>
				`
			});
			
			
			console.log(html);
			var tBody = document.querySelector('#t_body');
			tBody.innerHTML =  html;
		},
		error : function(jqXHR, status, error) {

			
		}

	});



</script>


</body>
</html>