package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;

/**
 * 상품 분류 선택 UI와 거래처 선택 UI완성을 위한 데이터 조회
 * @author PC-20
 *
 */

public interface OthersDAO {
	public List<Map<String, Object>> selectLprodList(); //Map<String, Object>: 특정 vo의 컬럼명(String) , 데이터 값(Object) 
	
	public List<BuyerVO> selectBuyerList(@Param("buyerLgu")String buyerLgu); //buyerLgu라는 이름을 가진 파라미터명으로 값이 넘어감 
}
