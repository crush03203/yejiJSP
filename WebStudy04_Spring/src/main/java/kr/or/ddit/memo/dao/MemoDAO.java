package kr.or.ddit.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.MemoVO;

public interface MemoDAO {
	
  	public List<MemoVO> selectMemoList();
  	public int insertMemo(MemoVO memo);
  	public int updateMemo(MemoVO memo); //primary key 값을 가지는 값이 꼭 필요함 
  	public int deleteMemo(@Param("code") int code); //파라미터가 전달되고있음 Memo.xml에 parameterType="int"
	//int code는 이름이 존재 하지 않은채로 Memo.xml로 넘어가기 때문에 MEmo.xml에서 WHERE CODE = #{noname} 이런식으로 이 괄호안에 아무 것도 넣어줄수 없는데
  	//어노테이션으로 이름을 지정해주면 이 것을 이름으로 쓸수있음 

}
