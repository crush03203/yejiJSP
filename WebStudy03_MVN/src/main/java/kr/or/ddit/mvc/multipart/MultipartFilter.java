package kr.or.ddit.mvc.multipart;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Multipart request를 request wrapper(MultipartHttpServletRequest)로 변경하는 필터
 *
 */
public class MultipartFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		1. 현재 요청이 Multipart request 인지 판단(바디가 있어야하함(어케?컨텐츠)
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		if(contentType != null&&contentType.startsWith("multipart/form-data")) {
			HttpServletRequest modifiedReq = new MultipartHttpServletRequest(req);
			chain.doFilter(modifiedReq, response);
		} else {
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
