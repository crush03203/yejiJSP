package kr.or.ddit.memo.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if(accept.contains("xml")) {
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
//	비동기로 메모가 등록되도록 해라
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Post-Redirect-Get : PRG pattern
		MemoVO memo = getMemoFromRequest(req);
		dao.insertMemo(memo);
		resp.sendRedirect(req.getContextPath() + "/memo");
	}

	private MemoVO getMemoFromRequest(HttpServletRequest req) {
//		1. 요청 분석  req  = 받아온 요청
		MemoVO memo = new MemoVO();
		String writer = req.getParameter("writer");
		String date = req.getParameter("date");
		String content = req.getParameter("content");
		
//		setWriter에 받아온 정보를 넣는 것
		memo.setWriter(writer);
		memo.setContent(content);
		memo.setDate(date);
		

		return memo;
	}

	@Override

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
