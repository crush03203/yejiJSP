package kr.or.ddit.mvc.annotation;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * immutable 객체 형태로 값을 변경하지 않음.
 *
 */
@Getter //한번 값을 저장한 후 값을 다신 바꾸지 않겠다
@EqualsAndHashCode
public class RequestMappingCondition {
	private String url;
	private RequestMethod method;
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
