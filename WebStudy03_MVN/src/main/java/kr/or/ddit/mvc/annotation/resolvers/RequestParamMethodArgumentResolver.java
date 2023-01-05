package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;



/**
 * @RequestParam을 가지고 있으며, 기본형 타입인 인자를 해결  
 *
 */
public class RequestParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		RequestParam requestParam =  parameter.getAnnotation(RequestParam.class);
		boolean support = requestParam != null//requestParam를 안가지고있으면 null 가지고있으면 null이 아님 
				          && 
				          (
				        	parameterType.isPrimitive()
				        	||
				        	String.class.equals(parameterType)
				        	||
				        	(parameterType.isArray() && (
				        									parameterType.getComponentType().isPrimitive() 
				        									|| 
				        									parameterType.getComponentType().equals(String.class) 
				        								)
				        	)
				          );
		
		return support;  //requestParam를 가지고있을때만 처리하겠다
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		RequestParam requestParam =  parameter.getAnnotation(RequestParam.class);
		
		
		String requestParameterName =  requestParam.value();
		boolean required = requestParam.required();
		String defaultValue = requestParam.defaultValue();
		
		String[] requestParameterValues = req.getParameterValues(requestParameterName);
		if(required && ( requestParameterValues==null  //꼭 있어야 하는 파라미터이면서 +  요청파라미터가 안넘아왔다면 400에러 
						|| requestParameterValues.length==0
						|| StringUtils.isBlank(requestParameterValues[0]) //문자열이 비어있는지 확인 (공백도 )
		)) {
			//resp.sendError(400);
			throw new BadRequestException(requestParameterName + "이름의 필수 파라미터 누락");
		}
		if(requestParameterValues==null || requestParameterValues.length==0 || StringUtils.isBlank(requestParameterValues[0])) {
			requestParameterValues = new String[] {defaultValue};
		}
		
		Object argumentObject = null;
		if(parameterType.isArray()) {
			Object[] argumentObjects = new Object[requestParameterValues.length];
			for(int i=0; i<argumentObjects.length; i++) {
				argumentObjects[i] = singleValueGenerate(parameterType.getComponentType(), requestParameterValues[i]);
			}
			argumentObject = argumentObjects;
		}else {
			argumentObject = singleValueGenerate(parameterType, requestParameterValues[0]);
		}
		return argumentObject;
	}
	
	private Object singleValueGenerate(Class<?> singleValueType, String requestParameter) {
		Object singleValue = null;
		
		if(int.class.equals(singleValueType)) {
			singleValue = Integer.parseInt(requestParameter);
		}else if(boolean.class.equals(singleValueType)) {
			singleValue = Boolean.parseBoolean(requestParameter); //파싱해서 boolean만들어내기 
		}else {
			singleValue = requestParameter;
		}
		return singleValue;
	}
	
	
	public static class BadRequestException extends RuntimeException{

		public BadRequestException(String message) {
			super(message);
			
		}
		
	}

}
