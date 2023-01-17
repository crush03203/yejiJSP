<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/memberList.do</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>거래처목록 조회</h4>

<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th><spring:message code="rnum"/></th>
			<th><spring:message code="buyer.buyerName"/></th>
			<th><spring:message code="buyer.buyerLgu"/></th>
			<th><spring:message code="buyer.buyerAdd1"/></th>
			<th><spring:message code="buyer.buyerComtel"/></th>
			<th><spring:message code="buyer.buyerMail"/></th>
			<th><spring:message code="buyer.prodCount"/></th>
		</tr>
	</thead>
	<tbody>
		<c:set var="buyerList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty buyerList }">
				<c:forEach items="${buyerList }" var="buyer">
					<tr>
						<td>${buyer.rnum }</td>
						<td>
							<c:url value="/buyer/buyerView.do" var="viewURL">
								<c:param name="what" value="${buyer.buyerId }"/>
							</c:url>
							<a href="${viewURL }">${buyer.buyerName }</a>
						</td>
						<td>${buyer.lprodNm }</td>
						<td>${buyer.buyerAdd1 }</td>
						<td>${buyer.buyerComtel }</td>
						<td>${buyer.buyerMail }</td>
						<td>${buyer.prodCount }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">조건에 맞는 거래처가 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				${pagingVO.pagingHTML }
				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="name">거래처명</option>
						<option value="address">소재지</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="button" id="searchBtn" value="검색" />
					<a href="<c:url value='/buyer/buyerInsert.do'/>" class="btn btn-primary">신규등록</a>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<h4>Hidden Form</h4>
<form id="searchForm">
	<input type="text" name="page" />
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
</form>
<script type="text/javascript">
	$("[name=searchType]").val("${simpleCondition.searchType}");
	$("[name=searchWord]").val("${simpleCondition.searchWord}");
	
	let searchForm = $("#searchForm");
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
		let inputs = searchUI.find(":input[name]");
		$.each(inputs, function(index, input){
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
	
	$("a.paging").on("click", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page) return false;
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
		return false;
	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>



















