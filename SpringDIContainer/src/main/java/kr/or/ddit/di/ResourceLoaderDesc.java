package kr.or.ddit.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceLoaderDesc {
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext();
		Resource resource1 = context.getResource("file:D:/contents/images/cat1.jag");
		Resource resource2 = context.getResource("classpath:log4j2.xml");
		Resource resource3 = context.getResource("https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		
		System.out.printf("file system resource : %s\n",resource1.getClass().getSimpleName());
		System.out.printf("class system resource : %s\n",resource2.getClass().getSimpleName());
		System.out.printf("web system resource : %s\n",resource3.getClass().getSimpleName());
		
		
	}
}
