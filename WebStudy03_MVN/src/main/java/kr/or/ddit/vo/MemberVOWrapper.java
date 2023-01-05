package kr.or.ddit.vo;

import java.security.Principal;

//principal  구현체 사용하기 위해 membervo 수정
//1단계 adapter만들어서 vo 건들지 않고 memberVO수정
public class MemberVOWrapper implements Principal {
	
	private MemberVO realMember;

	//adapter는 기본생성자를 가지지 않는다
	public MemberVOWrapper(MemberVO realMember) {
		super();
		this.realMember = realMember;
	}
	
	public MemberVO getRealMember() {
		return realMember;
	}

	@Override
	public String getName() {
		return realMember.getMemId();
	}
	
	
	
}
