package kr.or.ddit.validate;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

public class ValidationUtilsTest {
	
	
	private static final Logger log = LoggerFactory.getLogger(ValidationUtilsTest.class);//log 프레임워크 쓰기 

	@Test
	public void testValidate() {
		
		MemberVO target = new MemberVO();
		Map<String, List<String>> errors = new LinkedHashMap<>();
		
		boolean valid =   ValidationUtils.validate(target, errors, InsertGroup.class); 
		
		if(!valid) {//검증에 통과 못했을 경우
			errors.forEach((k,v)->{
				log.error("{} : {}", k, v); //k: key, v: value
			});
		}
		
		
		
	}

}
