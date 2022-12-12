<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<!-- 

https://bigtop.tistory.com/66
https://bigtop.tistory.com/64
https://coding-with-jina.tistory.com/86
https://cheri.tistory.com/62

-->	
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력만들기</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/homework_calendar.css">
<script type="text/javascript">
window.onload = function(){
	
	//Date 객체 생성
	let date = new Date();
	
	
	
	//*** 시간  가져오기 *** 
	const getClock = function() {
		
		var currentTime = new Date();       
		const viewHour = currentTime.getHours(); 
		const viewMinutes = currentTime.getMinutes();
		const viewSeconds = currentTime.getSeconds();
		const timeInfo = document.querySelector('.time-info');
		
		//오전오후 출력 
		Date.prototype.amPm = function() {
			let hours = viewHour < 12 ? "오전" : "오후";
			return hours;
		}
		
		//시간 24시 => 12시간으로 변경
		hours = (viewHour%12)||12; //앞의 값이 false이면 12가 나타난다. hours는 hours의%12이다. hours가12일때는 값이 0이 되므로 12로 리턴한다.
		timeInfo.innerText = `\${date.amPm()} \${hours}:\${viewMinutes<10?`0\${viewMinutes}`:`\${viewMinutes}`}:\${viewSeconds<10?`0\${viewSeconds}`:`\${viewSeconds}`} `;
		setTimeout(getClock,1000);     
	}
	
	

	//***달력 그리기 ***
	/* const renderCalendar = () =>  */
	const renderCalendar = function() {
		
		const viewYear = date.getFullYear(); // 년도 가져오기 
		const viewMonth = date.getMonth(); //월 가져오기 
		const viewDate = date.getDate(); //일 가져오기 
		
		// 요일 가져오기 
		const week = new Array('일', '월', '화', '수', '목', '금', '토');
		const viewDay = week[date.getDay()]; 
		

		// 가져온 년 ,월로 year-month클래스 가진 태그에 년월 채우기
		document.querySelector('.year-month').innerText = `\${viewYear}년 \${viewMonth+1}월` ;

		

		//지난달 마지막 Date, 이번달 마지막 Date 출력 
		//new Date(year, month, 0).getDate() : 특정 연도, 월의 마지막 날짜 확인
		//특정 일자 요일 :  일요일 0 ~ 토요일 6
		const prevLast = new Date(viewYear, viewMonth, 0);
		const thisLast = new Date(viewYear, viewMonth + 1, 0);
		
		//이전달 날짜와 요일
		const PLDate = prevLast.getDate(); //new Date(viewYear, viewMonth, 0).getDate(); 
		const PLDay = prevLast.getDay(); //new Date(viewYear, viewMonth, 0).getDay();
		
		//다음달 날짜와 요일
		const TLDate = thisLast.getDate(); 
		const TLDay = thisLast.getDay(); 
		
		// Dates기본배열
		const prevDates = [];
		const thisDates = [...Array(TLDate + 1).keys()].slice(1); //배열 속 다 뒤적거려서 TLDate(다음달 날짜)의 키 값을 뽑아서 1개 썰어내라?....ㅎ.............뭔소리여...하..
		const nextDates = [];

		//prevDates  계산
		if(PLDay !== 6 ) {
			for(let i = 0; i < PLDay + 1; i++) {
				prevDates.unshift(PLDate - i);
			}
		}
		
		//nextDates 계산 
		for(let i = 1; i<7 - TLDay; i++) {
			nextDates.push(i);
		}
		
		//Dates  합치기
		const dates = prevDates.concat(thisDates, nextDates);
		
		//Dates 정리
		const firstDateIndex = dates.indexOf(1);
		const lastDateIndex = dates.lastIndexOf(TLDate);
		dates.forEach((date, i) => {
			const condition = i >= firstDateIndex && i < lastDateIndex + 1 ? 'this' : 'other';
			dates[i] = `<div class="date"><span class="\${condition}">\${date}</span></div>`;
		});
		
		// Dates 그리기
		document.querySelector('.dates').innerHTML = dates.join('');
		
		//***오늘날짜 ***
		const today = new Date(); //오늘날짜에 맞는  date객체를 새로 만들어줘야 이전달, 다음달에 today 안잡힘 
		//상단에 오늘날짜 출력 
		document.querySelector('.today-info').innerText = `\${today.getFullYear()}년 \${today.getMonth()+1}월 \${today.getDate()}일`;
		//달력에 오늘날짜 표시
		if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
		   for (let date of document.querySelectorAll('.this')) {
		     if (+date.innerText === today.getDate()) {
		       date.classList.add('today');
		       break; // 오늘 날짜는 하나밖에 없기 때문에   오늘 날짜를 찾게 되면 더이상 반복 중지 
		     }
		   }
		}

	
	}
	

	/////////////////////////////////
	
	// 달만 출력 
	const months = document.querySelector('.months');
	const dateContainer = document.querySelector('.date-container');
	const monthsArray = function() {
		
		const viewYear = date.getFullYear(); // 년도 가져오기 
		
		// 가져온 년 ,월로 year-month클래스 가진 태그에 년월 채우기
		document.querySelector('.year-month').innerText = `\${viewYear}년` ;
		months.style.display = "block";
		dateContainer.style.display = "none";
	
		//전월 출력하기
		const monthArray= [];
		const d = new Date();

		
		for(let month = 1; month <= 12; ++month) {
		   d.setMonth(month);
		   monthArray.push(month);
		}

		months.innerHTML =  "<a href='javascript:void(0)'>" + monthArray[0] + "</a>" + 
							"<a href='javascript:void(0)'>" + monthArray[1] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[2] + "</a>" + 
							"<a href='javascript:void(0)'>" + monthArray[3] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[4] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[5] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[6] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[7] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[8] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[9] + "</a>" +
							"<a href='javascript:void(0)'>" + monthArray[10] + "</a>"+
							"<a href='javascript:void(0)'>" + monthArray[11] + "</a>";  
	
	}
	document.querySelector('.year-month').addEventListener('click', function() {
		monthsArray();
	});
	
	/////////////////////////////////
	
		
	
	//***이전달 다음달로 이동하기  버튼 이벤트 ***
	const prevBtn = document.querySelector('#prev');
	const nextBtn = document.querySelector('#next');
	const todayBtn = document.querySelector('#today'); 
	
	prevBtn.addEventListener('click', function() {
		if(dateContainer.style.display == "block") {
			date.setMonth(date.getMonth() - 1);
			renderCalendar();
		}else if(months.style.display == "block") {
			date.setYear(date.getFullYear() - 1);
			monthsArray();
		}
		
	});
	nextBtn.addEventListener('click', function() {
		if(dateContainer.style.display == "block") {
			date.setMonth(date.getMonth() + 1);
			renderCalendar();
		}else if(months.style.display == "block") {
			date.setYear(date.getFullYear() + 1);
			monthsArray();
		}
	});
	todayBtn.addEventListener('click', function() {
		date = new Date();
		months.style.display = "none";
		dateContainer.style.display = "block";
		renderCalendar();
	});
	
	
	
	



	renderCalendar();
	getClock();
	
	

}

</script>
</head>
<body>

	
	<div class="container">
		<div class="top-box">
			<div class="time-info"></div>
			<a href="javascript:void(0);" class="today-info" id="today"><span class="hide">오늘날짜</span></a>
		</div>

		<!-- 달력 본문 calendar-group -->
		<div class="calendar-group">
			<div class="t-head">
				<div class="nav-group">
					<button type="button" class="year-month"><span class="hide"></span></button>
					<div class="btn-wrap">
						<button type="button" id="prev" class="prev"><span class="hide">이전달</span></button>
						<button type="button" id="next" class="next"><span class="hide">다음달</span></button>
					</div>
				</div>
			</div>
			
			<div class="date-container" style="display: block;">
				<ul class="days">
					<li class="">일</li>
					<li class="">월</li>
					<li class="">화</li>
					<li class="">수</li>
					<li class="">목</li>
					<li class="">금</li>
					<li class="">토</li>
				</ul>
				<div class="dates"></div>
			</div>
			<div class="months"></div>
			<div class="years"></div>
		</div>
		<!--  //달력 본문 calendar-group -->
	</div>
	
	




</body>
</html>