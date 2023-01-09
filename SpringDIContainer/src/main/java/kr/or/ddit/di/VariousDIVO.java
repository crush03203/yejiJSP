package kr.or.ddit.di;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariousDIVO {
	private int number;
	private boolean flag;
	private double db1Number;
	private char ch;
	private String str;
	
	private File fileSystemFile;
	private File classPathFile;
	
	public void init() {
		log.info("{} 객체 초기화 완료", getClass().getSimpleName());
	}
	public void destory() {
		log.info("{} 객체 초기화 완료", getClass().getSimpleName());
	}
}
