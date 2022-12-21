<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/elDesc</title>
</head>
<body>
<h4>EL(Expression Language)</h4>
<pre>
	: 표현(속성 데이터 출력)을 목적으로 하는 스크립트 형태의 언어
	
	<%
		String data = "데이터";
		pageContext.setAttribute("attr-Data", data);
		
	%>
	1. 속성 (EL 변수 )접근 방법
		<%=data %>,${attr-Data }
<%-- 		왜 0이 출력 되었을까 ? ${pageScope['attr-Data'] } 보안적으로 가장 좋은 방법은 이것 --%>
		pageScope, requestScope, sessionScope, applicationScope
		${pageScope.attr-Data } , ${pageScope['attr-Data'] }
	2. 연산자 종류
		산술연산자 : +-*/%
			${3+4 },${"3"+4 } ${"3" + "4" } , ${attr+4 }, \${"a"+4 }
			\${"a"+4 }이건 원래 실행되지 않지만 역슬래시를 넣으면 문자열로 변환해서 실행시킬 수 있다
			출력값 : 7, 7 , 4 , 500에러 이엘연산자는 문자열  더하기는 지원하지 않는다 //연산의 중심은 연산자이다
			${4/3 } <!-- 연산은 실수로 나온다 --> ${attr*data }<!-- 출략: 0, 없다는 상황을 그냥 0으로 표현 -->
		논리연산자 : &&(and, short-curkit), ||(or, ,short-curkit), !(not)
			${true and true} ${"true" and true}, ${true or "flase"}, ${false or attr}
			${attr} ${not attr} 
		비교연산자 : &gt;(gt), &lt;(lt) , &gt;=(ge), &lt;=(le), ==(eq), !=(ne)
			${3 lt 4} ${4 gt 3} ${4 ge 3} ${4 eq 3} ${4 ne 3}
		단항연산자 : empty , exist -> null -> length, size check
		<% 
			pageContext.setAttribute("attr", "  "); //null || isEmpty 코드랑 비슷
		    pageContext.setAttribute("listAttr", Arrays.asList("a","b"));	
		%>
			${empty attr}
			list attr : ${not empty listAttr}
		삼항연산자 : (조건식)? 참표형:거짓표현
			${not empty attr ? "attr 비어있음" : attr}
			${listAttr}, ${not empty listAttr ? listAttr : "기본값" }
	3. (속성)객체에 대한 접근법
			<%
			
				MemoVO memo = new MemoVO();
				pageContext.setAttribute("memoAttr", memo);
				memo.setWriter("작성자");
			%>
			${memoAttr}, ${memoAttr.writer}, ${memoAttr['writer']}
	4. (속성)집합 객체에 대한 접근법 : <a href="elCollection">elCollection</a>
	5. EL 기본객체 : <a href="elObject.jsp">elObject.jsp</a>
		
	

</pre>
</body>
</html>