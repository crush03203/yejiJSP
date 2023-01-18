package kr.or.ddit.buyer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BuyerServiceImpl implements BuyerService {
	
	private final BuyerDAO buyerDAO;

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO) {
		pagingVO.setTotalRecord(buyerDAO.selectTotalRecord(pagingVO));
		pagingVO.setDataList(buyerDAO.selectBuyerList(pagingVO));
		return pagingVO.getDataList();
	}

	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = buyerDAO.selectBuyer(buyerId);
		if(buyer==null)
			throw new RuntimeException(String.format("%s 상품 없음.", buyerId));
		return buyer;
	}

	@Override
	public int createBuyer(BuyerVO buyer) {
		return buyerDAO.insertBuyer(buyer);
		
	}

	@Override
	public int modifyBuyer(BuyerVO buyer) {
		return buyerDAO.updateBuyer(buyer);
	}

}
