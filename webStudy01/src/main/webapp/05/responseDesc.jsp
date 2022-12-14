<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/ResponseDesc.jsp</title>
</head>
<body>
<h4>response(HttpServletResponse)</h4>
<pre>
	Http의 response packaging
	1. Response Line : Staatus Code(응답상태 코드, XXX)
		100~ : ...ING...
		200~ : OK
		300~ : 최종 처리하기 위해 클라이언트의 추가 액션이 필요함.(response body가 없음.)
			304(cache data 관련) : Not Modified
			301/302/307 : Moved + Location response header와 함께 사용(redirect request)
			<%--   <%
 				request.getRequestDispatcher("/04/messageView.jsp").forward(request, response); //서버 내에서 이동 
				String location = request.getContextPath() + "/04/messageView.jsp"; 
				response.sendRedirect(location); //클라이언트로부터 새로운 요청이 발생
			%> --%>
		
		400~ : client side error -> Fail (사용자의 문제)
		400 : <%=HttpServletResponse.SC_BAD_REQUEST %>, 클라이언트 전송데이터(파라미터) 검증시 활용.
		401/403 : 인증(Authentication /신원 인증)과 인가(Authorization/신원확인 된 사람에게 어떤 권한?) 기반의 접근 제어에 활용 /예)너는 들어올 수 없어
			  <%= HttpServletResponse.SC_UNAUTHORIZED %>,<%=HttpServletResponse.SC_FORBIDDEN %>
		404 : <%=HttpServletResponse.SC_NOT_FOUND %>
		405 : <%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %>, 현재 요청의 메소드에 대한 콜백 메소드가 재정의 되지 않았을 때.
		406/415 : : content-type(MIME)과 관련된 상태코드
			 <%=HttpServletResponse.SC_NOT_ACCEPTABLE %>, Accept request 헤더에 설정된 MINE 데이터를 만들 수 없을 때.
			 	ex) accept:application/json
			 		content-type:application/json(XXX)
			 <%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE%> : Content-type request 헤더를 해석할 수 없을 때
				ex) content-type : application/json ->unmarshalling(XXX)
		
		500~ : server side error -> Fail, 500(Internal Server Error) (서버의 문제  )
	
	2. Response Header : meta data 
		2-1. Content(body)에 대한 부가정보 설정 : Content-*, Content-Type(MIME), Content-Length(size)
								Content-Disposition(content name , 첨부여부)
				<%
// 					response.setHeader("Content-Disposition", "inline[attatchement];filename=\"파일명\"");
				%>
		2-2. Cache control : 자원에 대한 캐싱 설정
		2-3. Auto Request : 주기적으로 갱신되는 자원에 대한 자동 요청
		2-4. Location : 기반의 이동구조(Redirection)
		
	3. Response Body(Content body, message body) :
<%-- 		respomse.getWriter(), response.getOutputStream(), <% %>, out --%>

</pre>
</body>
</html>