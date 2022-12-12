package kr.or.ddit.servlet04.service;

import java.io.InputStream;
import java.util.Properties;

import kr.or.ddit.servlet01.DescriptionServlet;

public class PropertiesServiceImpl implements PropertiesService {

	@Override
	public Properties retrieveData() {
		
		Properties properties = new Properties(); //properties 파일을 읽어오기 위한 객체 
	
		try (
			//kr/or/ddit/props/DataStore.properties 경로에 있는 properties 파일을 읽어들여서
			InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/props/DataStore.properties");	
		){
			//읽어들인 내용을 properties에다가 로드 시켜놔라  
			properties.load(is);
		
		}catch(Exception e) {
			throw new RuntimeException(e); 
		}
		
		return properties;
	}

}
