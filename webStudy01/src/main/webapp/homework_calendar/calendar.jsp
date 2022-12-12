<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력만들기</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/css/homework_calendar.css">
</head>
<body>

<!-- 
https://sungpil94.tistory.com/137  
https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=dynat&logNo=20059527785
https://ggoreb.tistory.com/164

https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=euijun54&logNo=221683368003
https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=jinsil0323&logNo=10108339066

-->
	<%
		Calendar cal = Calendar.getInstance(); //캘린더 클래스를 이용하여 년 월 구하기 
		int yy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH) + 1; //1~12월
		int dd = cal.get(Calendar.DATE); // 일

		/////////////////////////////////////////
		
		
		//*** 이전달 다음달 클릭시 달력 변경 
		//년과 월의 값을 받아온 것(이전달, 다음달을 클릭하였을 때 받아오는 값)
		String fyear = request.getParameter("year");
		String fmonth = request.getParameter("month");
		int year = yy; // 현재년도와 달 선언 
		int month = mm;
		int week = cal.get(Calendar.DAY_OF_WEEK); //오늘 현재 날짜 
		
		//넘어온 값이 널값이 아니면 해당하는 fyear값은 year의 값
		if(fyear != null) {
			year = Integer.parseInt(fyear);
		}
		//넘어온 값이 널값이 아니면 해당하는 fmonth값은 month의 값
		if(fmonth != null) {
			month = Integer.parseInt(fmonth);
		}
		
		//넘어온 값을 새롭게 cal객체 생성한 곳에 입력됨 (년월일 초기화)
		cal.set(year, month-1, 1);
		
		//입력 되어진 년과 달의 값을 다시 year, month 로 선언 
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;

		////////////////////////////////////////

		
		try{
			yy = Integer.parseInt(request.getParameter("yy")); 
		}catch(Exception e) {
			yy = cal.get(Calendar.YEAR);
		}
		
		// 년도를 받아서 int형태로 변환하여 파라미터 값에 yy라고 저장하여 넘기기 
		//get방식이므로 ?yy=' '&mm=' '에서 ' ' 안에 들어가는 값을 받아와야 하므로 yy와 mm의 값을 request를 이용해서 받아오는데 문자열로 받아오므로 정수로 변환한다.
		try{
			mm = Integer.parseInt(request.getParameter("mm"));
		}catch(Exception e) {
			mm = cal.get(Calendar.MONTH)+1;
		}
		
		if(mm == 13) { //만약에 달이 13월달이라면 
			yy++; //년도 한개 늘면서 
			mm = 1; //월은 1월로
		}
		if(mm == 0) { //달이 0월이면
			yy--; //년도  -되고 
			mm = 12; //월은 12월로 	
		}
		
		//1일의 요일과 그 달의 마지막 날짜 알아내기
		//cal.set()을 이용하여 원하는 날짜 시간 정보 세팅하기 
		//해당월의 날짜를 1일로 바꾼다. cal변수는 우리가 사용하는 1~12월을 0~11로 표현하기 때문에 -1을 해주어 cal.set(yy,mm-1,1)로 설정한다.
		cal.set(yy,mm-1, 1); // cal변수는 1~12월을 0~11로 나타내므로 -1해주기 ?....
		
		// 요일은 1~7로 알려준다. 1:일요일, 7:토요일
		int w = cal.get(Calendar.DAY_OF_WEEK);
		String wTxt = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREAN); //요일 한글로 변환
		
		// 그 달의 마지막 날짜를 알아낸다.
		int lastday = cal.getActualMaximum(Calendar.DATE);
		
		/////////////////////////////////////////
		

		// *** 현재시간 및 날짜요일 정보 가져오기
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("aa hh:mm:ss");//대문자 HH : 22시 
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy년 MM월 dd일 E요일"); 
	

		
		
	%>



	<div class="container">

		<div class="top-box">
			<strong><%= formatter.format(date) %></strong> 
			<p><%=formatter2.format(date)%></p>
		</div>


		<div class="tbl-wrap">
			<div class="t-head">
				<a href=""><%=yy %>년 <%=mm %>월</a>
				<a href="?year=<%= yy %>&month=<%= mm-1%>">이전달</a>
				<%-- <button type="button" onclick="location.href='?year=<%= yy %>&month=<%= mm-1%>'">이전달</button> --%>
				<a href="?year=<%= yy %>&month=<%= mm+1%>">다음달</a>
				<%-- <button type="button" onclick="location.href='?year=<%= yy %>&month=<%= mm+1%>'">다음달</button> --%>
			</div>

			<table>
				<colgroup>

				</colgroup>
				<thead>
					<tr>
						<th>일</th>
						<th>월</th>
						<th>화</th>
						<th>수</th>
						<th>목</th>
						<th>금</th>
						<th>토</th>
					</tr>
				</thead>

				<tbody>
					<tr>

					<%
					

						//*** 1일의 요일을 맞추기 위하여 공백을 출력한다.***
						for(int i = 1; i<w ; i++) 
							out.println("<td>&nbsp;</td>");
					
					
					
						//***날짜 출력 ***
						//1일부터 마지막 날짜까지 날짜 출력. 출력한 날이 토요일이면 줄바꿈 
						for(int i = 1; i<= lastday; i++) {
							
							//출력한 날의 요일 구하기 
							cal.set(yy,mm-1,i);
							w = cal.get(Calendar.DAY_OF_WEEK); // week 정보 가져와서 w에 담기 
							
							if(w == 1) //만약 일요일이다? 
								out.println("<td class='red'>" + i + "</td>");
							else if(w == 7) //만약 토요일이다?
								out.println("<td class='blue'>" + i + "</td>"); 
							else //이도저도 아니다? 
								out.println("<td>" + i + "</td>");
							
							
							//<tr></tr>만들기 
							if(w==7){ //만약 일요일이면 
								out.println("</tr>"); //tr태그 닫고 다음 tr줄로 
								if(i<lastday) //마지막날이 아닐때까지 계속 tr열면서 반복
									out.println("<tr>");
							}
						}
						

						//***마지막 날짜인 순간부터 칸 채우기 ***
						//토요일이 아니면 토요일까지 빈칸을 만들어줘야하므로 공백생성 
						if(w!=7) {// 토요일이 아니면?
							for(int i=w; i < 7; i++) 
								out.println("<td>&nbsp;</td>");
							out.println("</tr>");
						}
						

					%>

				</tbody>
			</table>
		</div>
	</div>



</body>
</html>