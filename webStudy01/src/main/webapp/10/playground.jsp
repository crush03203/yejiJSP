<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>네이버 크로울링 결과</h4>
<form action="<c:url value='/10/proxyTarget.jsp'/>" id="crawlerForm" > 
   <input type="url" name="target" placeholder="https://www.naver.com" />
   <input type="checkbox" name="source" value="true" />소스로보기
   <input type="submit" value="가져오기" />
</form>

<div id="container">

</div>
<script type="text/javascript">
   let container = $("#container");
   $("#crawlerForm").on("submit", function(event){
      event.preventDefault();
      let url = this.action;
      let method = this.method;
      let data = $(this).serialize();
      container.load(url, data);
//          $.ajax({
//             url : url
//       //       "https://www.naver.com"를 그대로 uri로 보내면 오류가 난다 이유는 네이버 자사의 웰컴페이지라 주소 오류남
//       //      "<c:url value='/10/naver.jsp'/>" 이렇게 받아줘야 오류다 나지 않음
//             method : method,
//             data : data,
//             dataType : "html",
//             success : function(resp) {
// //                let content = $(resp).find("div:first").html();; //네이버의 첫번째 div 태그 
//                container.html(resp);
//             },
//             erro : function(jqXHR, status, error) {
//                console.log(jqXHR);
//                console.log(status);
//                console.log(error);
      
//             }
//          });
      
      return false;
   });

</script>
</body>
</html>