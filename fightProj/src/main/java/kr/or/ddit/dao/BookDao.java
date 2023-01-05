package kr.or.ddit.dao;

import java.util.List;
import java.util.TooManyListenersException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

/*
 매퍼xml(book_SQL.xml)을 실행시키는 DAO(Data Access Object) 클래스
 
 Repository 어노테이션 : 데이터에 접근하는 클래스이다.라고 스프링에게 알려줌
 	스프링은 데이터를 관리하는 클래스라고 인지하여 자바빈으로 등록하여 관리해줌
 */
@Slf4j
@Repository
public class BookDao {
	
	//DI(Dependency Injection) : 의존성 주입
	//IoC(Inversion Of Control) : 제어의 역전
	//sqlSessionTemplate 타입 객체를 BookDao 객체에 주입함
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	//<insert id="insert" parameterType="bookVO">
	//insert, update, delete는 반영된 건수가 return됨
	public int insert(BookVO bookVO) {
		int bookId = 0;
		//.insert("namespace명.id", 파라미터)
		//insert성공 : 1이상, 실패면 : 0
		//BookVO [bookId=0, title=개똥이의 모험, category=소설, price=10000, insertDate=null]
		int result = this.sqlSessionTemplate.insert("book.insert",bookVO);
		log.info("result : " + result);
		if(result>0) {//insert성공
			//<selectKey order="BEFORE" resultType="int" keyProperty="bookId">
			bookId = bookVO.getBookId();
		}else {
			bookId = 0;
		}
		log.info("bookId : " + bookId);
		//입력된 기본키 값
		return bookId;
	}
	//책상세보기
	public BookVO detail(BookVO bookVO) {
//		sqlSessionTemplate : 쿼리를 실행해주는 객체(root_context.xml)
//		selectOne() 메소드 : 1행을 가져올 때 사용/ selectList()  메소드 : 결과 집합 목록 반환(다중행)
//		결과 행수가 0일 때 null 반환
//		결과 행 수가 2이상일 때 TooManyListenersException 예외 발생
//		selectOne("namespace.id", 파라미터)
		return this.sqlSessionTemplate.selectOne("book.detail",bookVO);
	}
	
//	책 수정하기
//insert , update, delete의 경우 리턴 타임은 int
	public int update(BookVO bookVO) {
		/**
		 * sqlSessionTemplate 객체의 update 매소드(쿼리Id, 쿼리 파라미터)
		 * 쿼리ID :  메퍼 xml의 namespace.id
		 * update 구문은 조건에 일치하는 모든 행을 갱신하므로
		 * 영향받은 행 수는 0또는 1이상
		 */
		return this.sqlSessionTemplate.update("book.update", bookVO);
	}
	
	//책 삭제하기
	public int delete (BookVO bookVO) {
		//delete 구문도 where 조건에 일치하는 모든 행을 삭제하므로
//		영향받은 행 수는 0또는 1 이상
		return this.sqlSessionTemplate.delete("book.delete",bookVO);
	}
	
	//책 목록
	public List<BookVO> select() {
		//sqlSessionTemplate : 쿼리 실행객체 
		//.selectList("namespce명.id")
		//.selectOne() : 쿼리 실행 결과과가 1행일 경우(단일행)
		//.selectList() : 쿼리 실행 결과가 2행 이상일 경우(다중행)
		return this.sqlSessionTemplate.selectList("book.select");
		
	}
}









