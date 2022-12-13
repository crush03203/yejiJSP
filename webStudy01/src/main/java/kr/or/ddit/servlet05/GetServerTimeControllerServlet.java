package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/05/getServerTime")
public class GetServerTimeControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청을 분석하는 작업
		String localeParam = req.getParameter("locale");
		
//		2. 모델을 가져와야한다
		Date now = new Date();
		String nowStr = String.format(Locale.FRANCE,"now :%tc",now);
		req.setAttribute("now", nowStr);
		resp.setHeader("Refresh", "1");
//		4. 뷰선택
		String viewName = "/jsonView.do";
//		5. 
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
