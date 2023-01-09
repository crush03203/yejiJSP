package kr.or.ddit.hierarchy;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class ContextHierarchyTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = 
				new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/Parent-Context.xml");
		ConfigurableApplicationContext child = 
				new ClassPathXmlApplicationContext(
						new String[] {"kr/or/ddit/sample/conf/Sample-context.xml"}
						, parent
						
				);
		child.registerShutdownHook();
		parent.registerShutdownHook();
		
		SampleService service = child.getBean(SampleService.class);
		System.out.println(service.retrieveInformation("PK_2"));
		
		Map<String, String> oracleDB= (Map) child.getBean("oracleDB");
		System.out.println(oracleDB);

	
		SampleService service2 = parent.getBean(SampleService.class);
	
	}
}
