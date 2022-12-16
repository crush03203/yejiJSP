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

