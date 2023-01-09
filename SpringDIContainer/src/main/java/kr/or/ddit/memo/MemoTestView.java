package kr.or.ddit.memo;

import kr.or.ddit.memo.controller.MemoService;

public class MemoTestView {
	public static void main(String[] args) {
		
		   MemoService service = new MemoService();
		      service.retrieveMemoList().forEach(System.out::println);
	}
}
