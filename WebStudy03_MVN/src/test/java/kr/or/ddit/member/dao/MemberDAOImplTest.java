package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDAOImplTest {
   
   private MemberDAO dao = new MemberDAOImpl();
   
   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void testInsertMember() {
      fail("Not yet implemented");
   }

   @Test
   public void testSelectMemberList() {
	   PagingVO<MemberVO> pagingVO = new PagingVO<>();
	   pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
	   pagingVO.setCurrentPage(2);
	   
      List<MemberVO> memberList = dao.selectMemberList(pagingVO);
      memberList.stream()
            .forEach(System.out::println);
      
      pagingVO.setDataList(memberList);
      
      log.info("paging: {} " ,pagingVO);
   }

   @Test
   public void testSelectMember() {
	   MemberVO member = dao.selectMember("a001");
	   System.out.println(member);
	   member = dao.selectMember("1234a");
	   assertNull(member);
   }
   

   @Test
   public void testUpdateMember() {
      fail("Not yet implemented");
   }

   @Test
   public void testDeleteMember() {
    int rowcnt = dao.deleteMember("b001");
    assertEquals(1, rowcnt);
    
   }

}