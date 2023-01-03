package kr.or.ddit.mvc.annotation;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestMappingHandlerMapping implements HandlerMapping {
	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	public RequestMappingHandlerMapping(String...basePackages) {
		//여기서 최종적으로 Map<RequestMappingCondition, RequestMappinginfo> handlerMap 만들어야한다 
		handlerMap = new LinkedHashMap<>();
		scanBasePackages(basePackages);
	}

	private void scanBasePackages(String[] basePackages) {
		//제일 먼저 할 일/ 컨트롤러 어노테이션을 가지고 있는 것이 필요
		ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages)
			.forEach((handlerClass, controller)->{
				try {
					Object commandHandler = handlerClass.newInstance();
					//예와 발생하지 않았다면 정상적으로 핸들러 객체가 생성되고 있는 것
					ReflectionUtils.getMethodsWithAnnotationAtClass(
						handlerClass, RequestMapping.class, String.class
					).forEach((handlerMethod, requestMapping)->{
						RequestMappingCondition mappingCondition =
								new RequestMappingCondition(requestMapping.value(), requestMapping.method());
						RequestMappingInfo mappingInfo = 
								new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
						handlerMap.put(mappingCondition, mappingInfo);
						log.info("수집된 핸들러 정보 : {}", mappingInfo);
					});
				}catch (Exception e) {
					log.error("핸들러 클래스 스캔 중 문제 발생", e);
				}
			});
	}

	@Override
	public RequestMappingInfo findCommandHalder(HttpServletRequest request) {
		String url = request.getServletPath(); //디스패처 서블릿에서 책임을 덜어내는 중
		RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());
		RequestMappingCondition mappingCondition = 
					new RequestMappingCondition(url, method);
		log.info("mappingCondition : {}",mappingCondition);
		return handlerMap.get(mappingCondition);
	}

}
