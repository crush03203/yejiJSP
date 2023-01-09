package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BookVO;

public interface BookService {
	//메소드 시그니처
	public int insert(BookVO bookVO);
	
	//책 상세
	public BookVO detail(BookVO bookVO);
	
	//책 수정
	int update(BookVO bookVO);

	//책 삭제
	public int delete (BookVO bookVO);
	
	//책 목록
	public List<BookVO> select(String keyword);
}
