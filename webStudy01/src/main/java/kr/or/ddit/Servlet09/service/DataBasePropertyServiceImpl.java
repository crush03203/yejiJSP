package kr.or.ddit.Servlet09.service;

import java.util.List;

import kr.or.ddit.Servlet09.dao.DataBasePropertyDAO;
import kr.or.ddit.Servlet09.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService {
	private DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
//	컨트롤러와 인터페이스 의존관계 생성 
	@Override
	public List<DataBasePropertyVO> retrieveProvertyList(String propertyName) {
		List<DataBasePropertyVO> list = dao.selectPropertyList(propertyName);
		list.stream()
			.forEach(System.out::println);//메소드 레퍼런스 구조
		return list;
	}

}
