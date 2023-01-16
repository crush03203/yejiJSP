package kr.or.ddit.auth;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 1) 보호 자원에 대한 요청인지,
 * 2) 신원확인(session scope안에 들어있는 authMember 속성)을 한 사용자인지 판단
 *  
 * 
 * 보호자원대한 요청O 신원확인O => 통과O
 * 보호자원에 대한요청 O 신원확인 X => 통과X
 * 일반요청 => 통과O
 *
 */


@Slf4j
public class AuthenticationFilter implements Filter {
	
	private Map<String, String[]> securedResources; //key value쌍을 securedRecousred안에 넣기  
													//=> String[] : ROLE_ADMIN,ROLE_USER
	public static final String SECUREDNAME ="securedResources";

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedResources = new LinkedHashMap<>();
		
		
		filterConfig.getServletContext().setAttribute(SECUREDNAME, securedResources);
		
		
		String filePath = filterConfig.getInitParameter("filePath"); // web.xml => <init-param>
		try(
			InputStream is = this.getClass().getResourceAsStream(filePath); // web.xml => <init-param>
		) {
			Properties props = new Properties(); //properties안에 있던 내용이 props에 담김
			props.load(is);
			props.keySet().stream()
						  .map(Object::toString)//Object가 가지고있는 
//						  .collect(Collectors.toList())
						  .forEach(key->{
							  String value = props.getProperty(key);
							  String[] values= Arrays.stream(value.split(","))
							  		.map(String::trim)
							  		.toArray(String[]::new);
							  securedResources.put(key, values); //key: uri / value: key에 대한 정보 배열
							  log.info("보호 자원[{} : {}]", key, securedResources.get(key));
						  });
			
		} catch (IOException e) {
			throw new ServletException(); //wrapper
		}
		
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//현재 클라이언트가 요구하는 자원이 무엇인가 ? HttpServletRequest request가 가지고있음 
		HttpServletRequest req = (HttpServletRequest) request; 
		HttpServletResponse resp = (HttpServletResponse) response; 
		
		String uri = req.getServletPath(); //HttpServletRequest req = (HttpServletRequest) request; 
		
		boolean pass = true;
		
		
		if(securedResources.containsKey(uri)) {//보호자원일경우
			Principal principal =   req.getUserPrincipal();
			if(principal==null) { //authMember가 없으면
				pass = false; //통과못함
			}
			//Object authMember = req.getSession().getAttribute("authMember");
			/*if(authMember==null) { //authMember가 없으면
				pass = false; //통과못함
			}*/
			
		}

		
		if(pass) { //pass가 true일 경우 => 통과 (보호자원대한 요청O + 신원확인O  or 일반요청)
			chain.doFilter(request, response); //통과
		}else { //pass가 false일 경우 => 통과 못함  (보호자원에 대한요청 O  + 신원확인 X) => 신원확인하러 보내기 => loginForm
			//loginform으로 이동, redirect 방식으로 
			String viewName =  req.getContextPath() + "/login/loginForm.jsp";
			resp.sendRedirect(viewName); //HttpServletResponse resp = (HttpServletResponse) response; 	
		}	
	}

	
	
	@Override
	public void destroy() {
		
	}

}
