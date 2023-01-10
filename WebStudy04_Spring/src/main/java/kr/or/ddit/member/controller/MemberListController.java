package kr.or.ddit.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //front뒤에서 동작하는 command handler가 됨
public class MemberListController{ //완벽한 POJO가 됐음 
	
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberList.do") //메소드가 get으로 숨어있음
	public String memberList(
			//required생략 , 파라미터는 page로가져오기 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//			,@RequestParam(value="searchType", required=false) String searchType
//			,@RequestParam(value="searchWord", required=false) String searchWord
			, @ModelAttribute SearchVO simpleConition
			//따로 이름을 사용하고싶다면 @ModelAttribute("simpleConition") SearchVO simpleConition
			, HttpServletRequest req
	)  {
		
	
		//검색 필터링 - 파라미터 받기 
//		String searchType = req.getParameter("searchType");
//		String searchWord = req.getParameter("searchWord");
//		SearchVO simpleConition = new SearchVO(searchType, searchWord); //검색 

		
		//String pageParam = req.getParameter("page"); //넘어오는 page값이 숫자가 아니라 string 임  이때 아래에서 파싱해야됨 
		/*int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) { //이 if문에 들어왔다면 넘어온 파라미터 값이 숫자니까 파싱
			currentPage = Integer.parseInt(pageParam);
		}*/
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(4,2);  //4개와 2개 페이지만 보여줌 
		//PagingVO<MemberVO> pagingVO = new PagingVO<>(); 
		//pagingVO.setCurrentPage(1); //1페이지 요청    pagingVO에서 setter호출 setCurrentPage
		pagingVO.setCurrentPage(currentPage); 
		pagingVO.setSimpleCondition(simpleConition); //검색 
		

		//2 모델확보 - 모델레이어 사용(service 가져와서 사용)
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO); 
		req.setAttribute("pagingVO", pagingVO); //페이징
		
		//3 
		req.setAttribute("memberList", memberList); //memberList 가져와서 "memberList"라는 이름으로 jsp에 보내주기 
		
		
		
		
		log.info("paging data : {}", pagingVO);
		
		
		//4.
		String viewName = "member/memberList";
		
		return viewName;
		

		
	}
}
