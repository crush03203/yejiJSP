package kr.or.ddit.auth;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;


/**
 * 1) 보호자원에 대한 요청인 경우,
 * 2) 자원에 설정된 역할 정보와 사용자에게 부여된 역할 정보가 일치할때 접근 허용 
 * 자원에 설정된 역할 정보가 ROLE_USER일 경우  사용자에게 부여된 역할 정보도 ROLE_USER여야 함 
 *
 */
public class AuthorizationFilter implements Filter{

	private ServletContext application;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		application = filterConfig.getServletContext();
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Map<String, String[]> securedResources =  (Map)application.getAttribute(AuthenticationFilter.SECUREDNAME);
		
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpSession session = req.getSession();
		
		boolean pass = true;
		
		String uri = req.getServletPath();
		if(securedResources.containsKey(uri)) { //보호자원일 경우
			
			String[] resRoles = securedResources.get(uri); // 여러개 role을 가지고 있을 경우
			
			//MemberVO authMember = (MemberVO) session.getAttribute("authMember");
			MemberVOWrapper principal = (MemberVOWrapper) req.getUserPrincipal();
			MemberVO authMember = principal.getRealMember(); //세션없어도 순수하게 req만으도 인증된 사람인지 아닌지 알수있음 
			
			String memRole = authMember.getMemRole();
			pass = Arrays.stream(resRoles)
				  .anyMatch(ele->ele.equals(memRole)); //allMatch : 모든게 맞는지 확인  / anyMatch: 어느 하나라도 맞는지 확인
		}
		
		if(pass) { //통과
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "권한 없는 자원에 대한 요청"); //403 오류
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
