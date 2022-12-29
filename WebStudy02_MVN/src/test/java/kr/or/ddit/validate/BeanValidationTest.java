package kr.or.ddit.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
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
	
//	@Before //testcase 이전에 무조건 한번 실행
	@BeforeClass //여러개 있어도 무조건 딱 한번만?
	public static void setUpBeforeClass() {
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		String memssageBaseName = "kr.or.ddit.msgs/errorMessage";
		ValidatorFactory factory = Validation.byDefaultProvider()
		        					.configure()
		        					.messageInterpolator(
		        							new ResourceBundleMessageInterpolator(
		        									new PlatformResourceBundleLocator( "memssageBaseName" )
		                )
		        )
		        					.buildValidatorFactory();
		
		
		validator = factory.getValidator();
	}
	@Test
	public void memberVOTest() {
		MemberVO member = new MemberVO();
//		member.setMemId("b001");
////		member.setMemBir("2000/01/01"); //패턴여부 체킹 해야한다
//		member.setMemBir("2000-01-01"); 
////		member.setMemMail("aagmail.com"); //패턴여부 체킹 해야한다
//		member.setMemMail("aag@mail.com");
////		member.setMemPass("ab"); //패턴여부 체킹 해야한다
//		member.setMemPass("abvd");
////		member.setMemMileage(-1000); //패턴여부 체킹 해야한다
//		member.setMemMileage(1000);
		
		Set<ConstraintViolation<MemberVO>> constraintViolations = 
//							validator.validate(member); //기본그룹
							validator.validate(member,InsertGroup.class);
		constraintViolations.stream()
							.forEach(singleViolation->{
								Path propertyPath = singleViolation.getPropertyPath();
								String errorMessage = singleViolation.getMessage();
								log.error("{} : {}",propertyPath,errorMessage);
		});
	
	}
}
