package kr.or.ddit.vo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagingVOTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		PagingVO pagingVO = new PagingVO();
		pagingVO.setTotalRecord(108);
		pagingVO.setCurrentPage(3);
		log.info("paging : {}",pagingVO );
		
		pagingVO.setCurrentPage(7);
		log.info("paging : {}",pagingVO );
	}

}
