package kr.or.ddit.sample.dao;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
//DB 서버가 바껴서 그에 맞는 DAO를 구성함

@Slf4j
public class SampleDAOImpl_Postgre implements SampleDAO {

   public void init1() {
      log.info("{} 객체 초기화", getClass().getSimpleName());
   }
   
   public void destroy2() {
      log.info("{} 객체 소멸", getClass().getSimpleName());
   }   
   
   
   
   private Map<String, String> dummyDB;
   
   //기본 생성자
   public SampleDAOImpl_Postgre() {
      super();
      log.info("{} 객체 생성", getClass().getSimpleName());
//      dummyDB = new HashMap<>();
//      int idx = 0;
//      dummyDB.put("PK_" + ++idx, "postgreSQL 레코드 " + idx);
//      dummyDB.put("PK_" + ++idx, "postgreSQL 레코드 " + idx);
//      dummyDB.put("PK_" + ++idx, "postgreSQL 레코드 " + idx);
   }
   
   
   
   public void setDummyDB(Map<String, String> dummyDB) {
      this.dummyDB = dummyDB;
      log.info("dummyDB를 setter 주입함.");
   }

   
   
   
   @Override
   public String selectRawData(String primaryKey) {
      
      return dummyDB.get(primaryKey);
   }

}