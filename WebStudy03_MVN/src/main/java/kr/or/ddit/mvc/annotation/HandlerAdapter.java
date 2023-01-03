package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
	/**
	 * mappingInfo 에 포함된 핸들러 객체와 핸들러 메소드 정보를 기반으로 실제 핸들러를 호출하는 역할.
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String invokeHandler(
	//리시버랑 직접적인 연관성은 없음
			RequestMappingInfo mappingInfo
			, HttpServletRequest req
			, HttpServletResponse resp
	) throws ServletException, IOException;
}
