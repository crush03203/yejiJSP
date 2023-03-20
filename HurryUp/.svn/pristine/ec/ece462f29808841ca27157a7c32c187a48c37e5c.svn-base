package kr.or.ddit.radiology.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.vo.DiagHistoryVO;
import kr.or.ddit.commons.vo.DivisionTreatVO;
import kr.or.ddit.commons.vo.FilmCateVO;
import kr.or.ddit.commons.vo.SymptomVO;
import kr.or.ddit.commons.vo.TrmChartVO;
import kr.or.ddit.commons.vo.WaitHistoryVO;
import kr.or.ddit.commons.vo.WaitStatusVO;
import kr.or.ddit.doctor.dao.DoctorDAO;
import kr.or.ddit.radiology.dao.RadiologyDAO;

@Service
public class RadiologyServiceImpl implements RadiologyService {

	@Inject
	private RadiologyDAO radiologyDAO;
	
	// 환자리스트
	@Override
	public List<FilmCateVO> retrievePatientList() {
		return radiologyDAO.selectPatientList(null);
	}
	
	// 환자 검색
	@Override
	public List<FilmCateVO> retrievePatientSearchList(Map<String, String> map) {
		return radiologyDAO.selectPatientList(map);
	}
	

	@Override
	public FilmCateVO retrieveFilmRecord(String filmCd) {
		FilmCateVO filmRecord = radiologyDAO.selectPatient(filmCd); 
		return filmRecord;
	}

	
	//대기실 상황
	@Override
	public List<WaitHistoryVO> selectXrayList() {
		return radiologyDAO.selectXrayList(null);
	}

	@Override
	public List<WaitHistoryVO> selectMRIList() {
		return radiologyDAO.selectMRIList(null);
	}

	@Override
	public List<WaitHistoryVO> selectCTList() {
		return radiologyDAO.selectCTList(null);
	}
	
	@Override
	public List<WaitHistoryVO> selectUltraList() {
		return radiologyDAO.selectUltraList(null);
	}
	
	@Override
	public List<WaitHistoryVO> selectChartList(String paNo) {
		return radiologyDAO.selectChartList(paNo);
	}
	
	// 촬영실 전체 대기 리스트 출력
	@Override
	public List<WaitHistoryVO> selectRadiAllList() {
		return radiologyDAO.selectRadiAllList(null);
	}

	 //대기히스토리 추가(대기중->촬영중)
	@Override
	public int createWaitHistory(WaitHistoryVO waitHistoryVO) {
		return radiologyDAO.insertWaitHistory(waitHistoryVO);
	}

	/* 
	 * 환자번호에 해당하는 접수번호를 얻고
	 * 접수번호로 해당하는 진료차트 번호 조회
	 * 진단내역, 증상내역, 처방 내역, 촬영 내역을 진료차트 번호로 조회
	 */		
	@Override
	public TrmChartVO retrieveTrmChart(String rcpNo) {

		return radiologyDAO.selectTrmChart(rcpNo);
	}

	// 대기 상태 select
	@Override
	public List<WaitHistoryVO> retreiveWaitStatus() {
		return radiologyDAO.selectWaitStatus();
	}

	
	// 증상내역 리스트 
	@Override
	public List<SymptomVO> retrieveSymptomList(Map<String, String> map) {
		return radiologyDAO.selectSymptomList(map);
	}
	// 상병내역 리스트
	@Override
	public List<DiagHistoryVO> retrieveDiagList(Map<String, String> map){
		return radiologyDAO.selectDiagList(map);
	}

	// 대기리스트
	@Override
	public List<WaitHistoryVO> retrieveWaitHistory(int empNo) {

		return radiologyDAO.selectWaitHistory(empNo);
	}

	@Override
	public List<FilmCateVO> retrieveFilmCate() {
		return radiologyDAO.selectFilmCate();
	}

}
