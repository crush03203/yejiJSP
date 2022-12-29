package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdDAOImplTest {

	private ProdDAO dao = new ProdDAOImpl();
	@Test
	public void test() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		log.info("buyer : {} " , prod.getBuyer());
		prod.getMemberSet().stream()
				.forEach(user-> {
					log.info("구매자 : {}" ,user);
				});
	}

}
