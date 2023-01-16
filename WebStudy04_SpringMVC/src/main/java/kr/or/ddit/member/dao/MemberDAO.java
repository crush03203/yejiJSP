package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Persistence Layer
 *
 */


public interface MemberDAO {
	
	
	
	/**
	 * 회원신규등록
	 * @param member
	 * @return 등록 레크드 수(rowcnt) > : 성공,그렇지 않으면 <= : 실패
	 */
	public int insertMember(MemberVO member); //int : 몇개의 회원이 입력되었는가 하는 rowcount를 의미
	
	

	
	/**
	 *  회원 전체목록 조회
	 * @param pagingVO TODO
	 * @return size == 0인 경우, 조건에 맞는 레코드 없음 
	 */
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);
	//페이징
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO);
		
	
	
	/**
	 * 회원상세조회
	 * @param memId
	 * @return 조건에 맞는 레코드 없을 경우(ex. "sdfsdfsghmmmㄴㅜ"), null반환
	 * 
	 * 
	 */
	public MemberVO selectMember(@Param("memId") String memId); //멤버 하나만 찾기
	
	/**
	 * 회원정보수정
	 * @param member
	 * @return 수정된 레코드 수 (rowcnt) > : 성공,그렇지 않으면 <= : 실패
	 */
	public int updateMember(MemberVO member);
	
	
	/**
	 * 회원정보 삭제
	 * @param memId
	 * @return 삭제된 레코드 수 (rowcnt) > : 성공,그렇지 않으면 <= : 실패
	 */
	public int  deleteMember(String memId);
}
