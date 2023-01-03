package kr.or.ddit.vo;

import java.io.Serializable;
//DB를 이용하지 않고 파일저장으로 메모를 관리할 수 있는 프로그램 생성                           
public class MemoVO implements Serializable{
//	transient : 민감한 데이터에 사용한다
	private Integer code;
	private  String writer;
	private String content;
	private String date;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
//	public String getWriter() {
//		return writer;
//	}
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
