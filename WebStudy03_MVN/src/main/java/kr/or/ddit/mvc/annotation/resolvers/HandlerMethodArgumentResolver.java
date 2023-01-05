package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerAdapter가 핸들러 메소드를 호출할 때,
 * 메소드 인자 하나하나를 따로따로 처리하기 위한 전략 객체  
 *
 */

public interface HandlerMethodArgumentResolver {
	
	
	/**
	 * 현재 파라미터가 resolver에 의해 처리가능한 경우인지 판단
	 * @param parameter 
	 * @return true반환시 처리가능. false 반환시 처리 불가능 
	 */
	public boolean supportsParameter(Parameter parameter);//내가 처리해야 할 객체가 resp인지 req인지 session인지 구분하기 위한 객체
	
	
	
	/**
	 * 핸들러 메소드의 인자 하나를 처리(생성)하기 위한 메소드
	 * @param parameter
	 * @param req
	 * @param resp
	 * @return
	 */
	public Object resolveArgument(Parameter parameter, HttpServletRequest req,HttpServletResponse resp)
		throws ServletException, IOException; //resp인지 req인지 session인지  모르니까 Object타입으로 받기! 
	
	
	
}
