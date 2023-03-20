package kr.or.ddit.nurse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.commons.vo.OperationInfomationVO;
import kr.or.ddit.commons.vo.PatientVO;
import kr.or.ddit.commons.vo.SymptomVO;
import kr.or.ddit.commons.vo.TrmChartVO;
import kr.or.ddit.nurse.vo.CexVO;

@Mapper
public interface OperationDAO {

	public List<OperationInfomationVO> selectOperationList();

	public TrmChartVO selectDiagnosis(Map<String, String> map);

	public List<SymptomVO> selectSymptomList(Map<String, String> map);
    
	public int insertCex(Map<String, String> map);

	public int selectCexHistory(@Param("trmCd")String trmCd, @Param("diseaseCd")String diseaseCd);

	public List<PatientVO> selectCexWaitList();

	public List<PatientVO> selectCexCompleteList();

	public int updateCex(CexVO cex);

	public CexVO selectCexDetail(CexVO cex);

	public List<CexVO> selectCexList(Map<String, String> map);


}
