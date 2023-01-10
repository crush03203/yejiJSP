<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/resources/js/custom.js"></script>




</head>
<body>
serializeObject.js
factorialForm.jsp
MemoControllerServlet
<h4>Restful 기반의 메모관리</h4>


<!--

form : 동기요청을 발생시킨다. 
submit를 통해서 

  -->
<form name="memoForm" action="${pageContext.request.contextPath}/memo" method="post">
	<input type="text" name="writer" placeholder="작성자" />
	<!-- <input type="text" name="writer" placeholder="작성자" /> --> <!-- 작성자 태그가 두개일 경우 custom.js line25줄 참고   -->
	<input type="date" name="date" placeholder="작성일" />
	<textarea name="content"></textarea>
	<input type="submit" value="등록" />
</form>
<br><br>

<table class="table-bordered">
	<thead>
		<tr>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
</table>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


<script>

/*
일반객체 : let memoForm = this;
jquery객체 :let memoForm = $(document.memoForm).on("submit",function(event) {/
*/

//	$('[name="memoForm"]')
	let memoForm = $(document.memoForm).on("submit",function(event) {//document.memoForm : document에 있는 memoForm 이라는 name을 가지고 있는 태그 찾기 <form name="memoForm">
		event.preventDefault();	
		//this== event.target 
		//$(this) == $(document.memoForm)
		let url = this.action; //this에서 action을 꺼내온다.
		let method = this.method;
		
		//1. 직렬화해서 파라미터로 보내는 방식
//		let data = $(this).serialize(); //writer=작성자&date=작성일&content=내용(=> QueryString)
//		let memoForm = this; //일반 객체 


		//2. 제리쿼리 객체를 이용해서 json으로 보내는 방식 
		let data = $(this).serializeObject(); //custom.js에서 만들어준 function들고옴 
		//serialize를 사용하지 않고 json으로 (비동기로) 파라미터 넘길때 사용하는 방식  
/* 		{
			writer: "", //form.name : "form.value"
			date : "",
			content: ""
		} */
		
		
		
		$.ajax({
			url : url,
			method : method,
			contentType: "application/json;charset=UTF-8", //request의 content-type을 결정하기 (보내는 편지의 type을 결정하기 )
/* 			data : { //뒷단으로 넘어갈땐 파라미터 형태로 넘어가게됨 => 직렬화 과정을 거치게 됨 
				writer: "",
				date : "",
				content: ""
			},  */
			//data : data, //직렬화방식으로 넘길경우는 이렇게만 해줘도 되지만 
			data : JSON.stringify(data), //직렬화 말고 제이쿼리 객체를 통해서 뒷단으로 넘길땐  data를  마샬링 해줘야됨 ㄴ 
			dataType : "json", //request Accept , response content-type
			success : function(resp) { //요청처리에 성공했을 경우 
				makeListBody(resp.memoList)
				//this //=> ajax 그 자체
				
				//등록 후 input 박스 내용 clear시키기 
				//memoForm.reset(); //(일반 객체 )let memoForm = this;으로 사용했을 경우    
				memoForm[0].reset(); //(제이쿼리 객체)let memoForm = $(document.memoForm).on("submit",function(event) 으로 사용했을 경우 
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}

		});
		return false; // submit 동작 금지 
		 
	});  


	
	
	
	
	

	//EDD(Evetn Driven Development)(O) vs TDD(Test Driven Development)

	//show.bs.modal : 버튼 띄워질때 적용 	 / shown.bs.modal:다 띄워지고 난 후에 적용
	$("#exampleModal").on("show.bs.modal", function(event) { //event => show라는 이벤트
		//this == event.target : modal창 그 자체
		let memo = $(event.relatedTarget).data("memo") // 모달을 띄울때 사용했던 엘리먼트 (modal을 오픈할 때 사용한 클릭대상 : tr버튼태그)
		//.data("memo")  밑에서 저장해놨던 .data("memo", memo)의 data의 키 값을 가져와서 내용물 들고옴

		$(this).find(".modal-body").html(memo.content);

	}).on("hidden.bs.modal", function(event) {//event => hidden라는 이벤트 (hidden 이벤트 안하면 모달 닫히고서도 계속  모달 내용물이 남아있는데 hidden 이벤트를 통해서  empty해줌으로써 모달 내용물 확 지워버릴수있다. )
		$(event.target).find(".modal-body").empty(); //event.target == this
	});




let listBody = $("#listBody");
let makeListBody = function(memoList1) { // makeListBody(resp.memoList);
	listBody.empty();
	let trTags = [];
	if(makeListBody.length>0) { //메모가 한 건 이상 있으면 
		
		
/* 		<tr data-bs-toggle="modal" data-bs-target="#exampleModal">
			<td>memo.writer</td>
			<td>this.date</td>
		</tr> */
		$.each(memoList1, function(index, memo) { //index, memo : 인덱스와 한 건의 메모 가져오기 
			let tr = $("<tr>").append(
				$("<td>").html(memo.writer),		
				$("<td>").html(this.date)// this.date = memo.date
			).attr({ //<tr data-bs-toggle="modal" data-bs-target="#exampleModal">
				/* data-bs-toggle="modal" data-bs-target="#exampleModal" a링크 굳이 만들 필요 없음 */ 
				"data-bs-toggle" : "modal",
				"data-bs-target" : "#exampleModal"
			}).data("memo", memo);//데이터의 원래의 타입을 유지시켜줄수 있음 memo전체의 내용을 data타입으로 저장해둠 (memo라는 키를 가지고)
			trTags.push(tr);
		});
		
	}else {
		let tr = $("<tr>").html(
			$("<td>")
				.attr("colspan","2")
				.html("작성된 메모 없음")
		);
		trTags.push(tr);
	}
	listBody.append(trTags); //여러개 tr태그 넣어주기 
}

$.ajax({
	url : "${pageContext.request.contextPath}/memo", 
	method : "get", //put(update): 1번 메모("${pageContext.request.contextPath}/memo?idx=1)를 수정하겠다, post: 메모를 작성하겠다 , get: 메모를 가져오겠다 , delete: 1번 메모를 삭제하겠다    
	dataType : "json", 
	success : function(resp) { 
		console.log(resp.memoList[0].writer);
		makeListBody(resp.memoList);
	},
	error : function(jqXHR, status, error) {
		console.log(jqXHR);
		console.log(status);
		console.log(error);
	}
});







</script>



<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>