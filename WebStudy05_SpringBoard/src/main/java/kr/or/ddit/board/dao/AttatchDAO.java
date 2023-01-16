package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;

@Mapper
public interface AttatchDAO {
	public int insertAttatches(BoardVO board);
	public List<AttatchVO> selectAttatchList(int boNo);
	public AttatchVO selectAttatch(int attNo);
	public int deleteAttatches(BoardVO board);
}
