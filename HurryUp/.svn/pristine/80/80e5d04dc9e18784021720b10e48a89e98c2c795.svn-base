package kr.or.ddit.nurse.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.vo.OperationInfomationVO;
import kr.or.ddit.commons.vo.PatientVO;
import kr.or.ddit.commons.vo.SymptomVO;
import kr.or.ddit.commons.vo.TrmChartVO;
import kr.or.ddit.nurse.service.OperationService;
import kr.or.ddit.nurse.vo.CexVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/nurse")
public class OperationMainController {

	@Inject
	private OperationService service;
	
	@GetMapping("/operationMain")
	public String operationView() {
		String viewName = null;
		viewName = "nurse/operationMain";
		return viewName;
	}
	
	@ResponseBody
	@GetMapping("/operationList")
	public List<OperationInfomationVO> operationList(){
		List<OperationInfomationVO> list = service.retrieveOperationList();
		return list;
	}
	
	@ResponseBody
	@PostMapping("/diagnosisDetail")
	public TrmChartVO diagnosisDetail(@RequestBody Map<String, String> map) {
		TrmChartVO chart = service.retrieveDiagnosisDetail(map);
		return chart;
	}
	
	@ResponseBody
	@PostMapping("/symptomList")
	public List<SymptomVO> symptomList(@RequestBody Map<String, String> map) {
		List<SymptomVO> symptom = service.retrieveSymptomList(map);
		return symptom;
	}
	
	@ResponseBody
	@PostMapping("/cexCreate")
	public int createCex(@RequestBody Map<String, String> map) {
		int result = service.createCex(map);
		return result;
	}
	
	@ResponseBody
	@PostMapping("/cexWaitList")
	public List<PatientVO> cexWaitList(){
		List<PatientVO> list = service.retrieveCexWaitList();
		return list;
	}
	
	@ResponseBody
	@PostMapping("/cexCompleteList")
	public List<PatientVO> cexCompleteList(){
		List<PatientVO> list = service.retrieveCexCompleteList();
		return list;
	}
	
	@ResponseBody
	@PostMapping("/cexRegistration")
	public int cexRegistration(@RequestBody CexVO cex) {
		int result = service.modifyCex(cex);
		return result;
	}
	
	@ResponseBody
	@PostMapping("/cexResultDetail")
	public CexVO cexResultDetail(@RequestBody CexVO cex) {
		cex = service.retrieveCexDetail(cex);
		return cex;
	}
	
	@ResponseBody
	@PostMapping("/cexHistorySearch")
	public List<CexVO> cexPatientSearch(@RequestBody Map<String, String> map) {

		List<CexVO> list = service.retrieveCexPatientList(map);
			
		return list;
	}
}
