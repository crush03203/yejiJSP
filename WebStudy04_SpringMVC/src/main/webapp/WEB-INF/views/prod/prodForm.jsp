<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>


<form:form modelAttribute="prod" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th><spring:message code="prod.prodId" /></th>
			<td>
				<form:input path="prodId"  cssClass="form-control"  />
				<form:errors path="prodId" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodName" /></th>
			<td>
				<form:input path="prodName"  cssClass="form-control"  />
				<form:errors path="prodName" element="span" cssClass="text-danger" />
			</td>
		</tr>
			<tr>
			<th><spring:message code="prod.prodLgu" /></th>
			<td>
				<form:select path="prodLgu">
					<option value>분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
					</c:forEach>
				</form:select>
				<span class="text-danger">${errors.prodLgu}</span>
				<form:errors path="prodLgu" element="span" cssClass="text-danger"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodBuyer" /></th>
			<td>
				<form:select path="prodBuyer">
					<option value>거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<form:option 
							value="${buyer.buyerId }" 	
							label="${buyer.buyerName }"
							cssClass="${buyer.buyerLgu }" 
						/>
					</c:forEach>
				</form:select>
				<form:errors path="prodBuyer" element="span" cssClass="text-danger"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodCost" /></th>
			<td>
				<form:input path="prodCost" type="number" cssClass="form-control"  />
				<form:errors path="prodCost" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodPrice" /></th>
			<td>
				<form:input path="prodPrice" type="number" cssClass="form-control"  />
				<form:errors path="prodPrice" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodSale" /></th>
			<td>
				<form:input path="prodSale" type="number" cssClass="form-control"  />
				<form:errors path="prodSale" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodOutline" /></th>
			<td>
				<form:input path="prodOutline"  cssClass="form-control"  />
				<form:errors path="prodOutline" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodDetail" /></th>
			<td>
				<form:input path="prodDetail"  cssClass="form-control" />
				<form:errors path="prodDetail" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodImage" /></th>
			<td>
				<input type="file" name="prodImage" class="form-control"/>
				<form:errors path="prodImage" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodTotalstock" /></th>
			<td>
				<form:input path="prodTotalstock" type="number" cssClass="form-control"  />
				<form:errors path="prodTotalstock" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodInsdate" /></th>
			<td>
				<form:input path="prodInsdate" type="date" cssClass="form-control" />
				<form:errors path="prodInsdate" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodProperstock" /></th>
			<td>
				<form:input path="prodProperstock" type="number" cssClass="form-control"  />
				<form:errors path="prodProperstock" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodSize" /></th>
			<td>
				<form:input path="prodSize"  cssClass="form-control" />
				<form:errors path="prodSize" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodColor" /></th>
			<td>
				<form:input path="prodColor"  cssClass="form-control" />
				<form:errors path="prodColor" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodDelivery" /></th>
			<td>
				<form:input path="prodDelivery"  cssClass="form-control" />
				<form:errors path="prodDelivery" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodUnit" /></th>
			<td>
				<form:input path="prodUnit"  cssClass="form-control" />
				<form:errors path="prodUnit" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodQtyin" /></th>
			<td>
				<form:input path="prodQtyin" type="number" cssClass="form-control" />
				<form:errors path="prodQtyin" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodQtysale" /></th>
			<td>
				<form:input path="prodQtysale" type="number" cssClass="form-control" />
				<form:errors path="prodQtysale" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodMileage" /></th>
			<td>
				<form:input path="prodMileage" type="number" cssClass="form-control" />
				<form:errors path="prodMileage" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" /> 
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
</form:form>	
<script>
let prodBuyerTag = $("[name=prodBuyer]");
$("[name=prodLgu]").on("change", function(){
	let prodLgu = $(this).val();
	if(prodLgu){
		prodBuyerTag.find("option:gt(0)").hide();
		prodBuyerTag.find("option."+prodLgu).show();
	}
}).trigger("change");
</script>
<jsp:include page="/includee/postScript.jsp" />

</body>
</html>














