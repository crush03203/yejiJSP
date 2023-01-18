<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="buyer.buyerId" /></th>
			<td>${buyer.buyerId}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerName" /></th>
			<td>${buyer.buyerName}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerLgu" /></th>
			<td>${buyer.lprodNm}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBank" /></th>
			<td>${buyer.buyerBank}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBankno" /></th>
			<td>${buyer.buyerBankno}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBankname" /></th>
			<td>${buyer.buyerBankname}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerZip" /></th>
			<td>${buyer.buyerZip}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerAdd1" /></th>
			<td>${buyer.buyerAdd1}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerAdd2" /></th>
			<td>${buyer.buyerAdd2}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerComtel" /></th>
			<td>${buyer.buyerComtel}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerFax" /></th>
			<td>${buyer.buyerFax}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerMail" /></th>
			<td>${buyer.buyerMail}</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerCharger" /></th>
			<td>${buyer.buyerCharger}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a class="btn btn-secondary" href="<c:url value='/buyer/buyerList.do'/>">목록으로</a>
				<c:url value="/buyer/buyerUpdate.do" var="updateURL">
					<c:param name="what" value="${buyer.buyerId }" />
				</c:url>
				<a href="${updateURL }" class="btn btn-primary">수정</a>
			</td>
		</tr>
		<tr>
			<th>거래물품</th>
			<td>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>상품아이디</th>
							<th>상품명</th>
							<th>구매가</th>
							<th>판매가</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="prodList" value="${buyer.prodList }" />
						<c:choose>
							<c:when test="${not empty prodList }">
								<c:forEach items="${prodList }" var="prod">
									<tr>
										<td>${prod.prodId }</td>
										<td>
											<c:url value="/prod/${prod.prodId }" var="prodViewURL" />
											<a href="${prodViewURL }">${prod.prodName }</a>
										</td>
										<td>${prod.prodCost }</td>
										<td>${prod.prodPrice }</td>
										<td>${prod.prodMileage }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="7"> 구매기록 없음. </td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>












