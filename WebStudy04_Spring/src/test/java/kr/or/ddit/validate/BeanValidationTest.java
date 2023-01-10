package kr.or.ddit.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

public class BeanValidationTest {
	
	private static final Logger log = LoggerFactory.getLogger(BeanValidationTest.class);
	
	private static Validator validator;
	
	@BeforeClass//클래스가 다섯개 있어도 딱 한번 실행됨 
	public static void setUpBeforeClass() {

	}
	
	@Test
	public void memberVOTest() {
		MemberVO member = new MemberVO();
		member.setMemBir("2000-01-01"); 
/*		member.setMemId("b001");
		member.setMemBir("2000-01-01"); 
		//member.setMemBir("2000/01/01");  //"\d{4}-\d{2}-\d{2}"와 일치해야 합니다
		member.setMemMail("aa@gmail.com");
		
		//member.setMemMail("aagmail.com"); //올바른 형식의 이메일 주소여야 합니다
		member.setMemPass("abcd");
		member.setMemMileage(-1000);
*/	

		
		Set<ConstraintViolation<MemberVO>> constraintViolations = validator.validate(member, InsertGroup.class); //InsertGroup => InsertGroup.class vo에서 이 그룹힌트를 가지고있는 것들만 검증하겠따
		//Set<ConstraintViolation<MemberVO>> constraintViolations = validator.validate(member); // 기본그룹 => 기본그룹이 아닌 것들은 제외하고 검증한다. 
		//Set<ConstraintViolation<MemberVO>> constraintViolations = validator.validate(member, UpdateGroup.class); //insertGroup.java에 기본그룹 상속받고있으므로 => extends Default 기본그룹이면서 insert그룹인것들을 출력한다.  
		//Set<ConstraintViolation<MemberVO>> constraintViolations = validator.validate(member, DeleteGroup.class); 
		
		
		constraintViolations.stream()
							.forEach(singleViolation->{// 한건의Violation받아오기 
								Path propertyPath = singleViolation.getPropertyPath();
								String errorMessage = singleViolation.getMessage();
								log.error("{} : {}", propertyPath, errorMessage);
							}); 
	
	}
	
}
