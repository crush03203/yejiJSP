<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>

</head>
<body>

   <table>
   
      <thead>
         <tr>
            <th>일련번호</th>
            <th>상품분류</th>
            <th>상품명</th>
            <th>거래처명</th>
            <th>구매가</th>
            <th>판매가</th>
            <th>상품구매자수</th>
         </tr>
      </thead>
      <tbody id="listBody">
      
      
      <!--  el : 동기 처리할 때만 필요함 비동기에선 필요 없음  -->
<%--       <c:set var="prodList" value="${pagingVO.dataList}" /> <!-- 페이징  -->
         <c:choose>
            <c:when test="${not empty prodList }">  <!-- prodList안에 내용물이 있으면  -->
               <c:forEach items="${prodList }" var="prod">
                  <tr>
                     <td>${prod.rnum}</td>
                     <td>${prod.lprodNm }</td>
                     <td>${prod.prodName }</td>
                     <td>${prod.buyer.buyerName }</td>
                     <td>${prod.prodCost }</td>
                     <td>${prod.prodPrice }</td>
                     <td>${prod.memCount }</td>
                  </tr>         
               </c:forEach>   
            </c:when>
            <c:otherwise> 
               <tr>
                  <td colspan="7">조건에 맞는 상품 없음</td>
               </tr>
            </c:otherwise>
         </c:choose> 
--%>


      
      </tbody>
      <tfoot>
         <tr>
            <td colspan="7">
               <div class="pagingArea">
                  <%-- ${pagingVO.pagingHTML} --%>
               </div>
               <div id="searchUI">
                  <select name="prodLgu">
                     <option value>분류</option>
                     <!-- <option value="P101">삼성컴퓨터</option> -->
                     <c:forEach items="${lprodList }" var="lprod">
                        <option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
                     </c:forEach>
                  </select>
                  <select name="prodBuyer">
                     <option value>거래처</option>
                     <!-- <option value="P10101" class="P101">컴퓨터제품</option> -->
                     <c:forEach items="${buyerList }" var="buyer">
                        <option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
                     </c:forEach>
                  </select>                  
                  <input type="text" name="prodName" placeholder=" 상품명" />
                  <input type="button" value="검색" id="searchBtn" />
               </div>
            </td>
         </tr>
      </tfoot>
   </table>
   
   
   <h4>hidden form</h4>
   <form id="searchForm">
      <input type="text" name="page" /> <!-- 페이지 숫자 링크 클릭해도 아무런 반응 없는거 수정 - 페이지 클릭시 이동   -->
      <input type="text" name="prodLgu" placeholder="분류코드" />
      <input type="text" name="prodBuyer" placeholder="거래처코드" />
      <input type="text" name="prodName" placeholder=" 상품명" />
   </form>
   <script>
      
      //초기값 세팅
      $("[name=prodLgu]").on("change", function(){
         let prodLgu = $(this).val();
         prodBuyerTag.find("option:gt(0)").hide();
         prodBuyerTag.find("option."+prodLgu).show();
      });
      let prodBuyerTag = $("[name=prodBuyer]");

      
      //비동기처리로 리스트 띄우기 
      let listBody = $("#listBody");
      
      let pagingArea = $(".pagingArea").on("click", "a.paging", function(event) {
         event.preventDefault();
         let page = $(this).data("page");
         if(!page) return false;
         searchForm.find("[name=page]").val(page);
         searchForm.submit();
         return false;
      });
      
      let makeTrTag = function(prod) {
         let aTag = $("<a>")
                        .attr("href", "${pageContext.request.contextPath}/prod/prodView.do?what="+prod.prodId)
                        .html(prod.prodName);
         return $("<tr>").append(
                  $("<td>").html(prod.rnum)
                  , $("<td>").html(aTag)
                  , $("<td>").html(prod.lprodNm)
                  , $("<td>").html(prod.buyer.buyerName)
                  , $("<td>").html(prod.prodCost)
                  , $("<td>").html(prod.prodPrice)
                  , $("<td>").html(prod.memCount)
               );
      }
      let searchForm = $("#searchForm").on("submit", function(event) {
         event.preventDefault();   

         let url = this.action;
         let method = this.method;
         let queryString = $(this).serialize(); 
         
         $.ajax({
            url : url,
            method : method,
            data : queryString,  
            //contentType: "application/json;charset=UTF-8", 
            dataType : "json", 
            success : function(resp) {  
               //submit버튼 누를때마다 초기화 시켜야 input에 기존에 남아있던 데이터 없어짐
               listBody.empty();
               pagingArea.empty();
               searchForm[0].page.value="";
               
               let pagingVO = resp.pagingVO;
               
               let dataList = pagingVO.dataList;
               let trTags = [];
               if(dataList) { //tr태그들 만들어서 trTags배열에 넣어준 후 listBody에 덮어씌워주기 
                  $.each(dataList, function(index, prod) {
                     trTags.push(makeTrTag(prod));
                  });
               }else {
                  $("<tr>").html(
                     $("<td>").attr("colspan", "7")
                            .html("조건에 맞는 상품 없음")   
                  );
                  trTags.push(tr);
               }
               listBody.html(trTags);
               
               pagingArea.html(pagingVO.pagingHTML);
            },
            error : function(jqXHR, status, error) {
               console.log(jqXHR);
               console.log(status);
               console.log(error);
            }
         });
         return false;
      }).submit();//페이지 한번 로딩되자마자 무조건 1페이지 로딩
      

      
      let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
         // this == #searchBtn
         //:input => 모든 입력태그 
         let inputs = searchUI.find(":input[name]");
         $.each(inputs, function(index, input) {
            //this == input
            let name = this.name;
            let value =  $(this).val();
            
            //searchForm.get(0).searchType
            //searchForm.get(0)['searchType']
            //searchForm.get(0)[name]
            searchForm[0][name].value = value;
         });
         searchForm.submit();
      });   
   
   
   
   </script>
   <jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>