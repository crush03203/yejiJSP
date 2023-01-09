package kr.or.ddit.memo.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemoVO;

public class FileSystemMemoDAOImpl implements MemoDAO {
   
   private File dataBase = new File("d:/memos.dat");
   private Map<Integer, MemoVO> memoTable;
   
   //싱글톤 - 생성자가 private 일 경우 싱글톤을 이용해서 접근할 수 있음 
   private static FileSystemMemoDAOImpl instance; 
   public static FileSystemMemoDAOImpl getInstance() {
      if(instance==null)
         instance = new FileSystemMemoDAOImpl();
      return instance;
   }
   
   //생성자
   private FileSystemMemoDAOImpl() { 
      //뒷단에 들어있는 데이터들을 (역직렬화해서  SerializationTest참고) 받아오기  -  이 예제에서는 db없기 떄문에 임의적으로 만들어서 불러옴
      try(
         FileInputStream fis = new FileInputStream(dataBase);   
         BufferedInputStream bis = new BufferedInputStream(fis); //속도 높이기 위해서 사용
         ObjectInputStream ois = new ObjectInputStream(bis);
      ){
            memoTable = (Map<Integer, MemoVO>) ois.readObject(); //ois.readObject()이걸 읽어와서 memoTable에 넣어주기 
      }catch(Exception e) {
         System.err.println(e.getMessage()); //에러로그 출력
         this.memoTable = new HashMap<Integer, MemoVO>();
      }
   }
   
   
   
   
   @Override
   public List<MemoVO> selectMemoList() {
      return new ArrayList<>(memoTable.values()); //memoTable값들을 ArrayList형태로 반환 
   }

   @Override
   public int insertMemo(MemoVO memo) {
      //1. memo.getCode()만들기 
      int maxCode = memoTable.keySet().stream()
                                .mapToInt(key->key.intValue()) //int형으로 변경하기 
                                .max()
                                .orElse(0); //max값이 있으면 mnax값 반환하고 없으면 0반환
      memo.setCode(maxCode+1); //insert하기 전에 primary key를 생성하는 코드 만들어 줌 

      
      //2. memo.getCode()에 맞춰서 memo값 넣어주기 
      memoTable.put(memo.getCode(),memo); 
      serializeMemoTable();
      return 1;
   }
   
   //파일시스템(가짜 DB)에 정보가 반영되는 로직
   //누군가가 글을 쓸때마다 쓴 글을 모아서 memoTable에 써놔야됨 (=>직렬화해서 insertMemo에 출력시킴)
   private void serializeMemoTable() {
      try(
         FileOutputStream fos = new FileOutputStream(dataBase);   
         ObjectOutputStream oos = new ObjectOutputStream(fos);
      ){
         oos.writeObject(memoTable);
      }catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public int updateMemo(MemoVO memo) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int deleteMemo(int code) {

   
      
      return 0;
   }

   

}