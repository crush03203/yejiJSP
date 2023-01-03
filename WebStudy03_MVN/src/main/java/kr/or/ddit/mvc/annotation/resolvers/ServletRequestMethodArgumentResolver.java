package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link HttpServletRequest}, {@link HttpSession} 타입의 핸들러 메소드 인자 해결.
 */
public class ServletRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

   //현재 파라미터 타입이 resp인지 req인지 확인
   //내가 만들수있는 파라미터 결정하기 
	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		boolean support = HttpServletRequest.class.equals(parameterType)
							||
						  HttpSession.class.equals(parameterType);	
		return support;
	}
	 //결정된 파라미터를 여기서는 실제로 만들어주는 역할 
	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object argumentObject = null;
		if(HttpServletRequest.class.equals(parameterType)) {
			argumentObject = req;
		}else {
			argumentObject = req.getSession(); //req에서 session을 꺼내기
		}
		return argumentObject;
	}

}




















