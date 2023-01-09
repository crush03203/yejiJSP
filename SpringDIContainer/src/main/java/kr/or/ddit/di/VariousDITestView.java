package kr.or.ddit.di;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VariousDITestView {
	public static void main(String[] args) {
//      1. 컨테이너 객체 생성
//      2. 자동으로 소멸될 수 있는 구조도 필요함
//      3. 컨테이너 모든 객체들은 lifecyle 콜백을 가지고 있어야 함.
//			객체 소멸되었다는 로그 확인.
//      4. 등록한 빈을 메인 메소드에서 받아서 property상태를 알 수 있도록 로그를 출력함.
	ConfigurableApplicationContext context = 
			new ClassPathXmlApplicationContext("kr/or/ddit/di/conf/VariousDI-Context.xml");
	context.registerShutdownHook();
	
	VariousDIVO vo1 = context.getBean("vo1",VariousDIVO.class);
	VariousDIVO vo2 = context.getBean("vo2",VariousDIVO.class);
	log.info("주입된 객체 : {} ", vo1);
	log.info("주입된 객체 : {} ", vo2);
	}
}
