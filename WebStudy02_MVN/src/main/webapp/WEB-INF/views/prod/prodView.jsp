<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>상품 상세 조회</h4>
<table>

 
<!--  상품 상세 조회시, 해당 거래처의 모든 정보도 함께 조회함.   -->
<!--  * 상품 상세 조회시, 구매 리스트(회원아이디, 회원명, 휴대폰, 이메일, 마일리지) 함께 조회함. -->
 
<!-- MEMBER 회원 정보 (아이디, 회원명, 휴대폰, 이메일, 마일리지) 조회 -->
<!-- LPROD  -->
<!-- PROD -->
<!-- CART -->
<!-- BUYER 거래처 정보 모두 조회 -->

      <tr>
         <th>상품아이디</th>
         <td>${prod.prodId}</td>
      </tr>
      <tr>
         <th>상품명</th>
         <td>${prod.prodName}</td>
      </tr>
      <tr>
         <th>상품분류</th>
         <td>${prod.lprodNm}</td>
      </tr>
      <tr>
         <th>거래처</th>
         <td>
            <table>
               <thead>
                  <tr>
                     <th>거래처명</th>
                     <th>담당자명</th>
                     <th>지역</th>
                  </tr>
               </thead>
               <tbody>
                  <c:set var="buyer" value="${prod.buyer }" />
                  <tr>
                     <td>
                        <c:url value="/buyer/buyerView.do" var="buyerViewURL">
                           <c:param name="what" value="${buyer.buyerId }" />
                        </c:url>
                        <a href="${buyerViewURL }">${buyer.buyerName }</a>
                     </td>
                     <td>${buyer.buyerCharger }</td>
                     <td>${buyer.buyerAdd1 }</td>
                  </tr>
               </tbody>
            </table>
         </td>
      </tr>
      <tr>
         <th>구매가</th>
         <td>${prod.prodCost}</td>
      </tr>
      <tr>
         <th>판매가</th>
         <td>${prod.prodPrice}</td>
      </tr>
      <tr>
         <th>세일가</th>
         <td>${prod.prodSale}</td>
      </tr>
      <tr>
         <th>상품요약</th>
         <td>${prod.prodOutline}</td>
      </tr>
      <tr>
         <th>상품상세</th>
         <td>${prod.prodDetail}</td>
      </tr>
      <tr>
         <th>상품이미지</th>
         <td>${prod.prodImg}</td>
      </tr>
      <tr>
         <th>재고</th>
         <td>${prod.prodTotalstock}</td>
      </tr>
      <tr>
         <th>입고일</th>
         <td>${prod.prodInsdate}</td>
      </tr>
      <tr>
         <th>적정재고</th>
         <td>${prod.prodProperstock}</td>
      </tr>
      <tr>
         <th>크기</th>
         <td>${prod.prodSize}</td>
      </tr>
      <tr>
         <th>색상</th>
         <td>${prod.prodColor}</td>
      </tr>
      <tr>
         <th>배송방법</th>
         <td>${prod.prodDelivery}</td>
      </tr>
      <tr>
         <th>단위</th>
         <td>${prod.prodUnit}</td>
      </tr>
      <tr>
         <th>입고량</th>
         <td>${prod.prodQtyin}</td>
      </tr>
      <tr>
         <th>출고량</th>
         <td>${prod.prodQtysale}</td>
      </tr>
      <tr>
         <th>마일리지</th>
         <td>${prod.prodMileage}</td>
      </tr>
      <tr>
         <th>구매자목록</th>
         <td>
            <table>
               <thead>
                  <tr>
                     <th>구매자명</th>
                     <th>휴대폰</th>
                     <th>이메일</th>
                  </tr>
               </thead>
               <tbody>
                  <c:choose>
                     <c:when test="${not empty prod.memberSet }">
<!--                         한명이라도 샀다. -->
                        <c:forEach items="${prod.memberSet }" var="user">
                           <c:url value="/member/memberView.do" var="memberViewURL">
                              <c:param name="who" value="${user.memId }" />
                           </c:url>
                           <tr>
                              <td>
                                 <a href="${memberViewURL}">${user.memName }</a>
                              </td>
                              <td>${user.memHp }</td>
                              <td>${user.memMail }</td>
                           </tr>
                        </c:forEach>
                        </c:when>
                     <c:otherwise>
                        <tr>
                           <td colsapn="3">제발 팔려라!</td>
                        </tr>
                     </c:otherwise>
                  </c:choose>
               </tbody>
            </table>
         </td>
      </tr>
      
      
      
      </body>
</html>