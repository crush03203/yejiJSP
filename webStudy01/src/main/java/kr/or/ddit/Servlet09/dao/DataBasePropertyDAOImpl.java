package kr.or.ddit.Servlet09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements DataBasePropertyDAO {

   
   @Override
   public List<DataBasePropertyVO> selectPropertyList(String propertyName) {
      StringBuffer sql = new StringBuffer();
      sql.append("select property_name, property_value, description ");
      sql.append("from database_properties ");
      if(StringUtils.isNotBlank(propertyName)) {
         sql.append(" WHERE PROPERTY_NAME = ? ");
         }

      
      try(
            
         Connection conn = ConnectionFactory.getConnection(); //ConnectionFacotyr에 있는 계정정보 가져오기    
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            
       //쿼리 객체 생성
//         Statement stmt =  conn.createStatement();   
      ) {
         //쿼리 실행 
//       ResultSet rs = stmt.executeQuery(sql.toString()); // 스트링버퍼를 문자열로 바꿔서 실행 
        if(StringUtils.isNotBlank(propertyName)) {
           pstmt.setString(1, propertyName);
        }
         
        ResultSet rs = pstmt.executeQuery();
        List<DataBasePropertyVO> list = new ArrayList<>();
         //vo가져오기
         while(rs.next()) {
            DataBasePropertyVO vo = new DataBasePropertyVO();
            list.add(vo);
            
            vo.setPropertyName(rs.getString("property_name"));
            vo.setPropertyValue(rs.getString("property_value"));
            vo.setDescription(rs.getString("description"));
         }   
         return list;
      }catch(SQLException e) {
         throw new RuntimeException(e);
      }
      
   }
   
   
}