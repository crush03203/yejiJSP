package kr.or.ddit.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.MemoVO;

public class DataBaseMemoDaoImpl  implements MemoDAO{
	
	
	private DataBaseMemoDaoImpl() {
		super();
	}

	//싱글톤 - 메모리 공간을 절약하기 위해서 (객체풀링구조도 다른 방법중 하나가 될수도있음 )
	private static DataBaseMemoDaoImpl instance;
	public static DataBaseMemoDaoImpl getInstance() {
		if(instance==null)
			instance = new DataBaseMemoDaoImpl();
		return instance;
	}
	


	@Override
	public List<MemoVO> selectMemoList() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  code, writer, content, \"DATE\"  ");
		sql.append(" FROM   tbl_memo                        ");
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {

			ResultSet rs = pstmt.executeQuery();
			List<MemoVO> memoList = new ArrayList<>();
			while (rs.next()) {
				MemoVO memo = new MemoVO();
				memoList.add(memo);
				memo.setCode(rs.getInt("CODE"));
				memo.setWriter(rs.getString("WRITER"));
				memo.setContent(rs.getString("CONTENT"));
				memo.setDate(rs.getString("DATE"));
			}
			return memoList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


	@Override
	public int insertMemo(MemoVO memo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO tbl_memo (           ");
		sql.append(" 	 code,    writer,    content  ");
		sql.append(" )VALUES( MEMO_SEQ.NEXTVAL , ? , ?)");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int i = 1;
			pstmt.setString(i++, memo.getWriter());
			pstmt.setString(i++, memo.getContent());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE tbl_memo   ");
		sql.append(" SET               ");
		sql.append(" WRITER = ?,       ");
		sql.append(" CONTENT = ?       ");
		sql.append(" WHERE CODE = ?    ");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int i = 1;
			pstmt.setString(i++, memo.getWriter());
			pstmt.setString(i++, memo.getContent());
			pstmt.setInt(i++, memo.getCode());
			
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMemo(int code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM tbl_memo   ");
		sql.append(" WHERE CODE = ?    ");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int i = 1;
			pstmt.setInt(i++, code);
			
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	


	

}
