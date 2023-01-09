package kr.or.ddit.sample.dao;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_Oracle implements SampleDAO {
	
	public void init1() {
		log.info("{} 객체 초기화",getClass().getSimpleName());
	}
	public void destory2() {
		log.info("{} 객체 소멸",getClass().getSimpleName());
	}
	
	private Map<String, String> dummyDB;
	
	public SampleDAOImpl_Oracle( Map<String, String> dummyDB) {
		super();
		log.info("{} 객체 생성, 생성자 주입으로 dummyDB", getClass().getSimpleName());
		this.dummyDB = dummyDB;
//		dummyDB = new HashMap<>();
//		int idx = 0;
//		dummyDB.put("PK_"+ ++idx, "oracle 레코드 "+idx);
//		dummyDB.put("PK_"+ ++idx, "oracle 레코드 "+idx);
//		dummyDB.put("PK_"+ ++idx, "oracle 레코드 "+idx);
	}

	@Override
	public String selectRawData(String primaryKey) {
		return dummyDB.get(primaryKey);
	}

}

















