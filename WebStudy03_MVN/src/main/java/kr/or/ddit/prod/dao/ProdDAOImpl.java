package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {

	//mybatis와의 의존관계
	private SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory(); //MybatisUtils여기서 session받아오기 

	@Override
	public ProdVO selectProd(String prodId) {

		 try(
				 SqlSession sqlSession = sqlSessionFactory.openSession(); //sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
			    ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class); //mapper에 interface(MemoDAO.java)에 대한 정의 해줘야 됨 
				return mapperProxy.selectProd(prodId); 
		}
	}

	
	
	
	
	@Override
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO) {
		  try(
			  SqlSession sqlSession = sqlSessionFactory.openSession();   
		         ){
			  ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
		            return mapperProxy.selectTotalRecord(pagingVO);
		 }
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		 try(
				 SqlSession sqlSession = sqlSessionFactory.openSession();  
		){
			 ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);  
				return mapperProxy.selectProdList(pagingVO); 
		}
	}





	@Override
	public int insertProd(ProdVO prod) {
		 try(
				 SqlSession sqlSession = sqlSessionFactory.openSession();  
		){
			 ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);  
				int rowcnt = mapperProxy.insertProd(prod); 
				sqlSession.commit();
				return rowcnt;
		}
	}





	@Override
	public int updateProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}



}
