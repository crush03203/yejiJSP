package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 페이징과 관련한 모든 데이터를 가진 객체
 *
 */

@NoArgsConstructor 
@ToString
@Getter
// setter는 딱두개임 나머지 값은 두가지 값으로 연산해서 받아옴
public class PagingVO<T> {//<T> : 어떤 페이지를 대상으로 하더라도 상관없음 
	
	//미리 이거screenSize, blockSize는 고정시켜 놓겠다 (private int screenSize=10 )
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}


	private int totalRecord; // DB 조회해서 디비에 있는 모든 데이터 들고옴
	private int screenSize=10; // 우리가 임의 설정 
	private int blockSize=5; // 우리가 임의 설정 
	
	private int currentPage; //클라이언트가 보내주는 파라미터값으로 결정됨
	
	private int totalPage; //totalRecord, screenSize 로 연산
	private int startRow; //currentpage, screenSize로 연산
	private int endRow; 
	private int startPage;//currsentpage, blockSize 로 연산
	private int endPage;
	
	//private List<MemberVO> dataList; //페이지 결과물
	private List<T> dataList; //memberVO말고도 다른 vo들도 들어올수있따

	
	//검색 필터링
	private SearchVO simpleCondition; //단순검색용
	private T detailCondition; //상세검색용 (private ProdVO detailCondition; )
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	
	
	//dataList(결과물 받을수있는 list)
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	
	// setter는 딱두개임(setTotalRecord ,setCurrentPage)나머지 값은 두가지 값으로 연산해서 받아옴
	//totalRecord가 vo로 들어오는 순간 나머지 것(totalPage)도 연산할 수 있음
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		totalPage = (totalRecord + (screenSize - 1))  / screenSize;
	}
	

	//currentpage 값이 vo로 들어오는 순간 나머지 것들(endRow,...)도 연산할수있음 
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		endRow = currentPage * screenSize; 
		startRow = endRow - (screenSize - 1);
		endPage = ((currentPage +(blockSize-1)) / blockSize) * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	//아래쪽에 있는 페이징 태그 만들기 
	//private final String APATTERN = "<a href='?page=%d'>%s</a>";
	//a링크 눌러서 말고 스크립트를 이용해서 페이지 이동을 하게끔 만들기  =>2페이지로 가면 대전으로 검색했는데 대구로 나오는 오류 수정 
	private final String APATTERN = "<a href='#' class='paging' data-page='%d'>%s</a>";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer(); //모아두기  
		
		//이전버튼 만들기
		if(startPage > blockSize) { //이전블럭(=이전페이지) 존재함 
			html.append(
				String.format(APATTERN, startPage-blockSize, "이전")
			);
		}
		
		//페이징 숫자버튼 만들기
		endPage = endPage > totalPage ? totalPage : endPage;
		for (int page = startPage; page <= endPage; page++) {
			
			if(page == currentPage) {//현재페이지숫자 
				html.append(
					"<a href='#'>" + page + "</a>"
				);
			}else {//그냥 일반 숫자출력
				html.append(
						String.format(APATTERN, page, page+"") //page+"" =문자열로 만들어주기
				);
			}
		}
		
		//다음페이지 만들기
		if(endPage<totalPage) {
			html.append(
				String.format(APATTERN, endPage+1, "다음")
			);
		}
		
		
		return html.toString();
	}
	
	
	
}
