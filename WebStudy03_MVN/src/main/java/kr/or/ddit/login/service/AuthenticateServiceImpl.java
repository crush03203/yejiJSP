package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticate(MemberVO member) {
		
		//1. 이 사람이 있는지 여부 판단
    // selectMember => 멤버 하나만 찾기 
		MemberVO savedMember = memberDAO.selectMember(member.getMemId()); 

		if(savedMember == null || savedMember.isMemDelete()) { //그런 사람 없으면  // //savedMember.getMemDelete() => null이거나 1이면 (null이거나 탈퇴했으면 )
			throw new UserNotFoundException(String.format("%s 사용자 없음", member.getMemId()));//예외발생
		}
		
		//savedMember.getMemDelete() => c001, 7777로 로그인했을 시  => mem_delete값에 null값이 들어있기 때문에 nullpointerException
		//memberVO에 Boolean을 boolean으로 변경후 savedMember.isMemDelete() => mem_delete값에 null값이 들어있어도 자동으로 false값으로 출력 
		
		
		
		//2. 비번확인
		String inputPass= member.getMemPass(); //넘어온 비번
		String savedPass = savedMember.getMemPass(); //기존에 가지고있는 비번
		ServiceResult result = null;
		
		if(savedPass.equals(inputPass)) { //기존에 저장되어있던 비번과 넘어온 비번이 같다면
			
			try {
//				member.setMemName(savedMember.getMemName()); //인증에 성공했을 때 savedMember안에 저장되어있는 MemName을 가져올 수 있음 
//				아예 처음부터 memberVO안에 memberVO내용을 미리 저장해 둘수있다? reflection사용하면 가능
				BeanUtils.copyProperties(member, savedMember); //member: 원본 객체 / savedMember : 복사 대상 객체 => member내용을 savedMember안에 복사해서 넣어둠
				result = ServiceResult.OK; //ok출력 
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e); 
				//언체크드 오류 : 자동으로was에서 처리되는 예외  => throw new RuntimeException(e); 
				//체크드 오류 : 내가 확인해서 고쳐야하는 예외 
			}

			
		}else { //그렇지 아니면 INVAILDPASSWORD출력 
			result = ServiceResult.INVAILDPASSWORD;
		}
		
		return result;
	}

}