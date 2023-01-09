package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class SampleView {
	public static void main(String[] args) {
//		SampleDAO dao = new SampleDAOImpl_Oracle();
//		SampleService service = new SampleServiceImpl(dao);
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("/kr/or/ddit/sample/conf/Sample-context.xml");
		SampleService service = context.getBean(SampleService.class);
		StringBuffer model = service.retrieveInformation("PK_2");
		System.out.println(model);
	}
}
