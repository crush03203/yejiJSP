package kr.or.ddit.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemoVO;

/**
 * 
 * alt + shift + j
 * 
 * Serialization(직렬화)
 * 	: 전송이나 저장을 위해 객체의 상태를 바이트 배열로 변환하는 과정 
 *
 *
 * 객체의 상태를 통으로 저장한다 - 직렬화
 * 객체의 상태를 통으로 복원한다 - 역직렬화
 *
 */
public class SerializationTest {
	
//	private Map<String, Object> writeData;
	private MemoVO writeData;
	private File writeFile;
	
	
	@Before
	public void setUp() {
/*		writeData = new HashMap<>();
		writeData.put("code1", new Integer(23));
		writeData.put("code2", "TEXT");
		writeData.put("code3", new Boolean(true));*/
		writeData = new MemoVO();
		writeData.setCode(1);
		writeData.setWriter("작성자");
		writeData.setContent("내용");

		/*2022-12-14 17:20:00 출력*/
		//1$ => 첫번째 argument
		String date = String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS", LocalDateTime.now());  
		writeData.setDate(date);
		
		
		writeFile = new File("d:/sample.dat");
		
	}
	



//	@Test
	//직렬화 
	public void serializeTest() throws IOException {
		
		try(
			FileOutputStream fos = new FileOutputStream(writeFile); 
			ObjectOutputStream oos = new ObjectOutputStream(fos); //직렬화
		) {
			oos.writeObject(writeData);
		} 

	}
	
	
	
	@Test
	//역직렬화 : 우리가 읽을 수 있는 걸로 바꿔줌
	public void deSerializeTest() throws IOException, ClassNotFoundException {
		try(
			FileInputStream fis = new FileInputStream(writeFile); //1차스트림 
			ObjectInputStream ois = new ObjectInputStream(fis); //2차스트림
		){
//			Map<String, Object> map =  (Map<String, Object>) ois.readObject();
			MemoVO memo = (MemoVO) ois.readObject();
			System.out.println(memo);
		}
	}

	


}
