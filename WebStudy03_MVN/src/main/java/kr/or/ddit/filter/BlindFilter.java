package kr.or.ddit.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlindFilter implements Filter {
	
	private Map<String, String> blindMap; //blindMap을 선언해 줌

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", this.getClass().getName());
		
		blindMap = new LinkedHashMap<>();
		blindMap.put("127.0.0.1","나니까 블라인드");
		blindMap.put("0:0:0:0:0:0:0:1","나니까 블라인드");
		blindMap.put("192.168.35.32","나니까 블라인드"); //내 ip
		blindMap.put("192.168.35.13","알리미니까 블라인드"); //예지ip

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("blind filter 동작시작");
		
	    /*  
	    // 1클라이언트 ip 주소 필요
      	// 2ip 주소를 기준으로 대상자인지 확인
      	// 3대상자가 아니라면 서비스 이용
      	// 대상자라면 블라인드 사유를 알려주면서 블라인드된 메세지 띄우기
	    *
	    */
		
		String ipAddress = request.getRemoteAddr(); //client ip 필요
		if(blindMap.containsKey(ipAddress)) { //블라인드 대상자일경우 
			
			String reason = blindMap.get(ipAddress);
			String message = String.format("당신은 %s 사유로 블라인드 처리 됐습니다.", reason);
			request.setAttribute("message", message);
			
			String viewName = "/WEB-INF/views/commons/messageView.jsp";
			request.getRequestDispatcher(viewName).forward(request, response);
			
		}else { //블라인드 대상자가 아님
			chain.doFilter(request, response);
		}

		log.info("blind filter 동작종료");
	}

	@Override
	public void destroy() {
		log.info("{} 소멸", this.getClass().getName());
		
	}
}
