<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>doctor-go</title>

<style>
	.tab-big{height: 340px;}
	.tab-mid{height: 215px;}
	.tab-bigger{height: 375px;} 
	
	.dblclick-on{
		background-color : antiquewhite;
	}
</style>

<div class="wrapper">
	<div class="grid-stack">
		<div class="grid-stack-item "
			gs-x="0" gs-y="0" gs-w="4" gs-h="2" gs-no-resize="true" gs-no-move="true">
			<div class="grid-stack-item-content card-grid" >
				<h4 class="h4-title2">그룹오더목록</h4>
				<div class="tabcontentWrap tab-mid">
			    	<div class="tabcontent">
						<table class="table-blue">
			      			<colgroup>
					      		<col style="width: 40%;" />
					      		<col style="width: 40%;" />
					      	</colgroup>
				      		<thead class="fixedHeader">
				      			<tr>
				      				<th>그룹오더코드</th>
									<th>그룹오더명</th>
								</tr>
				      		</thead>
				      		<tbody id="tbodyGoList">
				      			<c:forEach items="${goList }" var="go">
				      				<tr>
				      					<td>${go["goCd"] }</td>
				      					<td>${go["goKo"] }</td>
				      				</tr>
				      			</c:forEach>
				      		</tbody>
				      	</table>
					</div>
				</div>
			</div>
		</div>
		<div class="grid-stack-item" 
			gs-x="4" gs-y="0" gs-w="8" gs-h="5" gs-no-resize="true" gs-no-move="true">
			<div class="grid-stack-item-content card-grid" >
				<h4 class="h4-title2">그룹오더정보</h4>
				<div>
					<div>
						<button id="btnSave" class="btn btn-primary">저장</button>
						<button id="btnUpdate" class="btn btn-primary">수정</button>
						<button id="btnDelete" class="btn btn-primary">삭제</button>
					</div>
					<div>
						<div>그룹오더코드</div>
						<div><input name="goCd" type="text" class="form-control"></div>
						<div>한글명칭</div>
						<div><input name="goKr" type="text" class="form-control"></div>
					</div>
					<div>
						<label>상병목록</label>
						<table class="table-blue">
			      			<colgroup>
					      		<col style="width: 20%;" />
					      		<col style="width: 40%;" />
					      		<col style="width: 40%;" />
					      	</colgroup>
				      		<thead class="fixedHeader">
				      			<tr >
				      				<th>상병코드</th>
				      				<th>영문</th>
				      				<th>한글</th>
				      			</tr>
				      		</thead>
				      		<tbody id="goDise">
				      		</tbody>
				      	</table>
					</div>
					<div>
						<label>처방목록</label>
						<table class="table-blue">
							<colgroup>
					      		<col style="width: 18%;" />
					      		<col style="width: 42%;" />
					      		<col style="width: 9%;" />
					      		<col style="width: 16%;" />
					      		<col style="width: 16%;" />
					      	</colgroup>
				      		<thead class="fixedHeader">
				      			<tr>
				      				<th>약품코드</th>
									<th>약품명</th>
									<th>투여</th>
									<th>단위</th>
									<th>급여구분</th>
								</tr>
				      		</thead>
							<tbody id="goPre">
							</tbody>
						</table>
					</div>
					<div>
						<label>증상목록</label>
						<table class="table-blue">
							<colgroup>
					      		<col style="width: 15%;" />
					      		<col style="width: 40%;" />
					      	</colgroup>
				      		<thead class="fixedHeader">
				      			<tr>
				      				<th>증상코드</th>
									<th>증상</th>
								</tr>
				      		</thead>
							<tbody id="goSym">
							</tbody>
						</table>
					</div>
				</div>
				
			</div>	
		</div>		
		<div class="grid-stack-item" 
			gs-x="0" gs-y="3" gs-w="4" gs-h="3" gs-no-resize="true" gs-no-move="true">
			<div class="grid-stack-item-content card-grid" >
				<h4 class="h4-title2">기초자료</h4>
				 <div class="tab doc-basic">
				    <ul class="tabnav">
				      <li><a href="#dise">상병</a></li>
				      <li><a href="#medi">처방</a></li>
				      <li><a href="#sym">증상</a></li>
				    </ul>
				    <div class="tabcontentWrap tab-big">
					    <div class="tabcontent">
					      <div id="dise">
					      	<table class="table-blue">
						      	<colgroup>
						      		<col style="width: 15%;" />
						      		<col style="width: 25%;" />
						      		<col style="width: 50%;" />
						      	</colgroup>
					      		<thead class="fixedHeader">
					      			<tr>
					      				<th>상병코드</th>
					      				<th>영문</th>
					      				<th>한글</th>
					      			</tr>
					      		</thead>
					      		<tbody>
					      			<c:forEach items="${disList }" var="disease">
					      				<tr>
					      					<td>${disease["diseaseCd"] }</td>
					      					<td>${disease["enNm"] }</td>
					      					<td>${disease["korNm"] }</td>
					      				</tr>
					      			</c:forEach>
					      		</tbody>
					      	</table>
					      </div>
					      <div id="medi">
					      	<table class="table-blue">
				      			<colgroup>
						      		<col style="width: 18%;" />
						      		<col style="width: 42%;" />
						      		<col style="width: 9%;" />
						      		<col style="width: 16%;" />
						      		<col style="width: 16%;" />
						      	</colgroup>
					      		<thead class="fixedHeader">
					      			<tr>
					      				<th>약품코드</th>
										<th>약품명</th>
										<th>투여</th>
										<th>단위</th>
										<th>급여구분</th>
									</tr>
					      		</thead>
					      		<tbody id="tbody_medi">
					      		</tbody>
					      	</table>
					      </div>
					      <div id="sym">
					      	<table class="table-blue">
				      			<colgroup>
						      		<col style="width: 15%;" />
						      		<col style="width: 40%;" />
						      	</colgroup>
					      		<thead class="fixedHeader">
					      			<tr>
					      				<th>증상코드</th>
										<th>증상</th>
									</tr>
					      		</thead>
					      		<tbody >
					      			<c:forEach items="${symList }" var="symptom">
					      				<tr>
					      					<td>${symptom["symCd"] }</td>
					      					<td>${symptom["symDetail"] }</td>
					      				</tr>
					      			</c:forEach>
					      		</tbody>
					      	</table>
					      </div>

					      <div id="group">
					      	<table class="table-blue">
				      			<colgroup>
						      		<col style="width: 40%;" />
						      		<col style="width: 40%;" />
						      	</colgroup>
					      		<thead class="fixedHeader">
					      			<tr>
					      				<th>그룹오더코드</th>
										<th>그룹오더명</th>
									</tr>
					      		</thead>
					      		<tbody >
					      			<c:forEach items="${goList }" var="go">
					      				<tr>
					      					<td>${go["goCd"] }</td>
					      					<td>${go["goKo"] }</td>
					      				</tr>
					      			</c:forEach>
					      		</tbody>
					      	</table>
					      </div>
					    </div>
					</div>
				</div>		
			</div>	
		</div>	
		
	</div>
</div>

<!--  xml을 json으로 바꿔주는 라이브러리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/x2js/1.2.0/xml2json.min.js"></script>
<script>		
	
	/* make table */
	let $tbody_medi =$("#tbody_medi");
	let f_makeTable = function(v_sel, v_data){
		let trTags = [];
		for(let i=0;i<v_data.length;i++){
			let $tr = $("<tr>").append(
				$("<td>").html(v_data[i]["mdsCd"])	  
				,$("<td>").html(v_data[i]["itmNm"])
				,$("<td>").html(v_data[i]["injcPthNm"])
				,$("<td>").html(v_data[i]["unit"])
				,$("<td>").html(v_data[i]["payTpNm"])
			);
			$tr.data("object",v_data[i]);
			trTags.push($tr);
		}
		v_sel.append(trTags);
	}
	
	let f_medicineList = function(){
		let url = "https://apis.data.go.kr/B551182/dgamtCrtrInfoService/getDgamtList?ServiceKey=m3zTXtgIgwkhr2WboRC5nIwTq5fQN7q8T2ZteWypCKCrIdm2eM4fUcWZ5PKNFiONOv6Xmj%2FAYqckvEgAmB6oEA%3D%3D&numOfRows=30&pageNo=1&mdsCd=65"
		let xhr = new XMLHttpRequest();
		xhr.open("get", url, true);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				let x2js = new X2JS();
				let jsonObj = x2js.xml_str2json(xhr.responseText); // xml을 편하게 json으로 변환
	//				console.log(xhr.responseText);
	//				console.log(jsonObj.response.body.items.item); // 눈으로 확인!
				let data = jsonObj.response.body.items.item;
				
				f_makeTable($tbody_medi, data);
	//				console.log($medi.find("tr").eq(1).data("object"));
			}
		};
		xhr.send();
	}	
	f_medicineList();
	
	GridStack.init();
	
	let $basicContents = $(".doc-basic .tabcontent > div");	
	$('.tabcontent > div').hide();	
	let $basicNavs = $('.doc-basic li a').click(function () {
		$basicContents.hide().filter(this.hash).fadeIn();
		$basicNavs.removeClass('active');
		$(this).addClass('active');
		return false;
	});
	$basicNavs.eq(0).click();
	
	let tbody_goDise = $("#goDise").on('dblclick','tr', function(){
		$(this).remove();
	});
	let tbody_goPre = $("#goPre").on('dblclick','tr', function(){
		$(this).remove();
	});
	let tbody_goSym = $("#goSym").on('dblclick','tr', function(){
		$(this).remove();
	});

	// input result
	$('.doc-basic tbody').on('click', 'tr', function(event){
		// console.log($td.parent("tr"));
		// console.log($(this).parents().filter("div").attr("id"));
		let $tr = $(this);
		let v_id = $(this).parents().filter("div").attr("id");
		if(v_id == "dise"){
			tbody_goDise.append($tr.clone());
		} else if (v_id == "medi"){
			let newTr = $tr.clone(true);
			console.log(newTr.data("object"));
			tbody_goPre.append(newTr);
		} else if (v_id == "sym"){
			tbody_goSym.append($tr.clone());			
		} 
	});
	
	let $goCd = $("input[name=goCd]");
	let $goKr = $("input[name=goKr]");
	
	let f_getData = () => {
		let goObject = {
				goCd : $goCd.val()
				, goKo : $goKr.val()
		};		
		
		// dise
		let diseArr = [];
		$.each(tbody_goDise.find("tr"), function(index, tr){
			// console.log(index, tr);		
			let dise = {};
			dise.icd = $(tr).children().eq(0).html().trim();
			dise.icdEng = $(tr).children().eq(1).html().trim();
			dise.icdName = $(tr).children().eq(2).html().trim();
			
			diseArr.push(dise);
		});
		goObject.goDiseaseVOList = diseArr;
		
		// sym
		let symArr = [];
		$.each(tbody_goSym.find("tr"), function(index, tr){
			// console.log(index, tr);
			let sym = {};
			sym.symCd = $(tr).children().eq(0).html().trim();
			sym.symDetail = $(tr).children().eq(1).html().trim();
			symArr.push(sym);
		});
		goObject.goSymptomVOList = symArr;
		
		//pre
		let preArr = [];
		$.each(tbody_goPre.find("tr"), function(index, tr){
 			// console.log(index, tr, $(tr).data("object"));
 			let pre = {};
 			pre.preCd = $(tr).children().eq(0).html();
 			pre.preDetail = $(tr).children().eq(1).html();
 			pre.injcPthNm = $(tr).children().eq(2).html();
 			pre.unit = $(tr).children().eq(3).html();
 			pre.payTpNm = $(tr).children().eq(4).html();
 			
 			preArr.push(pre);
		});
		goObject.goPrescriptionVOList = preArr;
		
		console.log(goObject);
		
		return goObject;
	};
	
	let $btnSave = $("#btnSave").on('click',function(){				
		$.ajax({
			url : "${pageContext.request.contextPath}/doctor/grouporder",
			method : "post",
			data : JSON.stringify(f_getData()),
			contentType : "application/json;charset=UTF-8",
			dataType : "text",
			success : function(resp) {
				swal({title:resp, icon:"success"});
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});
	let $btnUpdate = $("#btnUpdate").on('click',function(){
		
		swal({
			title : "그룹오더 수정",
			text : "수정하시겠습니까?",
			icon : "info",
			buttons : {
				cancel : {
					text : "아니오"
					, value : false
					, visible : true
					, closeModal: true
				}
				, confirm : {
					text : "네"
					, value : true
					, visible : true
					, closeModal: true
				}
			}
		}).then((result) => {
			if (result){
				let goCd = $goCd.val();
				
				$.ajax({
					url : "${pageContext.request.contextPath}/doctor/goUpdate",
					method : "post",
					data : JSON.stringify(f_getData()),
					contentType : "application/json;charset=UTF-8",
					dataType : "text",
					success : function(resp) {
						swal({title:resp, icon:"success"});
					},
					error : function(jqXHR, status, error) {
						console.log(jqXHR);
						console.log(status);
						console.log(error);
					}
				});
			}
		});
	});
	let $btnDelete = $("#btnDelete").on('click',function(){
		
		swal({
			title : "그룹오더 삭제",
			text : "삭제하시겠습니까?",
			icon : "info",
			buttons : {
				cancel : {
					text : "아니오"
					, value : false
					, visible : true
					, closeModal: true
				}
				, confirm : {
					text : "네"
					, value : true
					, visible : true
					, closeModal: true
				}
			}
		}).then((result) => {
			if (result){
				let goCd = $goCd.val();
				
				$.ajax({
					url : "${pageContext.request.contextPath}/doctor/grouporder/"+goCd,
					method : "get",
					dataType : "text",
					success : function(resp) {
						swal({title:resp, icon:"success"});
					},
					error : function(jqXHR, status, error) {
						console.log(jqXHR);
						console.log(status);
						console.log(error);
					}
				});
			}
		});

	});
	
	let $tbodyGoList = $("#tbodyGoList").on('dblclick', 'tr', function(){
		let $tr = $(this);
		$tr.siblings('tr').removeClass("dblclick-on");		
		$tr.addClass("dblclick-on");
		
		tbody_goDise.empty();
		tbody_goPre.empty();
		tbody_goSym.empty();
		
		// console.log($tr.children().eq(0).html());
		let goCd = $tr.children().eq(0).html();
		
		$.ajax({
			url : "${pageContext.request.contextPath}/doctor/grouporder/"+goCd,
			method : "get",
			dataType : "json",
			success : function(resp) {
				console.log(resp);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});
	
</script>