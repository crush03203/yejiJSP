<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<style>
   *{
/*       border: 1px solid green; */
      box-sizing: border-box;
   }
   table {
      color: white;
      width: 370px;
      height: 280px;
      margin: 10px;
/*       border-collapse:collapse; */
      border:none;
   }
   td {
      text-align: center;
   }
   #container{
      width: 400px;
      height: 500px;
      background-color: #36373A;
      opacity: 0.9;
   }
   #header{
      height : 125px;
      padding: 20px;
       border-bottom: solid 1px #636363;
      }
   #clock{
      width: 300px;
      height : 70px;
      color: white;
      font-size: 45px;
   }
   #date{
      width: 180px;
      height : 20px;
      color: #A6D8FF;
   }
   #content{
      height : 375px;
   }
   #selectArea{
      height : 50px;
      display : flex;
        align-items : center;
        color: white;
        
   }
   #selectYearMonth{
      width: 100px;
      height: 30px;
       margin: 10px;
       margin-left: 20px;
       margin-right: 140px;
   }
   
   #upbtn {
      margin: 15px;
   }

   #downbtn {
      margin: 15px;
   }

   #dateArea{
      height : 300px;
   }
   
</style>
<meta charset="UTF-8">
<title>윈도우 달력 구현</title>
<script>
let today = new Date();
   
let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜
let hours = today.getHours(); // 시
let minutes = today.getMinutes();  // 분
let seconds = today.getSeconds();  // 초
let whatday = getDayOfWeek(today.toLocaleDateString()); // 요일
let day = today.getDay();

function getDayOfWeek(dateStr){ //ex) 날짜를 넣으면 요일을 반환

    const week = ['일', '월', '화', '수', '목', '금', '토'];

    const dayOfWeek = week[new Date(dateStr).getDay()];

    return dayOfWeek;

}
   
$(document).ready(function() {    
   //$("#clock").text(today.toLocaleTimeString());
    $("#date").text(year + "년 "+ month + "월 " + date + "일 " + whatday + "요일");
});

/* setTimeout(function() {
     location.reload();
   }, 1000) */

function dayy(year, month){ //월의 일수를 구함
    switch(month){
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
        return 31;

        case 4: case 6: case 9: case 11:
        return 30;

        case 2:
        if(((year%400)==0||(year%4)==0&&(year%100)!=0)){
            return 29;
        }
        else{
            return 28;
        }
    }
   
}

function prevmonth(){ //이전 월로 가는 함수
   
    var selectYearMontha = document.getElementById("prev");
    var yg = document.getElementById("selectYearMonth");

    month--; //month를 계속 감소시켜준다
    if(month < 1){ // month가 1보다 작아지면
        month = 12; // month를 12로 만들어줌
        year -= 1; //year를 1 감소시켜준다
    }
    if(year < 1970){ //1970년 밑으로는 내려가지 않음..
        alert("기원전");
         for(var i=1;i<100;i--){
        window.top.moveTo(i ,i *=-1);
        }
    }

    var selectYearMontha = year + "년" + (month)+"월";

    present();
    }

function nextmonth(){  //다음 월로 가는 함수
   
    var selectYearMontha = document.getElementById("next");
    var yg = document.getElementById("selectYearMonth");

    month++; //month 를 계속 증가시켜줌
    if(month > 12){ //만약 month가 12를 넘어가면
        month = 1; // month를 1로 만듦
        year += 1; //year을 1 증가시켜준다
    }

    var selectYearMontha = year + "년" + month+"월";

    present(); //present()함수를 호출하여 다시 찍어줌

    }

function present(){
   
    var start = new Date(year, month-1, 1);
    var ymda = document.getElementById("selectYearMonth");
    var Tab = document.getElementById("tab");
   

    var row = null;
    var cnt = 0;
   
   
    var ym = year + "년" + (month)+"월";

    ymda.innerHTML = ym;

    while(tab.rows.length >1){     //테이블의 행의 길이가 2보다 크면 테이블의 행 제거함.
        tab.deleteRow(tab.rows.length -1);
    }

    row = Tab.insertRow();
   

    for(var j = 0; j<start.getDay(); j++){ //달력의 시작 일 구함
        cell = row.insertCell();
        cnt+=1;
    }

    for(var i = 0; i< dayy(year, month); i++){ //달력 일수만큼 찍어줌
    cell = row.insertCell();
    cell.innerHTML = i+1;
    cnt += 1;

    if(cnt%7 ==0){ //cnt가 7이면 행을 늘려줌
        row = tab.insertRow();
       
    }
}

}

function printClock() {
    
    var clock = document.getElementById("clock");            // 출력할 장소 선택
    var currentDate = new Date();                                     // 현재시간
    var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate() // 현재 날짜
    var amPm = '오전'; // 초기값 AM
    var currentHours = addZeros(currentDate.getHours(),2); 
    var currentMinute = addZeros(currentDate.getMinutes() ,2);
    var currentSeconds =  addZeros(currentDate.getSeconds(),2);
    
    if(currentHours >= 12){ // 시간이 12보다 클 때 PM으로 세팅, 12를 빼줌
       amPm = '오후';
       currentHours = addZeros(currentHours - 12,2);
    }

    clock.innerHTML = amPm+"</span>"+" "+currentHours+":"+currentMinute+":"+currentSeconds +" <span style='font-size:50px;'>"; //날짜를 출력해 줌
    
    setTimeout("printClock()",1000);         // 1초마다 printClock() 함수 호출
}

function addZeros(num, digit) { // 자릿수 맞춰주기
     var zero = '';
     num = num.toString();
     if (num.length < digit) {
       for (i = 0; i < digit - num.length; i++) {
         zero += '0';
       }
     }
     return zero + num;
}
</script>

</head>
<body onload="printClock()">
<div id ='container'>
   <div id = 'header'>
      <div id = 'clock'></div>
      <div id = 'date'></div>
   </div>
   <div id = 'content'>
      <div id = 'selectArea' >
         <div id = 'selectYearMonth' ></div>
         <label id='upbtn' onclick="nextmonth()"><div id='upbtn'>∧</div></label>
         <label div id='downbtn' onclick="prevmonth()"><div id='downbtn'>∨</div></label>
      </div>
      <div id = 'calendar'>
         <div id = 'dateArea'>
            <table class = "blueText"  id = "tab">
                 <tr align="center">
                     <td align="center">일</td>
                     <td align="center">월</td>
                     <td align="center">화</td>
                     <td align="center">수</td>
                     <td align="center">목</td>
                     <td align="center">금</td>
                     <td align="center">토</td>
                 </tr>      
    </table>
<script type="text/javascript">
   present();
           
</script>

         </div>
      </div>
   </div>
</div>
</body>
</html>