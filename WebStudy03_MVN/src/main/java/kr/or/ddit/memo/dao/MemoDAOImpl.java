package kr.or.ddit.memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.MemoVO;

public class MemoDAOImpl implements MemoDAO{

	private SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory();
	
	//SqlSessionFactory과는 다르게  sqlSession은 지역변수의 형태로만 존재할수있음 => 항상 열고 close해주는 역할 필요함 
	
	@Override
	public List<MemoVO> selectMemoList() {
		 try(
			 SqlSession sqlSession = sqlSessionFactory.openSession(); //sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		 ){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class); //mapper에 interface(MemoDAO.java)에 대한 정의 해줘야 됨 
			return mapperProxy.selectMemoList(); 
			//return sqlSession.selectList("kr.or.ddit.memo.dao.MemoDAO.selectMemoList");	 
		 }
	}

	
	//insert, update, delete는 트랜잭션 관리 필요  => open + close
	@Override
	public int insertMemo(MemoVO memo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); // 트랜잭션(ACID) 시작         sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
			int rowcnt = mapperProxy.insertMemo(memo);//받아온 파라미터 안넣어주고 다른방법으로 처리하는 방법
			//int rowcnt =  sqlSession.insert("kr.or.ddit.memo.dao.MemoDAO.insertMemo");  => 틀린방법
			//int rowcnt =  sqlSession.insert("kr.or.ddit.memo.dao.MemoDAO.insertMemo", memo); =>맞는방법(받아온 파라미터 고대로 넣어줘야됨)
			sqlSession.commit(); //  트랜잭션 종료           커밋을 해서 올리기 
			return rowcnt;
		}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); //트랜잭션 시작             sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class); //mapper에 interface에 대한 정의 해줘야 됨 
			int rowcnt = mapperProxy.updateMemo(memo); //mapperProxy:가짜구현체
//			int rowcnt =  sqlSession.update("kr.or.ddit.memo.dao.MemoDAO.updateMemo", memo); //받아온 파라미터 고대로 넣어줘야됨
			sqlSession.commit(); // 트랜잭션 종료  
			return rowcnt;
		}
	}

	@Override
	public int deleteMemo(int code) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); // 트랜잭션 시작                sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class); //mapper에 interface에 대한 정의 해줘야 됨 
			int rowcnt = mapperProxy.deleteMemo(code); //mapperProxy:가짜구현체
			//int rowcnt =  sqlSession.delete("kr.or.ddit.memo.dao.MemoDAO.deleteMemo", code); //받아온 파라미터 고대로 넣어줘야됨
			sqlSession.commit();  //트랜잭션 종료  
			return rowcnt;
		}
	}

}
