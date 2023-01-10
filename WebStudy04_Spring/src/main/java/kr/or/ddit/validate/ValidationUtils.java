package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class ValidationUtils {

	private static Validator validator;
	
	static {
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		//message에 locale적용하기 @NotBlank(groups= {Default.class, DeleteGroup.class}, message="아이디는 필수")
		String messageBaseName = "kr.or.ddit.msgs.errorMessage"; // 무슨 언어로 만들더라도 똑같은 baseName으로 갖게 됨 /WebStudy02_MVN/src/main/resources/kr/or/ddit/msgs/errorMessage_ko.properties
		ValidatorFactory factory = Validation.byDefaultProvider()
        .configure()
        .messageInterpolator(
                new ResourceBundleMessageInterpolator(
                        new PlatformResourceBundleLocator(messageBaseName) //baseName
                )
        )
        .buildValidatorFactory();
		
		
		validator = factory.getValidator();
	}
	
	//Class<?> => 어떤클래스든 받겠다 
	//... => 가변파라미터 
	//Map<String, V> errors이 객체로 전달될때는 call by reference로 전달됨 
	//String, List<String> -> 하나의 프로퍼티에 대해서 여러가지가 들어갈수있음 size, notnull, mesage....
	public static <T> boolean validate(T target, Map<String, List<String>> errors,Class<?>...groups) {	
		boolean valid = true;
		
		Set<ConstraintViolation<T>> constraintViolations  = validator.validate(target, groups);
		//constraintViolations여기에 모든 에러 정보를 담고있음 
		valid = constraintViolations.isEmpty(); //비어있으면 검증에 통과 
		
		if(!valid) {
			constraintViolations.stream()
							    .forEach(single->{
							    	String propertyName = single.getPropertyPath().toString(); //Map<String,를 스트링으로 받아오기ㅏ 
							    	String errorMessage = single.getMessage(); //Map<    , List<String>>메시지 받아오기 
							    	
							    	
							    	List<String> errorMessages = Optional.ofNullable(errors.get(propertyName)) //일단 꺼내오고, 근데 있을수도 있고 없을수도 있으니까 optional
							    										 .orElse(new ArrayList<>()); //없으면 기본값
							    	errorMessages.add(errorMessage); //그런담에 넣어주기 
							    	errors.put(propertyName, errorMessages); //errors안에 propertyName와 errorMessages넣기 
							    });
		}
		
		
		return valid;
	}
	
	
	
	
}
