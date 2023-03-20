package kr.or.ddit.receiption.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.vo.PatientVO;
import kr.or.ddit.commons.vo.WaitHistoryVO;
import kr.or.ddit.receiption.vo.ReadRegistVO;
import kr.or.ddit.receiption.vo.ReceiptionVO;
import kr.or.ddit.receiption.vo.RegistVO;
import kr.or.ddit.receiption.vo.SelectPatientVO;

/**
 * 
 * <pre>
 * 접수 화면을 위한 Business Logic Layer
 * </pre>
 * @author 박인성
 * @since 2023. 2. 6.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *   수정일                   수정자                         수정내용
 * --------      --------    ----------------------
 * 2023. 2. 6.     박인성                       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */

public interface ReceptionService {

	/** 환자검색
	 * @param map
	 * @return
	 */
	public List<ReceiptionVO> searchPatientList(Map<String, String> map);
	
	/**
	 * 접수등록
	 * @param receiptionVO
	 * @return
	 */
	public int createReceiption(RegistVO registVO);
	
	/**대기 히스토리 등록
	 * @param waitHistoryVO
	 * @return
	 */
	public int createWaitHistory(WaitHistoryVO waitHistoryVO);
	
	/**
	 * 새로운 환자 등록
	 * @param patientVO
	 * @return
	 */
	public int createPatient(PatientVO patientVO);

	/**
	 * 등록한 최신환자 조회
	 * @param selectPatientVO
	 * @return
	 */
	public SelectPatientVO selectPatient();
	
	public ReadRegistVO selectRegistVO(String paNo);
}






























