<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://www.ddit.or.kr/class305" prefix="ui" %>   
 
<h4>회원목록 조회</h4>
<table class="table table-bordered">
	<thead class="thead-dark">
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
		<c:set var="memberList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty memberList }">
				<c:forEach items="${memberList }" var="member">
					<tr>
						<td>${member.rnum }</td>
						<td>${member.memId }</td>
						<td>
							<c:url value="/member/memberView.do" var="viewURL">
								<c:param name="who" value="${member.memId }"/>
							</c:url>
							<a href="${viewURL }">${member.memName }</a>
						</td>
						<td>${member.memMail }</td>
						<td>${member.memHp }</td>
						<td>${member.memAdd1 }</td>
						<td>${member.memMileage }</td>
						<td>${member.cartCount }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8">조건에 맞는 회원이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				<ui:pagination pagingVO="${pagingVO }" type="bootstrap"/>
				<form:form id="searchUI" modelAttribute="searchVO" method="get" onclick="return false;">
					<form:select path="searchType">
						<option value>전체</option>
						<form:option value="name" label="이름" />
						<form:option value="address" label="주소1" />
					</form:select>
					<form:input path="searchWord" />
					<input type="button" id="searchBtn" value="검색" />
				</form:form>	
			</td>
		</tr>
	</tfoot>
</table>

<!-- <h4>Hidden Form</h4> -->
<form:form id="searchForm" modelAttribute="searchVO" >
	<input type="hidden" name="page" />
	<form:hidden path="searchType" />
	<form:hidden path="searchWord" />
</form:form>

<script type="text/javascript">
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



















