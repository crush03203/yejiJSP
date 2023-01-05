package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.BookDao;
import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;

//서비스 클래스 : 비즈니스 로직
//스프링 MVC 구조에서 Controller와 DAO를 연결하는 역할
//프링이가 자바빈으로 미리 등록해줌
@Service
public class BookServiceImpl implements BookService {
	//데이터베이스 접근을 위해서 BookDao 인스턴스(객체)를 주입받자
	//@Autowired : servlet-context.xml의 component-scan에 의해 이미 메로리에 있는
	//bookDao 객체를 BookServiceImpl 클래스로 주입하여 사용 
	//DI(Dependecy Injection) : 의존성 주입
	//IoC(Inversion of Control) : 제어의 역전(개발자가 new 를 하지 않고,  스프링에게 사용요청)
	@Autowired
	BookDao bookDao;
	
	@Override
	public int insert(BookVO bookVO) {
		//BOOK테이블에 insert 된 그 기본키 값(bookId:1)
		return this.bookDao.insert(bookVO);
	}
	//책 상세보기
	@Override
	public BookVO detail(BookVO bookVO) {
		return this.bookDao.detail(bookVO);
	}
	
	//	책 수정하기
	//insert , update, delete의 경우 리턴 타임은 int
	@Override
	public int update(BookVO bookVO) {
		return this.bookDao.update(bookVO);
	}
	
	//책 삭제하기
	@Override
	public int delete(BookVO bookVO) {
		return this.bookDao.delete(bookVO);
	}
	
	//책 목록
	@Override
	public List<BookVO> select() {
		return this.bookDao.select();
		
	}
}

