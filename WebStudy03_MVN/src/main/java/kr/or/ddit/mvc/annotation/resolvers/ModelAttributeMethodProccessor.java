package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.apache.commons.text.WordUtils;



/**
 * @ModelAttribute 어노테이션을 가진 command object(=> 기본형이 아니어야함) 인자 하나를 해결 
 * ex) @ModelAttribute MemberVO member (O);
 * 	   @ModelAttribute int cp(X);
 *
 */

public class ModelAttributeMethodProccessor implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		
		Class<?> parameterType = parameter.getType();
		ModelAttribute modelAttribute = parameter.getAnnotation(ModelAttribute.class);
		boolean support = modelAttribute != null
						&& 
						!(
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
		
		return support;
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Class<?> parameterType = parameter.getType();
		ModelAttribute modelAttribute = parameter.getAnnotation(ModelAttribute.class);
		
		try {
			//MemberVO member = new MemberVO(); 
			Object commandObject = parameterType.newInstance();

//			req.setAttribute("member", member); 
			String attrName = modelAttribute.value();
			//Coc(Convention over Configuration) : 명시적인 설정이 있으면 이름 그대로 쓰는데 명시적 설정 없으면 첫글자를 소문자로 해서 뭐시기 어쩌고 
			if(StringUtils.isBlank(attrName)) {
				//이름이 없을때 이방식대로 진행 
				attrName = WordUtils.uncapitalize(parameterType.getSimpleName());
				parameterType.getSimpleName();
			}
			req.setAttribute(attrName, commandObject);
			
	
	
	//		Map<String, String[]> parameterMap =  req.getParameterMap();
	//		try {
	//			BeanUtils.populate(member, parameterMap);
	//		}catch(IllegalAccessException | InvocationTargetException e) {
	//			throw new ServletException(e);
	//		}
			BeanUtils.populate(commandObject, req.getParameterMap());
			
			return commandObject;
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
