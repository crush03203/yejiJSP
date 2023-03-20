package kr.or.ddit.radiology.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.commons.vo.DiagHistoryVO;
import kr.or.ddit.commons.vo.DivisionTreatVO;
import kr.or.ddit.commons.vo.FilmCateVO;
import kr.or.ddit.commons.vo.PagingVO;
import kr.or.ddit.commons.vo.SymptomVO;
import kr.or.ddit.commons.vo.TrmChartVO;
import kr.or.ddit.commons.vo.WaitHistoryVO;
import kr.or.ddit.commons.vo.WaitStatusVO;

/**
 * <pre>
 * 촬영기록관리(CRUD)를 위한 Persistence Layer
 * </pre>
 * @author 이하예
 * @since 2023. 2. 3.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                       수정자                                수정내용
 * --------     --------    ----------------------
 * 2023. 2. 3.   이하예                               최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */ 
@Mapper
public interface RadiologyDAO {
	public int selectTotalRecord(PagingVO<FilmCateVO> pagingVO);
	
	/**
	 * 전체 영상 기록 조회
	 * @param map
	 * @returnsize 
	 */
	public List<FilmCateVO> selectPatientList(Map<String, String> map);
	
	/**
	 * 영상 기록 선택 출력
	 * @param filmCd
	 * @return 조건에 맞는 레코드 없는 경우, null 반환
	 */
	public FilmCateVO selectPatient(@Param("filmCd") String filmCd);

	/**
	 * 촬영 결과 전송 리스트 조회
	 * @param paNo
	 * @return 조건에 맞는 레코드 없는 경우, null 반환
	 */
	public List<WaitHistoryVO> selectChartList(@Param("paNo") String paNo);
	
	
	/**
	 * xray촬영리스트 출력
	 * @param map
	 * @returnsize 
	 */
	public List<WaitHistoryVO> selectXrayList(Map<String, String> map);
	
	/**
	 * MRI촬영리스트 출력
	 * @param map
	 * @returnsize 
	 */
	public List<WaitHistoryVO> selectMRIList(Map<String, String> map);
	
	/**
	 * CT촬영리스트 출력
	 * @param map
	 * @returnsize 
	 */
	public List<WaitHistoryVO> selectCTList(Map<String, String> map);
	
	/**
	 * 초음파촬영리스트 출력
	 * @param map
	 * @returnsize 
	 */
	public List<WaitHistoryVO> selectUltraList(Map<String, String> map);
	
	/**
	 * 촬영실 대기리스트 출력
	 * @param map
	 * @returnsize 
	 */
	public List<WaitHistoryVO> selectRadiAllList(Map<String, String> map);
	

	/**
	 * 환자 대기 상태 변경(대기중->촬영중)
	 * @param waitHistoryVO
	 * @return
	 */
	public int insertWaitHistory(WaitHistoryVO waitHistoryVO);
	
	/**
	 * 환자 현재 진료 차트
	 * @param rcpNo 접수번호
	 * @return 환자의 진료차트
	 */
	public TrmChartVO selectTrmChart(@Param("rcpNo") String rcpNo);

	/**
	 * 대기 상태 select
	 * @return
	 */
	public List<WaitHistoryVO> selectWaitStatus();
	public List<FilmCateVO> selectFilmCate();
	
	/**
	 * 증상, 상병리스트 출력
	 * @param map
	 * @return
	 */
	public List<SymptomVO> selectSymptomList(Map<String, String> map);
    public List<DiagHistoryVO> selectDiagList(Map<String, String> map);
	
	/**
	 * 촬영실 대기 환자 리스트
	 * @param empNo 사번
	 * @return 대기 환자 리스트
	 */
	public List<WaitHistoryVO> selectWaitHistory(int empNo);
}
