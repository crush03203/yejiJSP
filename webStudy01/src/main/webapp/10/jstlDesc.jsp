<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jstDesc</title>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Libarary)</h4>
<pre>
	: 커스텀 태그들 중에서 많이 활용될 수 있는 것들을 모아놓은 라이브러리.
	&lt;prefix:tagname attr_name ="attr_value" &gt;
	1. 커스텀 태그 로딩 : taglib directive
	2. preifix 를 통한 태그 접근
	
	** core 태그 종류
	1. EL 변수(속성)와 관련된 태그 : set, remove
		<c:set var="sample" value="샘플값" scope="session"/>
		${sample }, ${sessionScope.sample }
		<c:remove var="sample" scope="session"/> <!-- 어디영역에서 지울 지 정해줘야한다 -->
		--> ${sample }
		
	2. 조건문 : if(조건식){블럭문}, switch~case(조건값)...~default
		단일조건문 : if는 있지만 else는 없다
			<c:if test="${empty param.name1 }">
				파라미터 없음.
			</c:if>
			<c:if test="${not empty param.name1 }">
				파라미터 있음.
			</c:if>
		다중조건문 : choose ~ when ~ otherwise
			<c:choose>
				<c:when test="${empty param.name1 }">
					파라미터 없음
				</c:when>
				
				<c:otherwise >
					파라미터 있음
				</c:otherwise>
			</c:choose>
	3. 반복문 : foreach, forTokens, for(선언절, 조건절, 증감절 ) for(임시 블럭 변수 :  반복대상 집합객체)
		<c:set var="array" value='<%=new String[]{"value1","value2"} %>' />
		<c:forEach var="i" begin="0" end="${fn:length(array)-1 }" step="1" varStatus="vs">
<!-- 		스텝에 음수를 줄 수 는 없음 1이상의 양수만 줄 수 있고 1은 생략이 가능하다 -->
		${array[i] } --> 현재 반복의 상태(LoopTagStatus) : ${vs.index }, ${vs.count }, ${vs.first }, ${vs.last }
		</c:forEach>
		<c:forEach items="${array }" var ="element">
			${element }
		</c:forEach>
		
		int num = 3;
		intnum=3;
		select mem_id from member; 
		<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
			${token}
		</c:forTokens>
		
		<c:forTokens items="1,2,3,4" delims="," var="token">
			${token * 10}
		</c:forTokens>
		
	4. 기타
		url 재작성 : url(client side path, session parameter), redirect
			<c:url value="/06/memoView.jsp"/>
			<a href="<c:url value='/10/jstlDesc.jsp'/>">세션 유지</a>
<%-- 			<c:redirect url="/06/memoView.jsp"></c:redirect> --%>
			<%-- 위에 c:redirect 와 동일한 코드
				String location = request.getContextPath() + "/06/memoView.jsp";
				response.sendRedirect(location);
			
			--%>
		표현구조 : out //이스케이프 시켜주는 것
<%-- 		<jsp:include page="https://www.naver.com"></jsp:include> //주소 불가능함 a와 b의 결과를 끌고올때 동적 네트워크 --%>
<%-- 		<c:import url="https://www.naver.com"></c:import> //여긴 가능함  그러나 동적네트워크임 네이버에서 사용하는 웰컴페이지 소스를 다가져온 것--%>
		<c:out value="<h4>출력값</h4>" escapeXml="false"/>
</pre>
<c:import url="https://www.naver.com" var="naver" />
<c:out value="${naver }" escapeXml="true"/> 
<!-- 네이버를 가져와 그 코드를 다 이스케이프 시켜줘 그럼 네이버 소스를 다보여준다 -->
</body>
</html>












