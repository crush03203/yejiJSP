<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>성능고려사항</h4>
	<pre>
	응답소요 시간 : latency time(99,999%)  + processing time
	case 1 - 13ms - 다한번씩만 실행한 것
	case 2 - 1120ms - 커넥션도 100번 쿼리 실행도 100번 //// DBCP case 4 - 25ms 
	case 3 - 27ms - 커넥션은 1번 쿼리는 100번 실행했음 
	결론: 커넥션을 생성하는 것이 오랜 시간 걸린다 / 커넥션 작업을 미리해두면 시간을 단축시킬 수 있다 이걸 바로 쿨링?이라고 한다
	쿨을 만들어서 관리할 수 있어야한다. 커넥션 쿨링 구조

<%
		long startTime = System.currentTimeMillis();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  code, writer, content, \"DATE\"  ");
		sql.append(" FROM   tbl_memo                        ");
		for (int i = 1; i <= 100; i++) {
			try (Connection conn = ConnectionFactory.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

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
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		long endTime = System.currentTimeMillis();
	%>
	소요시간 <%=endTime - startTime%>ms

</pre>


</body>
</html>