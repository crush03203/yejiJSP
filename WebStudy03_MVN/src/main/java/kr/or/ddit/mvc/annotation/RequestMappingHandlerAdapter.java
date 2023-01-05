package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeMethodProccessor;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.RequestPartMethodArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.mvc.annotation.resolvers.ServletRequestMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletResponseMethodArumentResolver;


@Slf4j
public class RequestMappingHandlerAdapter implements HandlerAdapter {

	
	private List<HandlerMethodArgumentResolver> argumentResolvers;
	
	{
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletRequestMethodArgumentResolver());
		argumentResolvers.add(new ServletResponseMethodArumentResolver());
		argumentResolvers.add(new RequestParamMethodArgumentResolver());
		argumentResolvers.add(new ModelAttributeMethodProccessor());
		argumentResolvers.add(new RequestPartMethodArgumentResolver());
		
	}
	

	
	private HandlerMethodArgumentResolver findArgumentResolver(Parameter param) {
		HandlerMethodArgumentResolver finded = null;
		for(HandlerMethodArgumentResolver resolver : argumentResolvers) {
			if(resolver.supportsParameter(param)) { 
				finded = resolver;
				break; //남은 반복문 돌릴필요 없음 빠져나가면됨 
			}
		}
		
		return finded;
	}
	
	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
        Object handlerObject = mappingInfo.getCommandHandler();
 		Method handlerMethod = mappingInfo.getHandlerMethod(); 
 		
 		int parameterCount = handlerMethod.getParameterCount();//이제는 무조건 req, resp 두개 반드시 던져야 할 필요 없음 
 		
 		try {
 	 		//String viewName  = (String) handlerMethod.invoke(handlerObject, req, resp);//이제는 무조건 req, resp 두개 반드시 던져야 할 필요 없음 
 			String viewName  = null;
 			if(parameterCount > 0) { //파라미터 값이 있을 경우 
 				Parameter[] parameters = handlerMethod.getParameters();
 				Object[] arguments = new Object[parameterCount];
 				
 				//한개한개 파라미터 만들어내기 
 				for(int i=0; i<parameterCount; i++) {
 					Parameter param = parameters[i];
 					HandlerMethodArgumentResolver findedResolver = findArgumentResolver(param);
 					if(findedResolver==null) {
 						throw new RuntimeException(String.format("%s 타입의 메소드 인자는 현재 처리 가능한 resolver가 없음. ", param.getType()));
 					}else {
 						arguments[i] = findedResolver.resolveArgument(param, req, resp);
 						//for문을 다 돌고 난뒤 arguments는 필요한 인자를 다 가지고있게됨 
 					}
 					
 				}
 				viewName = (String) handlerMethod.invoke(handlerObject, arguments);

 				
 			}else { //파라미터가 한개도 없을 경우 resp, req 둘다 없을 경우
 				viewName  = (String) handlerMethod.invoke(handlerObject);
 			}
 			
 			return viewName;
		}catch(BadRequestException e) {
			log.error("handler adapter가 handler method argument resolver 사용 중 문제 발생",e);
			resp.sendError(400, e.getMessage());
			return null;
		} catch (Exception e) {
			throw new ServletException(e); //e : 기존의 예외정보 반드시 던져줘야됨 
		}

		
	}



}
