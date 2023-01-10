package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;



//D:\contents\movies\target.mp4


public class StreamCopyTest {
	
	private File targetFile; // target.mp4 불러오기
	private File destFile;
	
	@Before
	public void setUp() {
		targetFile = new File("D:/contents/movies/target.mp4");
		destFile =  new File("d:/target.mp4");
	}
	
	
	
	@Test
	//1kb 읽어들임 (2~4초 소요시간 걸림)
	public void copyTest1() throws IOException {
//		FileInputStream vs FileReader
		

		try(
			//이걸 try괄호안에 넣으면 input, output close해줄 필요 없음 	
			FileInputStream fis = new FileInputStream(targetFile); 
			FileOutputStream fos = new FileOutputStream(destFile);
		){
			int tmp = -1;// 파일이 없는상태	
			//fis.read() => 1바이트 반환돼서tmp에 넣어둠
			while((tmp=fis.read()) != -1) {
				fos.write(tmp);
			}
		}

		
	}
	
	
//	@Test	
	//0.0001초 => buffer사용하면 소요시간 엄청 짧아짐
	public void copyTest2() throws IOException {

		try(
			FileInputStream fis = new FileInputStream(targetFile); 
			FileOutputStream fos = new FileOutputStream(destFile);
		){

			//1바이트가 1024개가 모이면 1KB
			//1024까지만 읽을 수 있음 => 오류 안내려면 1024까지 읽은 후 (buffer, 0, length)이런식으로 범위를 설정해줘야 오류 안남
			byte[] buffer = new byte[1024]; //우리가 직접 만든 버퍼
			int length = -1;	
			int count = 1;
			while((length=fis.read(buffer)) != -1) {//다 읽을때까지 반복 
				if(count++ == 1) {
					Arrays.fill(buffer, (byte)0); 
				}
				fos.write(buffer, 0, length);//영상의 앞부분이 잘림: 버퍼를 내가 가지고 있으면 내용을 내가 임의로 바꿀수 있음 
			}
		}
	}
	

	@Test
	public void copyTest3() throws IOException {

		try(
			FileInputStream fis = new FileInputStream(targetFile); 
			FileOutputStream fos = new FileOutputStream(destFile);
				
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
	
				
		){
			int tmp = -1;	
			while((tmp=bis.read()) != -1) {
				bos.write(tmp); //bos에 tmp기록
			}
		}

		
	}
	

	
	//@Test
	public void test() throws IOException {
		
		
		//똑같은 파일을 해당 폴더에 붙여넣기
		FileInputStream input = new FileInputStream(targetFile); // 파일 받아와서 (어디에 있는 어떤 파일 받아올 것인지) 
		FileOutputStream output = new FileOutputStream(destFile); // 출력할 곳 지정
		
		
		byte[] buf = new byte[1];
		
		int readData;
		while((readData = input.read(buf)) > 0) {
			output.write(buf,0,readData);
		}
		
		
		// 생성된 InputStream Object를 닫아준다.
		input.close();
		output.close();
		
		

		
		
		
		
		
	}
}
