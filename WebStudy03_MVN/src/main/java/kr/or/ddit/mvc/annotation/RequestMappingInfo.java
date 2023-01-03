package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import lombok.Getter; //수정할 수 없는 객체가 됨
import lombok.ToString;

@Getter
@ToString
public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
//이게 들어가서 기본생성자 사라짐
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
	
	
}
