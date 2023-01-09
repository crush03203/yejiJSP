package kr.or.ddit.di;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectionDITestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/di/conf/CollectionDI-Context.xml");
		context.registerShutdownHook();
		
		CollectionDIVO vo1 = context.getBean("colVO1", CollectionDIVO.class);
		log.info("주입된 객체 : {}", vo1);
		CollectionDIVO vo2 = context.getBean("colVO2", CollectionDIVO.class);
		log.info("주입된 객체 : {}", vo2);
		CollectionDIVO vo3 = context.getBean("colVO3", CollectionDIVO.class);
		log.info("주입된 객체 : {}", vo3);
		CollectionDIVO vo4 = context.getBean("colVO4", CollectionDIVO.class);
		log.info("주입된 객체 : {}", vo4);
	}
}
