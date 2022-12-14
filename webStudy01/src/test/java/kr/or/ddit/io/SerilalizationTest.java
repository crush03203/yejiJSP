package kr.or.ddit.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
 * Serialization(직렬화) : 전송이나 저장을 위해 객체의 상태를 바이트 배열로 변환하는 과정
 */
public class SerilalizationTest {
//	private Map<String, Object> writeData;
	private MemoVO writeData;
	private File writeFile;

	@Before
	public void setup() {
//		writeData = new HashMap<>();
//		writeData.put("code1", new Integer(23));
//		writeData.put("code2", "TEXT");
//		writeData.put("code3", new Boolean(true));
		
		writeData = new MemoVO();
		writeData.setCode(1);
		writeData.setWriter("작성자");
		writeData.setContent("내용");
//		2022-12-14 17:20:00
		String date = String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS",LocalDateTime.now());
		writeData.setDate(date);

		writeFile = new File("d:/sample.dat");
		// 어떤 형태여도 괜찮다 writeData에 writeFile를 읽어라
	}

//	@Test //직렬화
	public void serializeTest() throws IOException {
		try (FileOutputStream fos = new FileOutputStream(writeFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(writeData);
		}
	}

	@Test // 역직렬화
	public void deSerializeTest() throws  IOException, ClassNotFoundException {
		try(
			FileInputStream fis = new FileInputStream(writeFile); 
			ObjectInputStream ois = new ObjectInputStream(fis);
		){
//			Map<String, Object> map = (Map<String, Object>)ois.readObject();
			MemoVO memo = (MemoVO)ois.readObject();
			System.out.println(memo);
		}
	}
}
//