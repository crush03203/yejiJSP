/**
 * 
 */

$.fn.serializeObject=function(){
	if(this.prop('tagName')!='FORM')
		throw Error("form 태그 외에는 사용불가.");
	let fd = new FormData(this[0]);
	let nameSet = new Set();
	for(let key of fd.keys()){
		nameSet.add(key);
	}
	let data = {};
	for(let name of nameSet) {
		let values = fd.getAll(name);
		if(values.length>1){
			data[name] = values; //연상배열구조 
		} else {
			data[name] = values[0];
		}
	}
	return data;
}

//폼태그의 모든 입력 데이터의 이름과 값을 콘솔에 로그로 출력할 수 있는 함수.
//ex) $("form").log().serializeObject
$.fn.log=function(){ //fn안에 log라는 함수
	let data = this.serializeObject();
	for( let prop  in data) {
		console.log(prop + " : " + data[prop]);
	} 
	return this();
}

$.fn.sessionTimer = function(timeout, msgObj) {
	if(!timeout)
		throw Error("세션 타임아웃 값이 없음.");
	const SPEED = 1000;
	const TIMEOUT = timeout;
	const timerArea = this;
// 	event propagation : bubbling 방식
	let msgaArea = null
	if(msgObj) {
		msgArea = $(msgObj.msgAreaSelector).on("click",msgObj.btnSelector,function(event){
			// 		console.log(this.id + "," + $(this).prop("id"));
			if(this.id=="YES") {
				jobClear();
				timerInit();
				$.ajax({
					method : "head"
				});
			} 
			msgArea.hide();
		}).hide();
		
		
	}
	let jobClear = function() {
		let timerJob = timerArea.data("timerJob");
		if(timerJob)
			clearInterval(timerJob);
		let msgJob = msgArea.data('msgJob');
		if(msgJob) 
			clearTimeout(msgJob);
		
	}
	
	let timerInit = function() {
		if(msgObj) {
			let msgJob = setTimeout(() => {
				msgArea.show();
			}, (TIMEOUT-60) * SPEED);
			msgArea.data('msgJob',msgJob);
		}
		
		let timer = TIMEOUT;// 1초마다 디스카운트해서$("#timeArea").html(timeFormat(TIMEOUT)); 이걸 반복수행 하려고 함
		let timerJob = setInterval(() => {
			if(timer==1) {
				clearInterval(timerJob);
				location.reload();
			}
			else
				timerArea.html(timeFormat(--timer));
		}, SPEED);
		timerArea.data("timerJob",timerJob);
	}
	timerInit();
	
	let timeFormat = function(time) {
		let min = Math.trunc(time / 60); //소수점 이하의 숫자는 버린다 Math.trunc
		let sec = time % 60
		return min + " : " + sec;
	}
	
	return this;
}

