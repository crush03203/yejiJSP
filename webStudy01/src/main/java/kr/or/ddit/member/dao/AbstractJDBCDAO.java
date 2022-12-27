package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.CaseUtils;

import kr.or.ddit.db.ConnectionFactory;

public abstract class AbstractJDBCDAO {
   
   
   
   public <T> T selectOne(String sql, Class<T> resultType, Object...params) {
      try(
            //1단계
            Connection conn = makeConnection();   
            //2단계
            PreparedStatement pstmt =  makePreparedStatement(conn, sql); //sql실행하려면 파라미터로 sql문 받아오기 
      ) {
         //3단계 - 다 다른형태로 받고있음 ? 형태로
         queryParameterSetting(pstmt, params);
         ResultSet rs = exeuteQuery(pstmt);
         T resultObject = null;
         while(rs.next()) {
            resultObject = resultBindingForOneRecord(rs, resultType); //rs를 레코드 하나를 바인딩 시켜서 
         }
         return resultObject; //레코드 하나에 대한 객체
      }catch(SQLException e) {
         throw new RuntimeException(e);
      }
   }
   
   
   //여러 레코드에 대한 리스트 객체
   //후크 메소드들의 순서 정하기 
   public <T> List<T> selectList(String sql, Class<T> resultType, Object...params ) { // Class<T> resultType여기로 받은 type을 List<T>여기로 들어간다. => 제너릭 

      try(
            //1단계
            Connection conn = makeConnection();   
            //2단계
            PreparedStatement pstmt =  makePreparedStatement(conn, sql); //sql실행하려면 파라미터로 sql문 받아오기 
      ) {
         //3단계 - 다 다른형태로 받고있음 ? 형태로
         queryParameterSetting(pstmt, params);
         ResultSet rs = exeuteQuery(pstmt);
         List<T> list = new ArrayList<>();
         while(rs.next()) {
            T recordObject = resultBindingForOneRecord(rs, resultType); //rs를 레코드 하나를 바인딩 시켜서 
            list.add(recordObject);
         }
         return list;
      }catch(SQLException e) {
         throw new RuntimeException(e);
      }
      
      
      
   }
   
   
   /////////////////////////////////


   //1. 커넥션 필요
   private Connection makeConnection() throws SQLException {
      return ConnectionFactory.getConnection();
   }
   
   //2. 쿼리 생성
   private PreparedStatement makePreparedStatement(Connection conn, String sql) throws SQLException { //preparedstatement 쓰려면 connection과 sql문 필요해서 파라미터로 받아옴
      return conn.prepareStatement(sql);
   }
   
   //3.  쿼리 실행
   private ResultSet exeuteQuery(PreparedStatement pstmt) throws SQLException {
      return pstmt.executeQuery();
   }

   ///////////////////////////////////////////////////////
   
   //쿼리 파라미터가 있을 경우 (sql문에서 where 절 있을 경우)
   public void queryParameterSetting(PreparedStatement pstmt, Object...params) throws SQLException {
      
      try {
         if(params.length>0) {//전달해야하는 파라미터가 1개 이상있을 경우
            for(int idx = 0; idx<params.length; idx++) {
               
               Object param = params[idx];
               if(param.getClass().equals(int.class)) {//숫자타입일 경우
                  pstmt.setInt(idx+1, (Integer)param);   
               }else { // 숫자외 다른 타입일 경우
                  pstmt.setString(idx+1, param.toString());   
               }
            
               
            }
         }
      }catch(Exception e) {
         throw new SQLException(e);
      }
      
   }; 
   
   
   public <T> T resultBindingForOneRecord(ResultSet rs, Class<T> resultType)  throws SQLException {
      
      
      try {
         T resultObject = resultType.newInstance();
         
         //조회된 컬럼을 resultObject 객체 안에 넣어주기 
         ResultSetMetaData rsmd = rs.getMetaData();
         int count =  rsmd.getColumnCount();
         for(int idx = 1; idx<=count ; idx++) { //조회된 컬럼 하나하나는
//            MEM_ID -> memId -> setMemId(rs.getString("MEM_id")) //reflection을 이용해서 이 소스 대신 적어줘야됨
//            MEM_MILEAGE -> memMileage -> setMileage(rs.getInt("MEM_MILEAGE")) //reflection을 이용해서 이 소스 대신 적어줘야됨
//            rsmd.getColumnName(3); //3번째 조회되고있는 컬럼의 이름이 무엇인가
            String columnName = rsmd.getColumnName(idx); //"MEM_id"
            String propertyName = CaseUtils.toCamelCase(columnName, false, '_');
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType); //전역변수에 대한 모든 정보를 가지고있음
            Method setter =  pd.getWriteMethod();
            
            
            //String or Integer 인지 구분하기 
            Class<?> propertyType = pd.getPropertyType();
            if(propertyType.equals(Integer.class)) {
               //Integer다?
               setter.invoke(resultObject, rs.getInt(columnName));
            }else {
               //String이다?
               setter.invoke(resultObject, rs.getString(columnName));
            }
            
         }
         return resultObject;
      }catch(Exception e) {
         throw new SQLException(); //발생한 예외를 한가지의 예외로 캡슐화 
      }
      
      
   };

   
   

   
}