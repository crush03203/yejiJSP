package kr.or.ddit.marchalling;

import static org.junit.Assert.*;

import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class MarchallingXMLTest {
	
	Object target;
	ObjectMapper mapper;
	

	@Before
	public void setUp() throws Exception {
		System.out.println("before");
		
		target = new LinkedHashMap<>();
		((Map)target).put("key1", "SAMPLE");
		((Map)target).put("key2", new Boolean(true));
		((Map)target).put("key3", new Double(2.3d));
		mapper = new XmlMapper();
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("after");
	}

	@Test
	public void test1() throws JsonProcessingException {
		
//		1. native -> json : marshalling - writeXXX
		String json = mapper.writeValueAsString(target);
		System.out.println(json);
		
//		2. json -> native : unmarchalling - readXXX
		Map<String, Object> map =  mapper.readValue(json, Map.class);
		System.out.println(map);	
	}
	

	
	@Test
	public void test2() throws IOException {
		
//		1단계 : 마샬링 : native -> json : marshalling - writeXXX
//		String json = mapper.writeValueAsString(target); //마샬링!!!!!!!!!!!!!!!
//		1-1단계: serialization(직렬화) : 객체의 상태를 전송이나 저장이 가능한 형태로 스트림화(바이트 배열로 변환)하는 것
		File file = new File("d:/test.xml");
		try(
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
		){
//			bw.write(json); //직렬화!!!!!!!!!!!!!!!
			mapper.writeValue(bw, target); //마샬링과 직렬화를 한큐에 할 수 있는 소스 
			//마샬링의 대상이 되는 것: target
			
			
			//결과 : d드라이브에 test.json파일 생성되어있어야됨 
		}
		

		
//		2-2. deserialization(역직렬화) : 전송이나 저장된 매체로부터 객체의 상태를 복원하는 과정
		try(
			//Closable 객체(resource) 선언문만 여기 괄호안에 들어올수 있음
			FileReader reader = new FileReader(file); //저장된 파일 가져오기 
			BufferedReader br = new BufferedReader(reader);
				
		) {
			//역직렬화!!!!!!!!!!!!!!!
//			String temp = null;
//			StringBuffer readedJson = new StringBuffer(); //이미 읽혀진 json
//			while((temp = br.readLine()) != null) {
//				readedJson.append(temp);
//			}
			
//			2. json -> native : unmarchalling - readXXX
//			Map<String, Object> map =  mapper.readValue(readedJson.toString(), Map.class); //언마살링!!!!!!!!!!!!!!!
			Map<String, Object> map =   mapper.readValue(br, Map.class);//언마샬링과 역직렬화를 한큐에 할 수 있는 소스 
			System.out.println(map);			
			
		}


	}
	

	


}
