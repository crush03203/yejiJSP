package kr.or.ddit.enumpkg;

public enum ServiceResult {
	OK, 
	FAIL,
	PKDUPLICATED, //pk이 중복되는 상황 
	INVAILDPASSWORD, //비번인증 실패 
	NOTEXIST
}
