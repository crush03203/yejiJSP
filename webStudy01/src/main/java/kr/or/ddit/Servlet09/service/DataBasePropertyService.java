package kr.or.ddit.Servlet09.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface DataBasePropertyService {
	public List<DataBasePropertyVO> retrieveProvertyList(String propertyName);
}
