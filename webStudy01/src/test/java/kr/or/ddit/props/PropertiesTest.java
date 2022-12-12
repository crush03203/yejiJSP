package kr.or.ddit.props;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

import kr.or.ddit.servlet01.DescriptionServlet;

public class PropertiesTest {
	
	
//	[데이터를 입력스트림으로 읽는 절차]
//	1. 매체(미디어)를 객체화 ex) File, Socket, DataSource
//	2. 입력 스트림 개방, 매체에 연결 ex) new FileInputStream(file)
//	3. 데이터의 마지막(EOF, EOS => -1, null)까지 반복적인 읽기 작업
//	4. 개방된 스트림 종료 (매체를 해제시켜 다른 쓰레드가 사용할 수 있도록 함)


//	@Test
	public void classLoaderTest() throws ClassNotFoundException, IOException {
		
		
		//*********1번단계 *********
//		A a = new A();
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Class<?> clz = loader.loadClass("kr.or.ddit.servlet01.DescriptionServlet");
		System.out.println(DescriptionServlet.class);
		System.out.println(DescriptionServlet.class.equals(clz));// true => 같은클래스 같은 객체
//		URL resourceURL =  loader.getResource("kr/or/ddit/props/DataStore.properties");
//		URL resourceURL = DescriptionServlet.class.getResource("/kr/or/ddit/props/DataStore.properties");
		URL resourceURL = DescriptionServlet.class.getResource("../props/DataStore.properties");
		File file = new File(resourceURL.getFile());
		System.out.println(file.getCanonicalPath()); // 전체경로 확인 
		

		try(
			//*********2번단계 *********
//			FileInputStream fis = new FileInputStream(file); //byte 단위 스트림 개방  	
			FileReader fr = new FileReader(file); //char 단위 스트림 개방 	
		) {
			
			//*********3번단계 *********
			int tmp = -1; //-1 => the end of the stream has beenreached
			while((tmp = fr.read()) != 1) {
				System.out.write(tmp);
			}
		}
		
		
		//*********4번단계 *********
//		fr.close(); 이거 대신 상단 try 구문사용
		
		
	}
	

//	@Test
	public void test() throws IOException {
		System.out.println("Test Case");
		
//		properties 파일 읽는 방법
//		1. Properties
		Properties properties = new Properties();
		try (
			InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/props/DataStore.properties");	
		){
			properties.load(is);
			System.out.println(properties.getProperty("prop1"));
			Enumeration<Object> en = properties.keys();
			while (en.hasMoreElements()) {
				Object key = (Object) en.nextElement();
				Object value = properties.get(key);
				System.out.printf("%s : %s\n", key, value);
			}
		}
	}
	

	
	//	kr.or.ddit.props > Message_ko.properties /  kr.or.ddit.props >  Message_en.properties
	@Test
	public void testResourceBundle() {
		//2. ResourceBundle
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
		System.out.println(bundle.getString("prop1"));
	}
	
	
	
	
	
	

}
