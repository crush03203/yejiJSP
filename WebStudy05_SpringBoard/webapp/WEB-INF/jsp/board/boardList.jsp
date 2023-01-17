
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.ddit.or.kr/class305" prefix="ui" %>

<table class="table">
<thead>
   <th>일련번호</th>
   <th>제목</th>
   <th>작성자</th>
   <th>이메일</th>
   <th>작성일</th>
   <th>조회수</th>
</thead>
<tbody>
   <c:set var="boardList" value="${pagingVO.dataList}" />
   <c:choose>
      <c:when test="${not empty boardList }">
         <c:forEach items="${boardList }" var="board">
            <tr>
               <td>${board.rnum }</td>
               <td>
                  <c:url value="/board/boardView.do" var="viewURL">
                     <c:param name="what" value="${board.boNo }"/>
                  </c:url>
                  <a href="${viewURL }">${board.boTitle }[${board.attCount }]</a>
               </td>
               <td>${board.boWriter }</td>
               <td>${board.boMail }</td>
               <td>${board.boDate }</td>
               <td>${board.boHit }</td>
            </tr>
         </c:forEach>
      </c:when>
      <c:otherwise>
         <tr>
            <td colspan="6">조회된 게시글이 없습니다.</td>
         </tr>
      </c:otherwise>
   </c:choose>
</tbody>
<tfoot>
   <tr>
      <td colspan="6">
         <div id="pagingArea">
        	<ui:pagination pagingVO="${pagingVO }" type="bootstrap"/>
         </div>
         <form:form id="searchUI" modelAttribute="simpleCondition" method="get" onclick="return false;"> <!-- simpleCondition 컨트롤러에서 어댑터에서 만들어서 받은 이름 -->
            <form:select path="searchType">
               <form:option value="writer" label="작성자" />
               <form:option value="content" label="내용" />
            </form:select>
            <form:input path="searchWord"/>
<!--             <select name="searchType"> 이 방법을 쓰면 이전 자료가 초기화되지 않는다-->
<!--                <option value="">전체</option> -->
<!--                <option value="writer">작성자</option> -->
<!--                <option value="content">내용</option> -->
<!--             </select> -->
            
<!--             <input type="text" name="searchWord" /> -->
            <input type="button" id="searchBtn" value="검색" />
         </form:form>
      </td>
   </tr>
</tfoot>
</table>
<form:form id="searchForm" modelAttribute="simpleCondition" method="get">
   <form:hidden path="searchType"/>
   <form:hidden path="searchWord"/>
   <input type="hidden" name="page" />
</form:form>

<script type="text/javascript">
   $("[name=searchType]").val("${searchVO.searchType}");
   $("[name=searchWord]").val("${searchVO.searchWord}");
   
   let searchForm = $("#searchForm");
   let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
      let inputs = searchUI.find(":input[name]"); //: 붙이면 그 안에 모든 게 대상이 됨
      $.each(inputs, function(index, input){ //인덱스와 하나의 인풋 테이터를 받을 수 있는 블록 변수
         let name = this.name;
         let value = $(this).val();
         searchForm.find("[name="+name+"]").val(value);
      });
      searchForm.submit();
   });
   
   $("a.paging").on("click", function(event){
      event.preventDefault();
      //page란 데이터에서 몇페이지를 클릭했는지를 찾아야한다.
      let page = $(this).data("page");
      if(!page) return false;
      searchForm.find("[name=page]").val(page); //여기에 벨류를 셋팅해줘야한다
      searchForm.submit();
      return false;
   });
</script>