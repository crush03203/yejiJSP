package kr.or.ddit.ui;

import kr.or.ddit.vo.PagingVO;

public class DefaultPaginationRenderer implements PaginationRenderer {
   private final String APATTERN = "<a class='paging' href='#' data-page='%d'>%s</a>"; //data-page는 키가 page가 된다


	@Override
	public String renderPaginnation(PagingVO<?> pagingVO) {
		//html을 반환받을 꺼라서 String타입
	      StringBuffer html = new StringBuffer();
	      int startPage = pagingVO.getStartPage();
	      int blockSize = pagingVO.getBlockSize();
	      int endPage = pagingVO.getEndPage();
	      int currentPage = pagingVO.getCurrentPage();
	      int totalPage = pagingVO.getTotalPage();
	      
	      if( startPage > blockSize ) { 
	         //이전 페이지가 존재할 때
	         //다음으로 이동하기 위한 for문
	         html.append(
	            String.format(APATTERN, startPage-blockSize, "이전" )
	         );
	      }
	      for(int page=startPage; page<=endPage; page++) {
	         if(page==currentPage) { //현재 페이지가 현재페이지라면
	            html.append(
	               "<a href='#'>"+page+"</a>"
	            );
	         } else {
	            html.append(
	               String.format(APATTERN, page, page+"")
	            );
	         }
	      }
	      
	      if( endPage < totalPage ) { //마지막 페이지가 전체페이지보다 작을때의 경우의 수만 생각하면 됨
	         html.append(
	            String.format(APATTERN, endPage+1, "다음" )
	         );
	      }
	      
	      return html.toString(); //html은 문자라서 toString
	}


	
}