package kr.or.ddit.sample.dao;

public class SampleDAOFactory {
	public static SampleDAO getSampelDAO() {
		return new SampleDAOImpl_Postgre();
	}
}
