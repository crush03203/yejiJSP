<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<h4>거래처 폼</h4>

<form:form modelAttribute="buyer">
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="buyer.buyerId" /></th>
			<td>
				<form:hidden path="buyerId"  readonly="true"/>
				<form:errors path="buyerId" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerName" /></th>
			<td>
				<form:input path="buyerName" 
					cssClass="form-control" required="true" />
				<form:errors path="buyerName" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerLgu" /></th>
			<td>
				<form:select path="buyerLgu">
					<option value>분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
					</c:forEach>
				</form:select>
				<form:errors path="buyerLgu" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBank" /></th>
			<td>
				<form:input path="buyerBank" 
					cssClass="form-control" />
				<form:errors path="buyerBank" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBankno" /></th>
			<td>
				<form:input path="buyerBankno" 
					cssClass="form-control" />
				<form:errors path="buyerBankno" element="span"
					cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBankname" /></th>
			<td>
				<form:input path="buyerBankname" 
					cssClass="form-control" />
				<form:errors path="buyerBankname" element="span"
					cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerZip" /></th>
			<td>
				<form:input path="buyerZip" 
					cssClass="form-control" />
				<form:errors path="buyerZip" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerAdd1" /></th>
			<td>
				<form:input path="buyerAdd1" 
					cssClass="form-control" />
				<form:errors path="buyerAdd1" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerAdd2" /></th>
			<td>
				<form:input path="buyerAdd2" 
					cssClass="form-control" />
				<form:errors path="buyerAdd2" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerComtel" /></th>
			<td>
				<form:input path="buyerComtel" 
					cssClass="form-control" required="true" />
				<form:errors path="buyerComtel" element="span"
					cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerFax" /></th>
			<td>
				<form:input path="buyerFax" 
					cssClass="form-control" required="true" />
				<form:errors path="buyerFax" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerMail" /></th>
			<td>
				<form:input path="buyerMail" 
					cssClass="form-control" required="true" />
				<form:errors path="buyerMail" element="span" cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerCharger" /></th>
			<td>
				<form:input path="buyerCharger" 
					cssClass="form-control" />
				<form:errors path="buyerCharger" element="span"
					cssClass="text-danger" />
			</td>
		</tr>
		<tr>
			<td colspan="2"> 
				<input class="btn btn-success" type="submit" value="저장" />
				<input class="btn btn-danger" type="reset" value="취소" />
				<a class="btn btn-secondary" href="<c:url value='/buyer/buyerList.do'/>">거래처목록</a>
			</td>
		</tr>
	</table>
</form:form>	



















