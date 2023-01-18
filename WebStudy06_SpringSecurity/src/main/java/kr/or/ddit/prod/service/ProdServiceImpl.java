package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdServiceImpl implements ProdService {
	private final ProdDAO prodDAO;
	
	@Inject
	private WebApplicationContext context;
	
	@Value("#{appInfo.imageFolder}")
	private File saveFolder;
	@Value("#{appInfo.imageFolder}")
	private String saveFolderURL;
	
	@PostConstruct
	public void init() throws IOException {
		if(!saveFolder.exists())
				saveFolder.mkdirs();
		log.info("주입된 이미지 저장 경로 : {}", saveFolder);
	}
	
	private void processProdImage(ProdVO prod) {
//		1. 저장 
		try {
//			if(1==1) throw new RuntimeException("트랜잭션 관리 여부 확인을 위한 강제 발생 예외");
			prod.saveTo(saveFolder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null)
			throw new RuntimeException(String.format("%s 는 없는 상품", prodId));
		return prod;
	}

	@Override
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) {
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ProdVO> dataList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(dataList);
	}

//	@Inject
//	private SqlSessionFactory sqlSessionFactory;	
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
//		session open
//		try(
//			SqlSession sqlSession = sqlSessionFactory.openSession(); // 트랜잭션 시작
//		){
//			int rowcnt = prodDAO.insertProd(prod, sqlSession);
		
			int rowcnt = prodDAO.insertProd(prod);
			
			processProdImage(prod);
			
//			sqlSession.commit();
			
			return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
//		}
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProdId());
		
		int rowcnt = prodDAO.updateProd(prod);
		processProdImage(prod);
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}





















