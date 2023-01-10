package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j //log 프레임워크 따로 안깔아도 이거만 써도 끝
public class ProdDAOImplTest {

	private ProdDAO dao = new ProdDAOImpl();
	private PagingVO<ProdVO> pagingVO;
	
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
	}
	@Test
	public void testSelectTotalRecord() {
		int tr = dao.selectTotalRecord(pagingVO);
		assertNotEquals(0, tr); //0이 "아니어야" 한다  
	}
	@Test
	public void testSelectProdList() {
		List<ProdVO> prodList = dao.selectProdList(pagingVO);
		assertEquals(10, prodList.size()); //prodList사이즈가 10이어야 한다. 
		log.info("prodList: " , prodList);
	}
	

//	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod); //null이 있는지 확인
		log.info("buyer : {}", prod.getBuyer());
		prod.getMemberSet().stream()
						   .forEach(user->{ //사용자 한명한명에 접근
							  log.info("구매자:{}", user);
						   });
	
	
	}

}
