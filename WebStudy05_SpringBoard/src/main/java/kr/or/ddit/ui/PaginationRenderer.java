package kr.or.ddit.ui;

import kr.or.ddit.vo.PagingVO;

//인터페이스 실구현체가 아니기 때문에
public interface PaginationRenderer {
	//물음표 메타?
	public String renderPaginnation(PagingVO<?> pagingVO);

}
