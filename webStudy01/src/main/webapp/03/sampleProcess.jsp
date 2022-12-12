<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//디코딩: 데이터 사용전 읽기전에 해야됨 
	//get방식에서는 request body가 없기 때문에 안먹는데 
	//post방식(request body) 안에 있는 문자열에만 먹힘
	request.setCharacterEncoding("EUC-KR");

%>   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sampleProcess.jsp</title>
</head>
<body>


<pre>
	request line : <%= request.getRequestURL() %>
	request line -> query String : <%= request.getQueryString() %>
	request body:  <%= request.getInputStream().available() %> 
	
	*** 표준 입력양식(form)을 통해 입력된 파라미터 (String) 확보 
	String getParameter(name)
	String[] getParameterValues(name)
	Enumeration&lt;String&gt; getParameterNames() 
	Map&lt;String,String[]&gt; getParameterMap();
</pre>

<table>
	<thead>
		<tr>
			<th>파라미터명</th>
			<th>파라미터값(들)</th>
		</tr>
	</thead>
	<tbody>
		<%
			Enumeration<String>  names = request.getParameterNames();
			//Map<String, String[]> parameterMap =  request.getParameterMap();	
		
			while(names.hasMoreElements()) {
				String parameterName = names.nextElement();
				String[] values = request.getParameterValues(parameterName);
		%>
		<tr>
			<td><%= parameterName %></td>
			<td><%= Arrays.toString(values) %></td>
		</tr>	
		<% 	
		}	
		%>
	</tbody>
</table>




<table>
	<thead>
		<tr>
			<th>파라미터명</th>
			<th>파라미터값(들)</th>
		</tr>
	</thead>
	<tbody>
		<%
			Map<String, String[]> parameterMap =  request.getParameterMap();	
			//map의 한 쌍(key, value) : entry
			for(Entry<String,String[]> entry : parameterMap.entrySet()){
				String parameterName = entry.getKey();
				String[] values = entry.getValue();
		%>
		<tr>
			<td><%= parameterName %></td>
			<td><%= Arrays.toString(values) %></td>
		</tr>	
		<% 	
		}	
		%>
	</tbody>
</table>

</body>
</html>