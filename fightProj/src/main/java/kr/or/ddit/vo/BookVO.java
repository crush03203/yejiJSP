package kr.or.ddit.vo;

import java.util.Date;

import lombok.Data;

//자바빈 클래스
//1) 멤버변수 2) 기본생성자 3) getter/setter 메소드
@Data //이게 있으면 게터세터데이터 알아서 생성해줌 + 투스트링도 알아서 만들어줌
public class BookVO {
	private int rnum;
	//1) 멤버변수(속성=attribute)
	private int bookId;
	private String title;
	private String category;
	private int price;
	private Date insertDate;
	//2) 기본생성자. 생략가능
	public BookVO() {}
	
	@Override
	public String toString() {
		return "BookVO [rnum=" + rnum + ", bookId=" + bookId + ", title=" + title + ", category=" + category
				+ ", price=" + price + ", insertDate=" + insertDate + "]";
	}
	
	
	
}
