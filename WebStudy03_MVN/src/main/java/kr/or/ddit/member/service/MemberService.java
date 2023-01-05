package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Business Logic Layer
 * @author PC-20
 *
 */

public interface MemberService {

	/**
	 * 회원가입
	 * @param member
	 * @return 아이디 중복으로 인한 실패(=>PKDUPLICATED(ServiceResult emnum )), 가입성공(=> OK emnum), 가입 실패 (=>FAIL emnum)
	 */
	public ServiceResult createMember(MemberVO member);
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO);
	
	
	
	/**
	 * 회원정보 상세 조회 
	 * @param memId
	 * @return  회원존재하지 않는 경우, {@link UserNotFoundException} 발생
	 */
	public MemberVO  retrieveMember(String memId);
	
	
	/**
	 * 회원수정
	 * @param member
	 * @return 존재여부 확인 후 -> 비번 인증 확인 후 -> 성공 또는 실패
	 * 존재 부 일경우 : NOTEXIST실행
	 * 비번인증 실패 시 : INVALIDPASSWORD실행 
	 * 성공시 : OK실행
	 * 실패시 : FAIL실행 
	 */
	//public int modifyMember(MemberVO member);
	public ServiceResult modifyMember(MemberVO member);
	
	
	/**
	 * 회원탈퇴 
	 * @param memId
	 * @return 존재 여부 확인 -> 비번 인증 -> 탈퇴성공 또는 탈퇴 실패 
	 * 존재 부 일경우 : NOTEXIST실행
	 * 비번인증 실패 시 : INVALIDPASSWORD실행 
	 * 성공시 : OK실행
	 * 실패시 : FAIL실행 
	 */
	//public int removeMember(MemberVO member);
	public ServiceResult removeMember(MemberVO member);
	
	
}
