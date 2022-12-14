package kr.or.ddit.memo.dao;

import java.util.List;

import kr.or.ddit.vo.MemoVO;

public interface MemoDAO {
	public List<MemoVO> selectMemoList();
	public int insertMemo(MemoVO memo);
}
