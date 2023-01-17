package kr.or.ddit.auth;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;


//현재 사용자가 인증된 사용자일때 pricipal 사용 - session에 auth member가 있을 경우
public class GeneratePrincipalFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		if(authMember!=null) { //인증된 사용자일 경우
			//존재하지 않던 principal 만들기
			//MemberVOWrapper wrapper = new MemberVOWrapper(authMember);//authMember는 이제부터 principal 안에 들어있는 게 됨 
			
			//wrapper을 request에 넣어주기
			HttpServletRequest modifedReq = new HttpServletRequestWrapper(req) {
				@Override
				public Principal getUserPrincipal() {
					HttpServletRequest adaptee = (HttpServletRequest)getRequest();
					MemberVO realMember = (MemberVO) adaptee.getSession().getAttribute("authMember");
					return new MemberVOWrapper(realMember);
				}
			};
			chain.doFilter(modifedReq, response);//다음 필터
			
		}else { //일반유저일 경우 
			chain.doFilter(request, response); //아무것도 안하고 통과시키면됨
		}
		 
	}

	@Override
	public void destroy() {
		
	}

}
