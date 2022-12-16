package kr.or.ddit.memo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

@WebServlet("/memo")
public class MemoControllerServlet extends HttpServlet {

	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	컨트롤러의 의사코드 컨트롤러와 dao의 의존관계 만들기
//		1. 요청분석(생략)
		String accept = req.getHeader("Accept");
		if (accept.contains("xml")) {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}
//		2. 모델확보
		List<MemoVO> memoList = dao.selectMemoList();
//		3. 모델 공유(setAttribute)
		req.setAttribute("memoList", memoList);
//		4. 뷰선택
		String viewName = "jsonView.do";
//		5. 뷰로 이동
		req.getRequestDispatcher(viewName).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Post-Redirect-Get : PRG pattern
		MemoVO memo = getMemoFromRequest(req); // 요청처리
		dao.insertMemo(memo);
		resp.sendRedirect(req.getContextPath() + "/memo");
	}

	private MemoVO getMemoFromRequest(HttpServletRequest req) throws IOException {
		String contentType = req.getContentType();
		MemoVO memo = null;
		if (contentType.contains("json")) {

			try (BufferedReader br = req.getReader(); // body content read 용 입력스트림
			) {
				memo = new ObjectMapper().readValue(br, MemoVO.class);
				return memo;
			}

		} else if (contentType.contains("xml")) {
			try (BufferedReader br = req.getReader(); // body content read 용 입력스트림
			) {
				memo = new XmlMapper().readValue(br, MemoVO.class);
				return memo;
			}

		} else {

			memo = new MemoVO();
			memo.setWriter(req.getParameter("writer"));
			memo.setDate(req.getParameter("date"));
			memo.setContent(req.getParameter("content"));
		}

		return memo;
	}
//		

//		setWriter에 받아온 정보를 넣는 것
//		memo.setWriter();
//		memo.setContent(content);
//		memo.setDate(date);

//		try(
//			BufferedReader br = req.getReader();
//				) { 
//			String tmp = null;
//			StringBuffer strJson = new StringBuffer();
//			while ((tmp=br.readLine()) != null) {
//				strJson.append(tmp);
//			}
//			ObjectMapper mapper = new ObjectMapper();
//			memo = mapper.readValue(strJson.toString(), MemoVO.class);
//		} catch (IOException e) {
//		}
//

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
