package kr.or.ddit.mvc.annotation;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * immutable 객체 형태로 값을 변경하지 않음 
 *
 */


@Getter //getter만 있고 setter가 없다는 의미는 => 한번생성한 후에 절대 값들을 바꾸지 않겠다 =>immutable 
@EqualsAndHashCode  //url와 method가 반드시 같아야함 
public class RequestMappingCondition {

	private String url;
	private RequestMethod method;
	
	//생성자
	public RequestMappingCondition(String url, RequestMethod method) {
		super();
		this.url = url;
		this.method = method;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s[%s]", url, method);
	}
	
	
	
}
