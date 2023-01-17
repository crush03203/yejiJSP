package kr.or.ddit.memo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.memo.dao.MemoDAOImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemoVO;

@Controller
public class MemoController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MemoController.class);//MemoControllerServlet.class : kr.or.ddit여기에 등록되어있는 level따라감 

	
//	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();
//	private MemoVO memo; 

//	private MemoDAO dao = DataBaseMemoDaoImpl.getInstance();
	private MemoDAO dao = new MemoDAOImpl(); //결합력 
	
	@RequestMapping("/memo")
	public String doGet(
//		@RequestHeader("Accept") String accept,
		HttpServletRequest req, 
		HttpServletResponse resp
	) throws ServletException, IOException {

		//"요청분석"단계 굳이 넣을 필요 없음 - memoVO전체를 불러오기 때문에 필요 없음
		//만약 작업하려면 아래와 같이 하면됨 
		String accept = req.getHeader("Accept");
		log.info("accept header: {}", accept);
		// log4j2.xml <Logger name="kr.or.ddit" level="info" additivity="false"> 여기서 level을 info로 해놨으니까 여기도 info로 지정해줘야됨 
		
		if(accept.contains("xml")) {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE); //나는 xml을 제공할 수 없다 = > 406에러
			return null;
		}

		// 모델확보
		List<MemoVO> memoList = dao.selectMemoList();
		
		//모델공유
		//req에 setAttribute라는 걸 저장하는데 memoList라는 키를 가진 memoList 값 세팅 
		req.setAttribute("memoList", memoList);
		//System.out.println(memoList);

		//마샬링하기 위해서  뷰로이동
		return "forward:/jsonView.do";
		//String viewName = "/jsonView.do";
		//req.getRequestDispatcher(viewName).forward(req, resp); //viewName 여기에 req, resp 같이 보여줌 


	}
	
	@RequestMapping(value="/memo", method=RequestMethod.POST)
	public String doPost(
			HttpServletRequest req
	) throws IOException  {
//		Post-Redirect-Get : PRG pattern
		MemoVO memoParam = getMemoFromRequest(req);//요청받아서 이 메소드를 실행하겠다 

		
		//public int insertMemo(MemoVO memo);
		//String으로 받아온 req객체를 MemoVO객체화 할 수 밖에 없음 왜냐? insertMemo에 넣어줘야 되니까.
		// insertMemo에는 파라미터 값을 MemoVO로만 받을 수 있기 때문에 String으로 들어오는 req는 받으면 오류
		dao.insertMemo(memoParam); 
		//resp.sendRedirect(req.getContextPath() + "/memo");
		//getrequestDispathcer.forward대신 sendRedirect 썻음 
		//왜냐? 내 정보를 지워주기 위해서 안지워주면 내가 작성한 모든 정보들을 담고 넘겨줌 
		return "redirect:/memo";
	}
	
	private MemoVO getMemoFromRequest(HttpServletRequest req) throws IOException { 

		//방법1 . 파라미터 값으로 받아올 경우 (앞단에서 직렬화방식으로 통해서 바로 파라미터 값으로 넘어올 경우)
//		MemoVO memo = new MemoVO(); 
		//메모객체를 만들고 (넘어오는 값들이 "memoVO의 형태에 맞지 않기 때문에" 거기에맞게 변경해주기 위해서 다시 객체를 만들어줌 )
		
		///////////////////////////////////

		
		//앞단에서 파라미터 값 받아오고  - 앞단의 직렬화방식을 통해서 파라미터 방식으로 값을 받아왔을 경우  
//		memo.setWriter(req.getParameter("writer"));
//		memo.setDate(req.getParameter("date"));
//		memo.setContent(req.getParameter("content"));
		

		//다른방식
		/*String writer = req.getParameter("writer");
		System.out.println(writer);
		String date = req.getParameter("date");
		String content = req.getParameter("content");*/
		
		//memoVO에 추가 set
		//memoVO에서 데이터를 뽑아올땐 getWriter
	/*	memo.setWriter(writer);
		memo.setDate(date);
		memo.setContent(content);*/
		
		
		////////////////////////////////////////////
		

		//방법2 
		//파라미터 값으로 받으면 마샬링 필요 없음 json, xml로 넘어올 경우 마샬링 필요함 
		String contentType = req.getContentType(); //content body (f12 network )에 content내용
		MemoVO memo = null;
		if(contentType.contains("json")) { //json으로 들어왔을 경우 
			try(
					BufferedReader br = req.getReader();  //body content read용 입력 스트림 -> 
					//얘를 받아서 역직렬화(json형태로 바꾸기)를 하고 언마샬링(자바가 읽을 수 있게 바꾼후 memoVO에 집어넣기) 해야됨 
			){
					memo = new ObjectMapper().readValue(br, MemoVO.class ); //버퍼 안에 저장되어있는 값들을 받아서 지가 알아서 역직렬화 한 후 MemoVO.class이 타입으로 언마샬링 해줌
			}
		}else if(contentType.contains("xml")) {
			
			try(
					BufferedReader br = req.getReader();  //body content read용 입력 스트림 -> 
					//얘를 받아서 역직렬화(xml형태로 바꾸기)를 하고 언마샬링(자바가 읽을 수 있게 바꾼후 memoVO에 집어넣기) 해야됨 
			){
					memo = new XmlMapper().readValue(br, MemoVO.class ); //버퍼 안에 저장되어있는 값들을 받아서 지가 알아서 역직렬화 한 후 MemoVO.class이 타입으로 언마샬링 해줌
			}
			
		}else { //앞단에서 직렬화 방식으로 파라미터로 넘어왔을 경우  
			memo.setWriter(req.getParameter("writer"));
			memo.setDate(req.getParameter("date"));
			memo.setContent(req.getParameter("content"));
		}
		return memo;
		

	}

	@RequestMapping(value="/memo", method=RequestMethod.PUT)
	public String doPut(HttpServletRequest req) throws ServletException, IOException {
		return null;

	
	}
	
	@RequestMapping(value="/memo", method=RequestMethod.DELETE)
	public String doDelete(HttpServletRequest req) throws ServletException, IOException {
		return null;
	}
	
	
	
}







