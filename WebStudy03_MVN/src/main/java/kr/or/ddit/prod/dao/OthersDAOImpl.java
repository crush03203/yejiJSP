package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImpl implements OthersDAO {

	private SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory(); //MybatisUtils여기서 session받아오기
	
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try(
				 SqlSession sqlSession = sqlSessionFactory.openSession(); //sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
				OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class); //mapper에 interface(MemoDAO.java)에 대한 정의 해줘야 됨 
				return mapperProxy.selectLprodList(); 
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String buyerLgu) {
		try(
				 SqlSession sqlSession = sqlSessionFactory.openSession(); //sql문 실행하기 위해 열어준다. 반드시 sql실행 후에는 닫아줘야됨 
		){
			OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class); //mapper에 interface(MemoDAO.java)에 대한 정의 해줘야 됨 
				return mapperProxy.selectBuyerList(buyerLgu); 
		}
	}

}
