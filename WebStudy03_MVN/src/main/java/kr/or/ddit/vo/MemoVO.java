package kr.or.ddit.vo;

import java.io.Serializable;


//직렬화를 하려면 그 객체가 가지는 프로퍼티도 직렬화가 가능해야함 
//근데 transient를 가지고있는 객체는 그 객체의 프로퍼티가 직렬화 불가능해도 항상 직렬화 가능함


public class MemoVO implements Serializable {
	
	//transient
	//transient를 가지고있는 객체는 그 객체의 프로퍼티가 직렬화 불가능해도 항상 직렬화 가능함 (데이터를 보호할 수 있음)
/*	private transient Object prop;
	public Object getProp() {
		return prop;
	}
	public void setProp(Object prop) {
		this.prop = prop;
	}*/
	//private transient String writer; // 정보를 보호하고 싶으면 transient만 붙이면됨. 그럼 직렬화될때 writer를 제외한 나머지 정보만 출력됨 
	
	

	private Integer code;
	private String writer; 
	private String content;
	private String date;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "MemoVO [code=" + code + ", writer=" + writer + ", content=" + content + ", date=" + date + "]";
	}
	

}
