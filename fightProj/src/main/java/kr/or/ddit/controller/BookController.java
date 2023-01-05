package kr.or.ddit.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

/*
 Controller 어노테이션
 프링아 이거 웹 브라우저의 요청(request)를 받아들이는 컨트롤러야
 */
@Slf4j
@Controller
public class BookController {
//	DI : 의존성 주입
//	IoC : 제어의 역전
	
	@Inject
	BookService bookService;
	
	//GET 방식으로 /create URL을 톰켓서버에 요청
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String create() {
		// /WEB-INF/views/ + ... + .jsp
		return "book/create";
	}
	
//	URL : http://localhost/create?title=개똥이모험&category=소설&price=10000
	//요청파라미터(HTTP 파라미터, QueryString) : title=개똥이모험&category=소설&price=10000
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute BookVO bookVO) {
		/*
		 ModelAndView
		 1) Model : Controller가 반환할 데이터(String, int, List, Map, VO..)를 담당
		 2) View : 화면을 담당(뷰(View : JSP)의 경로)
		 */
		//BookVO [bookId=0, title=개똥이의 모험, category=소설, price=10000, insertDate=null]
		log.info("bookVO : " + bookVO.toString());
		
		//1증가된 기본키값
		int bookId = this.bookService.insert(bookVO);
		
		log.info("bookId : " + bookId);
		
		/*
		 ModelAndView
		 1) Model : Controller가 반환할 데이터(String, int, list...)를 담당
		 2) View :  화면을 담당(뷰(View : jsp)의 경로)
		 */
		ModelAndView mav = new ModelAndView();
		
		//bookId : 입력된 기본키 값
		if(bookId<1) { //insert 실패
//			create(get 방식)를 다시 요청 함 => uri 주소가 바뀜
			mav.setViewName("redirect:/create");
			
		} else {//insert 성공
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		
		return mav;
	}
	
	//책상세보기
	//요청 URI : http://localhost/detail?bookId=4
	//요청 UR: : http://localhost/detail
	//요청 파라미터 : bookId=4
	//방식 : get
	
	@RequestMapping(value = "detail", method=RequestMethod.GET) //요청이 이메소드로 메핑된다
	public ModelAndView detail(BookVO bookVO, ModelAndView mav) {
//		bookVO : BookVO [bookId=5, title=null, category=null, price=0, insertDate=null]
		BookVO data = this.bookService.detail(bookVO); 
		//model : data 데이터를 jsp로 넘겨줌
		mav.addObject("data",data);
		mav.addObject("bookId",bookVO.getBookId());
		
		//forwarding => 데이터를 jsp로 넘겨줌/ but, redirect : => 데이터를 jsp로 못 넘겨줌
		//WEB-ING/views/ + ... + .jsp
		mav.setViewName("book/detail");
		
		return mav;
	}
	
	// 요청 URI : /update?bookId=2
	// 요청 URL : /update
	// 요청 파라미터(String) : bookId=2 //요청 파라미터는 스트링이지만 int형으로 자동 형변환
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam int bookId, @ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		// 책 수정 화면 = 책 입력 화면 + 책 상세 화면
		// 책 입력 화면 형식을 그대로 따라가고 빈 칸을 데이터로 채워주면 된다 
		log.info("bookId : " + bookId);
		log.info("bookVO : " + bookVO.toString());
		
//		bookVO : BookVO [bookId=5, title=null, category=null, price=0, insertDate=null]
		BookVO data = this.bookService.detail(bookVO); 
		
		//model : data 데이터를 jsp로 넘겨줌
		mav.addObject("data",data);
		// view : jsp의 경로
		// /WEB-INF/views/  + ...+ .jsp
		// forwarding
		mav.setViewName("book/update");
		return mav;
	}
	
	/**
	 * {"bookId" : 2, "title" : "수정된 제목" , "category" : "수정된 카테고리"
	 * "price" : "10000"}
	 * 
	 * @ModelAttribute : 멤버 변수를? 
	 * VO 객체 매개변수 
	 * @return
	 */
	@RequestMapping(value ="/update" , method =RequestMethod.POST)
	public ModelAndView update(@ModelAttribute BookVO bookVO, 
			ModelAndView mav) {
		//BookVO [bookId=2, title=패자부활전3일차_0103수정, category=영화, price=10000, insertDate=null]
		log.info("bookVO" + bookVO.toString());
		
		// bookId=가 2번인 정보를 수정
		int result = this.bookService.update(bookVO);
		
		if(result>0) {
			//수정성공
		
		// 상세페이지로 이동 (redirect : URI 주소 재요청)
		// /detail?bookId=bookVO의 bookId 멤버 변수의 값
		// /detail?bookId=2
			mav.setViewName("redirect:/detail?bookID=" + bookVO.getBookId());
		} else {
			//수정 실패
			mav.setViewName("redirect:/update?bookID=" + bookVO.getBookId());
		}
		
		return mav;
	}
 	
	
	//글 삭제
//BookVO [bookId=2, title=null, category=null, price=null, insertDate=null] 

	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public ModelAndView delete(BookVO bookVO ,
			ModelAndView mav) {
		log.info("bookVO : " + bookVO.toString());
		
		//글삭제 처리
//		result : 0 또는 1 이상
		int result = this.bookService.delete(bookVO);
		
		if(result>0) {
			//성공 : /list로 redirect
			mav.setViewName("redirect:/list");
			
		} else {
			//실패 : detail?bookId=2 로 redirect
			mav.setViewName("redirect:/detail?bookId=" + bookVO.getBookId());
		}
		return mav;
	
	}
	//글 목록
	@RequestMapping(value = "list", method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mav) {
		List<BookVO> bookVOList = this.bookService.select();
		log.info("bookVOList" + bookVOList);
	      
	      // book 폴더의 list.jsp를 해석(list_jsp.java)하고, 컴파일(list_jsp.class).. 서블릿
	      // 하여 html을 만들어 크롬으로 응답(response)
	      // forwarding
	      // bookVO : BookVO [bookId=2, title=null, category=null, price=0,
	      // insertDate=null] -> list로 돌아감
	      
	      // ModelAndView
	      // Model : 데이터(List, Map, VO...)
	      // View : jsp
	      // Model에 bookVOList를 담아서 list.jsp로 전달
	      // data라는 이름에 리스트 데이터를 담음
		
	      mav.addObject("data",bookVOList);
	      log.info("bookVOList : " + bookVOList);
	      mav.setViewName("book/list");
	      return mav;
	   }

}
