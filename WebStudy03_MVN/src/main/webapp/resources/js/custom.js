/**
 * 
 */




$.fn.serializeObject=function() {//제이쿼리 안에 serializeObject라는 새로운 함수를 갖게 됐음 
	
	if(this.prop('tagName') != 'FORM') //tagName을 가지고있는 객체가 form태그가 아니라면 
		throw Error("form 태그 외에는 사용 불가 ");
	//this에서 tagName이라는 걸 꺼내겠다 
	//   this=> $.fn에서 사용되고있는  객체 
	
	
	
	let fd = new FormData(this[0]); //input 에  작성한 값들~ form에 저장되어있는 데이터들 
	let nameSet = new Set(); //중복 방지, 제거해주기 위해서 
	for(let key of fd.keys()) {
		nameSet.add(key); //중복되는 이름을 가지고 있으면 제거 
	}
	
	
	let data = {}; //비어있는 객체 (data ={} 이런식으로 뒷단으로 넘겨야 되니까)
	for(let name of nameSet) {
		
		//fd.get('writer');
		//fd.get(name); //이름은 하나인데 입력태그 두개일 경우  한개만 꺼내옴
		let values = fd.getAll(name);//이름은 하나인데 입력태그 두개일 경우  두개 다 꺼내올수 있음 
		if(values.length>1){ //값이 여러개일 경우 
			//data.writer;
			//data[name] ; //얼마든지 객체의 구조를 동적으로 만들수 있음 위처럼 하면 writer 하나만 사용할 수 있으니까 
			data[name] = values ;
			
		}else {
			data[name] = values[0]; //배열이 필요가 없음 하나만 가져오면 됨 
		}
	}
	return data;

}



//폼태그의 모든 입력 데이터의 이름과 값을 콘솔에 로그로 출력할 수 있는 함수.
//ex) $("form").log().serializeObject 이렇게 사용 할 수 있게끔 만들어줘야됨 
//$.log
$.fn.log = function() {
	let data = this.serializeObject(); //모든 입력 데이터의 모든 key value값을 다 가지고 있음 
	for(let prop in data) {
		console.log(prop + " : " + data[prop]); //data.writer[prop] : prop속에 들어있는 writer을 꺼내옴
	}
		
	// of: 반복의 대상이 될수 있는 경우 배열..
	// in : 일반객체 일 경우 대신 그 객체가 가지는 프로퍼티 하나하나에 접근할 수 있음
	return this; 
}




//$.fn.sessionTimer=function(timeout , msgAreaSelector, btnSelector) {
$.fn.sessionTimer=function(timeout , msgObj) {
	
	if(!timeout) //timeout이 없으면 ( )
		throw Error("세션 타임아웃 값이 없음");

	
	const SPEED = 100;
	
	/*const TIMEOUT = <%=session.getMaxInactiveInterval() %>; */
//	const TIMEOUT = ${pageContext.session.maxInactiveInterval};
	const TIMEOUT = timeout ; //sessionTimer(${pageContext.session.maxInactiveInterval})

//	const timerArea = $("#timerArea");
	const timerArea = this;
	
	
	
	//event propagation : bubbling 방식
	let msgArea = null;
	if(msgObj){ //msgObj가 있을때만 상수(msgArea) 만들어서 작업하기
		// this => .controlBtn
		//	const msgArea = $("#msgArea").on("click", ".controlBtn" , function(event){
		msgArea = $(msgObj.msgAreaSelector).on("click", msgObj.btnSelector , function(event){
			//console.log(this.id + "," + $(this).prop("id"));
			if(this.id == "YES"){ //예 버튼을 눌렀을 경우 
				jobClear();
				timerInit();
				
				//세션이 만료되지 않고 계속 새로운 세션 요청을 해줘야됨(서버에서 세션을 연장하기 위한 목적)
				//뒷단에서 정보를 가져오기 위한 목적이 아니기 때문에 body가 아니라 head, line만 받아오면됨 => method : head 
				// url 없다는 의미 : 현재 가지고 있는 페이지 그대로 들고옴
				$.ajax({
					method : "head"
				});
			};
			msgArea.hide(); //메시지 본래의 역할이 끝났기 때문에 다시 숨김
		}).hide(); //1분남은 시점이 됐을때 등장해야됨
	}
	
	
	
	
	
	
	//초기화 - 데이터 내용 비우기 
	let jobClear = function() {
		let timerJob = timerArea.data("timerJob"); //데이타 숨기기  
		// 데이터 꺼낼때는  :  timerArea.data("timerJob") 
		// 데이터 넣을 때는  : timerArea.data("timerJob", timerJob)
		if(timerJob) // 이 데이터가 있다면
			clearInterval(timerJob);
		let msgJob = msgArea.data('msgJob'); //원래 job의 id 형태를 유지하면서 데이터를 숨길수있음
		if(msgJob)
			clearTimeout(msgJob);
	}
	
	
	
	
	
	
	let timerInit = function() {
		if(msgObj) { //msgObj가 있을 때만 msgJob을 실행해라 
			//1분남은 시점이 됐을때 등장하는 msgArea 버튼 영역  
			let msgJob = setTimeout(() => {
				msgArea.show();
			}, (TIMEOUT-60)*SPEED); //밀리세컨드로 바꿔야하니까 *1000 해줘야됨
			msgArea.data('msgJob', msgJob); //원래 job의 id 형태를 유지하면서 데이터를 숨길수있음
		}
		
		let timer = TIMEOUT; //  1초에 한번씩 discount하기 
		//  1초에 한번씩 discount하기 
		let timerJob = setInterval(() => {
			if(timer == 1){
				clearInterval(timerJob); //timer가 1일때 discount 멈춤 (0보다 작을땐 더이상 디스카운트 안되게)
				location.reload();
			}else
				timerArea.html( timeFormat(--timer) ); // 시분초 출력하기 
		}, SPEED);
		
		timerArea.data("timerJob", timerJob); //데이타 숨기기
	}
	timerInit();
	
	
	

	// 시분초 출력하기 
	let timeFormat = function(time) {
		let min = Math.trunc(time / 60); //분 출력하기  - trunc: 소수점 이하 자리 지우기 
		let sec =  time % 60; //초 출력하기 
		return min + ":" + sec;
	}	
	
	
	
	
	
	return this;

}















