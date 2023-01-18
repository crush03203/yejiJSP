<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<table class="table table-bordered">
	<tr>
		<th><spring:message code="board.boTitle" /></th>
		<td>${board.boTitle}</td>
	</tr>
	<tr>
		<th><spring:message code="board.boWriter" /></th>
		<td>${board.boWriter}[${board.boMail}]</td>
	</tr>
	<c:if test="${not empty board.attatchList }">
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
					${attatch.attFilename } ${vs.last?"":"," }
				</c:forEach>
			</td>
		</tr>
	</c:if>
	<tr>
		<th><spring:message code="board.boDate" /></th>
		<td>${board.boDate}</td>
	</tr>
	<tr>
		<th><spring:message code="board.boIp" /></th>
		<td>${board.boIp}</td>
	</tr>
	<tr>
		<th><spring:message code="board.boHit" /></th>
		<td>${board.boHit}</td>
	</tr>
	<tr>
		<th><spring:message code="board.boContent" /></th>
		<td>${board.boContent}</td>
	</tr>
	<tr>
		<td colspan="2">	
			<c:url value="/board/boardUpdate.do" var="updateURL">
				<c:param name="what" value="${board.boNo }"/>
			</c:url>
			<a class="btn btn-primary" href="${updateURL }">수정</a>
			<a class="btn btn-danger" href="">삭제</a>
			<a class="btn btn-secondary" href="<c:url value='/board/boardList.do'/>">목록으로</a>
		</td>
	</tr>
</table>









