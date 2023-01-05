package kr.or.ddit.vo;

import static org.junit.Assert.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PagingVOTest {

	@Test
	public void test() {
		PagingVO pagingVO = new PagingVO();
		pagingVO.setTotalRecord(108); //이거 들어가는 순간 totalpage결정되고
		pagingVO.setCurrentPage(3); //클라이언트가 3페이지 요청
		log.info("paging : {}", pagingVO);
			
		pagingVO.setCurrentPage(7); //클라이언트가 7페이지 요청
		log.info("paging : {}", pagingVO);	
		
	}
	

}
