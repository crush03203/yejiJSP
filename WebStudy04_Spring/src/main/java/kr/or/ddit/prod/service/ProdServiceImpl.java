package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	
	
	private ProdDAO prodDAO = new ProdDAOImpl();

	@Override
	public ProdVO retrieveProd(String prodId) {
		
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null)
			throw new RuntimeException(String.format("%s는 없는 상품",prodId));
		
		return prod;	
	}


	@Override
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) {
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ProdVO> dataList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(dataList);
	}



	//상품 추가 
	@Override
	public ServiceResult createProd(ProdVO prod) {
		
		int rowcnt = prodDAO.insertProd(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		
		
	}



	//상품 수정 
	@Override
	public ServiceResult modifyProd(ProdVO prod) {

		/*ProdVO inputData = new ProdVO();
		inputData.setProdId(prod.getProdId());
		inputData.setProdName(prod.getProdName());
		*/
		return null;
	}




}
