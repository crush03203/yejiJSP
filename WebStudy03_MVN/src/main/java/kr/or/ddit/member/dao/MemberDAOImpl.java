package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemoVO;
import kr.or.ddit.vo.PagingVO;


/*AbstractJDBCDAO : mybatis 없을때는 이걸로 해줬음 */
public class MemberDAOImpl implements MemberDAO {

	
	private SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory(); //MybatisUtils여기서 session받아오기 

	
	
   @Override
   public int selectTotalRecord(PagingVO<MemberVO> pagingVO) {
      try(
            SqlSession sqlSession = sqlSessionFactory.openSession();   
         ){
            MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
            return mapperProxy.selectTotalRecord(pagingVO);
         }
      }
	@Override
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO) {
		 try(
				 SqlSession sqlSession = sqlSessionFactory.openSession(); //sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
				MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class); //mapper에 interface(MemoDAO.java)에 대한 정의 해줘야 됨 
				return mapperProxy.selectMemberList(pagingVO); 
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
				 SqlSession sqlSession = sqlSessionFactory.openSession(); //sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
				MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class); //mapper에 interface(MemoDAO.java)에 대한 정의 해줘야 됨 
				return mapperProxy.selectMember(memId); 
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(); // 트랜잭션(ACID) 시작         sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.insertMember(member);//받아온 파라미터 안넣어주고 다른방법으로 처리하는 방법
			sqlSession.commit(); //  트랜잭션 종료           커밋을 해서 올리기 
			return rowcnt;
		}
	}
	

	
	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(); // 트랜잭션(ACID) 시작         
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.updateMember(member);//받아온 파라미터 안넣어주고 다른방법으로 처리하는 방법
			sqlSession.commit(); //  트랜잭션 종료           
			return rowcnt;
		}
	}

	@Override
	public int deleteMember(String memId) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(); // 트랜잭션(ACID) 시작     => 세션 오픈  
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.deleteMember(memId);//받아온 파라미터 안넣어주고 다른방법으로 처리하는 방법
			sqlSession.commit(); //  트랜잭션 종료           커밋을 해서 올리기 
			return rowcnt;
		}
	}



	
}
