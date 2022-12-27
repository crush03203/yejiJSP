package kr.or.ddit.member.dao;

import java.beans.Statement;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl extends AbstractJDBCDAO implements MemberDAO {
	
	private Map<String, Statement> statementMap;

   @Override
   public int insertMember(MemberVO member) {
      // TODO Auto-generated method stub
      return 0;
   }


   @Override
   public List<MemberVO> selectMemberList() {
      StringBuffer sql = new StringBuffer(); //f6 디버깅 다음으로 f8 다음 브레이크포인트로 이동
      sql.append(" SELECT  mem_id, MEM_NAME, MEM_MAIL, MEM_HP,  MEM_ADD1, MEM_ADD2, MEM_MILEAGE ");
      sql.append(" FROM   member                        ");
      
      return selectList(sql.toString(), MemberVO.class); //위에있는 resultBindingForOneRecord,queryParameterSetting메소드 사용해서 가져오기 
   
   }

   @Override
   public MemberVO selectMember(String memId) {
      
      StringBuffer sql = new StringBuffer();
      
      sql.append("SELECT    ");
       sql.append("mem_id, mem_pass,  mem_name,                                 ");
       sql.append("mem_regno1,mem_regno2,                                       "); 
       sql.append("to_char(mem_bir, 'YYYY-MM-DD') mem_bir,                      ");
       sql.append("mem_zip, mem_add1,  mem_add2,                                ");
       sql.append("mem_hometel, mem_comtel,  mem_hp,                            ");
       sql.append("mem_mail, mem_job, mem_like,                                 ");
       sql.append("mem_memorial,                                                ");
       sql.append("to_char(mem_memorialday, 'YYYY-MM-DD') mem_memorialday,      ");
       sql.append("mem_mileage,  mem_delete                                     ");
      sql.append("FROM                                                         ");
      sql.append("    member                                                   ");
      sql.append("where mem_id = ?                                             ");
                            
      return selectOne(sql.toString(), MemberVO.class, memId); //sql을 toString형식으로 넘기고, MemberVO와 memID를 넘겨줌 
      
   }

   @Override
   public int updateMember(MemberVO member) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int deleteMember(String memId) {
      // TODO Auto-generated method stub
      return 0;
   }

}