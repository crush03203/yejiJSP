package kr.or.ddit.commons.wrapper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CookieHttpServletRequestWrapper extends HttpServletRequestWrapper{ //HttpServletRequestWrapper를 상속받으면  adater는 adaptee가 없으면 존재할 수 없음 => adaptee로 request를 받아옴

	private Map<String, Cookie> cookieMap; //우리가 사용하는 el객체는 이걸 뜻함 
	
	public CookieHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		cookieMap = new HashMap<>(); //배열안에 있는 쿠키들을 꺼내서 cookieMap에 넣어주기 
		Cookie[] cookies =  request.getCookies();
		if(cookies != null) {
			for(Cookie tmp : cookies ) { //tmp들을 cookieMap에 차례로 넣어주면 됨 
				cookieMap.put(tmp.getName(), tmp); //HashMap에 넣어주기 => 이름key주면 쿠키를 가져오게됨 
			}
		}	
	}

	//Map에서 쿠키찾기
	public Cookie getCookie(String name) { 
		return cookieMap.get(name); //"이름"으로 쿠키 찾을 수 이씅ㅁ ㅇ
	}
	

	public String  getCookieValue(String name) {
		Cookie finded = getCookie(name);
		return Optional.ofNullable(finded)  //finded는 null일수도 있기 때문에 of Nullable
				.map(cookie->{
					try {
						return URLDecoder.decode(cookie.getValue(), "UTF-8"); //디코딩해서 cooke값 리턴하기 
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
				})
				.orElse(null);
		
		//쿠키값이 있으면 쿠키의 값이 반환되고 없으면 null값이 반환됨 
				
		
		
		
	}
	
	
	
}
