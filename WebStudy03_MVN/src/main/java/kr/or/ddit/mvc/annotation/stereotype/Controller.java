package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




/**
 * Marker annotaion 형태로 Front controller 다음에서 동작한 command handler 객체 표현 ㅇ
 * (표현만 할 뿐이지 실제로 가지고있는거 없음 )
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
	
	

}
