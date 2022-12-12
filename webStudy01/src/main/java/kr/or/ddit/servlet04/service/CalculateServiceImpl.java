package kr.or.ddit.servlet04.service;


public class CalculateServiceImpl implements CalculateService {

	@Override
	public int plus(int a, int b) {
		int result = a + b;
		return result;
	}

	@Override
	public int minus(int a, int b) {
		int result = a - b;
		return result;
	}

	@Override
	public int multiply(int a, int b) {
		int result = a * b;
		return result;
	}

	@Override
	public int divice(int a, int b) {
		int result = a / b;
		return result;
	}



}
