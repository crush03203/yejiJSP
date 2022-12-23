package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/10/calendar.do")
public class CalendarControllerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		1. 요청분석
		String yearParam = req.getParameter("year");
		String monthParam = req.getParameter("month");
		String language = req.getParameter("language");
		
		Locale clientLocale = req.getLocale();
		if(language != null && !language.isEmpty()) {
			clientLocale = Locale.forLanguageTag(language);
		}
		
		Calendar calendar = Calendar.getInstance();
		try {
			if(yearParam!=null && monthParam!=null) {
				int year = Integer.parseInt(yearParam);
				int month = Integer.parseInt(monthParam);
				calendar.set(YEAR,year);
				calendar.set(MONTH,month);
			}
			req.setAttribute("calendar", new CalendarWrapper(calendar, clientLocale));
			
//		4. 뷰선택
			String viewName = "/WEB-INF/views/calendar.jsp";
//		5. 
			req.getRequestDispatcher(viewName).forward(req, resp);
			
		}catch (NumberFormatException e) {
			resp.sendError(400,e.getMessage()); //예외처리도 하나의 조건문으로 생각하고 발생시킬 수 있다
			return;
		}
	
//		if(yearParam != null && !yearParam.matches("\\d{4}")) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
//		위에 try~ catch문이랑 동일한 기능을하는 것
	}
}
