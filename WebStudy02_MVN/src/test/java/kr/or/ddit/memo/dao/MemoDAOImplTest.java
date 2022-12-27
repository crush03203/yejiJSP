package kr.or.ddit.memo.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemoVO;

public class MemoDAOImplTest {
	
	private MemoDAO dao = new MemoDAOImpl();
	private MemoVO memo;
	
	@Before
	public void setUp() throws Exception {
		memo = new MemoVO();
		memo.setCode(1);
		memo.setWriter("작성자33");
		memo.setContent("내용33");
//		String date = String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS", LocalDateTime.now());
//		memo.setDate(date);
		
	}

	   @Test
	   public void testSelectMemoList() {
	      
	      List<MemoVO> memoList = dao.selectMemoList();
	      //System.out.println(memoList);
	      memoList.stream() //그 안에 들어있는 한 건 한 건에 접근할 수 있음 
	            .forEach(singleMemo->{ //그 싱글메모 한 건 한 건에 대해서  아래코드 적용
	               System.out.println(singleMemo);
	            });
	            //.forEach(System.out::println);//메소드 레퍼런스 (위 코드의 업그레이드 버전)
	   }
	   
	@Test
	public void testInsertMemo() {
		dao.insertMemo(memo);
	}
	
	@Test
	public void testUpdateMemo() {
		dao.updateMemo(memo);
	}
	
	@Test
	public void testDeleteMemo() {
		dao.deleteMemo(1);
	}
	
	
	
	
}













