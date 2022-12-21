<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h4>EL에서 집합 객체 접근방법</h4>
<%
   //1. 배열일 경우
   String[] array = new String[]{"value1", "value2"};
   
   //2. list일 경우
   List<String> list = Arrays.asList("value1", "value2");
   
   //3. set일 경우
   Set<String> set = list.stream().collect(Collectors.toSet());

   //4.Map일 경우
   Map<String, Object> map = new HashMap<>();
//   map.put("key1", "value1");
//   map.put("key2", "value");
   map.put("key-1", "value1");
   map.put("key 2", "value");
   

   //setAttribute
   pageContext.setAttribute("array", array);
   pageContext.setAttribute("list", list);
   pageContext.setAttribute("set", set);
   pageContext.setAttribute("map", map);
   
   
   pageContext.setAttribute("memo", null);

%>



<pre>

   [각각의 집합객체에 접근해보기] 
   
   1) array에서 꺼내보기 :
      <%=array[1] %>
      <%-- <%=array[3] %>  => 500에러 뜸. array안에 두개요소밖에 없는데 3을 출력해버리니까 오류뜸--%>, 
      ${array[3] } => 500에러 안남 그냥 공백만 뜸 (el은 예외 발생해도 그거 처리 하는게 목적 아님 그냥 출력이 목적임 )
   
   
   2) list에서 꺼내보기 : 
      <%=list.get(1) %>
      ${list[1] }  => 결과값 value2
      ${list.get(1) } => 결과값 value2 (el에서는 메소드 직접 호출 하지 마라)
      
      
      <%-- <%=list.get(3) %>  => 500에러 뜸 --%>
      ${list[3] }  => 500에러 안남 그냥 공백만 뜸
      <%-- ${list.get(3) } => 500에러 뜸 --%>
      
   3) set : 
      <%=set %>
      ${set }
      
   4) map : 
<%--       
      <%=map.get("key1") %>
      ${map.get("key1") }
      ${map.key1 }
      ${map['key1'] }   
--%>

      <%=map.get("key-1") %> => 출력: value1
      ${map.get("key-1") } => 출력: value1
      ${map.key-1 } => 출력: -1
      ${map['key-1'] }    => 출력: value1
      
      <%=map.get("key 2") %> => 출력: value2
      ${map.get("key 2") } => 출력: value2
      \${map.key 2 } => 출력: 500에러(파싱에러)
      ${map['key 2'] }    => 출력: value2
      
   
   
   
   5) memo에서 writer 값 꺼내보기 :
      ${memo.writer } => 에러 안나고 공백뜸(원래 같았으면 nullpointerException떠야됨) el은 예외 발생해도 그거 처리 하는게 목적 아님 그냥 출력이 목적임
      , \${memo.getWriter() } => 공백뜸
      , ${memo['writer'] } => 공백뜸


</pre>



</body>
</html>