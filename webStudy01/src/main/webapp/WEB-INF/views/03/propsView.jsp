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

<label>
	<input type="radio" name="dataType" value="json" checked />JSON
</label>
<label>
	<input type="radio" name="dataType" value="xml"  />XML
</label>


<button type="button" class="loadData">LOAD DATA</button>
<button type="button" class="clearData">CLEAR DATA</button>
<table border="1px">
	<thead>
		<tr>
			<th>Key</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
</table>


<script>

let listBody = $('#listBody');
let dataTypes = $("[name=dataType]");

let makeTrTag = function(name, value) {
	let tr = $("<tr>").append( //<tr></tr>만들기
					$("<td>").html(name), //<td>name</td>
					$("<td>").html(value) //<td>value</td>
			 );
	return tr;
	
}


let fn_sucesses = {
		
	json : function(resp) { //뒷단에서 response 내용물들을 받아와서 
		let trTags = [];
		$.each(resp.target, function(name, value){ //resp 내용물이 끝날때까지 반복 
			trTags.push(makeTrTag(name, value)); //trTags 배열 안에 넣기 (resp안에 name과 value쌍인 내용물이 있겠지? 그걸 받아와서 각각 name과 value에 넣는다)
		});
		listBody.empty();
		listBody.append(trTags);

	},
	xml: function(comResp) { 
		console.log(comResp)
		let root = $(comResp).find("target"); //xml 태그 제일 상단에 있는 <Properties></Properties>태그 찾기 
		let trTags = [];
		root.children().each(function(idx, child){  //<Properties>태그 자식들에 각각 다 적용됨 
			
			let name = child.tagName; //key값 
			let value = child.innerHTML; //내용물
			let tr = makeTrTag(name, value); //한건한건의 key와 value값들을 다 받아서
			trTags.push(tr); //trTags에 넣어주기 

		});
		listBody.empty();
		listBody.append(trTags);
	}
}



let btn = $(".loadData").on("click", function() {
	
	let dataType = dataTypes.filter(":checked").val();
	$.ajax({
		   
	      /* url -> 이 요청이 servlet에게 넘어간다는 말씀! */
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