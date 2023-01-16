<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록조회</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>회원목록조회</h4>


<table>
	<thead>
		<tr>
			<th>일련번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>이메일</th>
			<th>휴대폰</th>
			<th>거주지역</th>
			<th>마일리지</th>
			<th>구매건수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${pagingVO.dataList}" /> <!-- 페이징  -->
		<c:choose>
			<c:when test="${not empty memberList }"> <!-- memberList에 내용물이 비어있지 않다면  -->
				<c:forEach items="${memberList }" var="member">
					<tr>
						<td>${member.rnum}</td>
						<td>${member.memId }</td>
						<td>
							<c:url value="/member/memberView.do" var="viewURL"> <!-- 여기서 만들어진 url을 viewURL에다가 넣어서   -->
								<c:param name="who" value="${member.memId}"></c:param>
							</c:url>
							<a href="${viewURL}">${member.memName }</a>
						</td>
						<td>${member.memMail }</td>
						<td>${member.memHp }</td>
						<td>${member.memAdd1 } ${member.memAdd2 }</td>
						<td>${member.memMileage }</td>
						<td>${member.cartCount }</td>
					</tr>			
				</c:forEach>	
			</c:when>
			<c:otherwise> <!-- 아무도 가입한 회원 없을 경우  -->
				<tr>
					<td colspan="7">조건에 맞는 회원이 없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<%-- ${pagingVO.getPagingHTML } --%>
				${pagingVO.pagingHTML } 
				<!-- form id="searchForm"> -->
				<!-- <form id="searchForm">
					<input type="text" name="page" />
					<select name="searchType">
						<option value="">전체</option>
						<option value="name">이름</option>
						<option value="address">주소1</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="submit" value="검색" />
				</form> -->
				<div id="searchUI">
					<select name="searchType">
						<option value="">전체</option>
						<option value="name">이름</option>
						<option value="address">주소1</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="button" value="검색" id="searchBtn" />
				</div>
			</td>
		</tr>
	</tfoot>
</table>

<!-- 검색어만 입력하고 전송버튼 누르지 않은 상태에서 다음버튼 누르면 검색되는 오류 수정  -->
<h4>hidden form</h4>
<form id="searchForm">
	<!-- <input type="hidden" name="page" /> 첫번째 넘어가는 파라미터 
	<input type="hidden" name="searchType" /> 2번째 넘어가는 파라미터 
	<input type="hidden" name="searchWord" /> 3번째 넘어가는 파라미터  -->
	<input type="text" name="page" />  
	<input type="text" name="searchType" /> 
	<input type="text" name="searchWord" /> 
</form>
<%-- ${requestScope } --%>

<script>
<%--
//	$("[name=searchType]").val("${pagingVO.simpleCondition.searchType}"); //(모든데이터 다 가지고있는)pagingVO안에 있는 simpleCondition안에있는 searchType데이터 가져오기 
//	$("[name=searchWord]").val("${pagingVO.simpleCondition.searchWord}"); //(모든데이터 다 가지고있는)pagingVO안에 있는 simpleCondition안에있는 searchWord데이터 가져와서 input에 그대로 value값 유지시키기 
--%>	
	$("[name=searchType]").val("${searchVO.searchType}");
	$("[name=searchWord]").val("${searchVO.searchWord}");
	

	let searchForm = $("#searchForm");	/* 2페이지로 가면 대전으로 검색했는데 대구로 나오는 오류 수정(파라미터 못넘김 )  */
	
	let searchUI = $("#searchUI").on("click", "#searchBtn", function() { //<!-- 검색어만 입력하고 전송버튼 누르지 않은 상태에서 다음버튼 누르면 검색되는 오류 수정  -->
		let inputs = searchUI.find(":input[name]");
		//:input모든 입력태그 => select, input,.... 
		//input => input
		$.each(inputs,function(index, input) {
			let name = this.name; //this에서 이것의 name값을 꺼내옴
			let value = $(this).val();
			searchForm.find("[name=" + name + "]").val(value);
		});
		searchForm.submit();
	});
	
	$("a.paging").on("click", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		if(!page){ //page라는 데이터가 없다면 (private final String APATTERN = "<a href='#' class='paging' data-page='%d'>%s</a>";)
			return fals;
		}
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
		return false; 
	});

</script>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>