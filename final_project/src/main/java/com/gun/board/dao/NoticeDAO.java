package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.gun.board.vo.Notice;



public interface NoticeDAO {
   public int insertBoard(Notice board) throws Exception;

   public int insertPhoto(Notice board) throws Exception;

   public ArrayList<Notice> getBoards(String friend_id) throws Exception;

   public Notice getBoard(int board_num) throws Exception;

   public void upHits(int board_num) throws Exception;

   public int deleteBoard(int board_num) throws Exception;

   public int deletePhoto(Notice board) throws Exception;

   public int updateBoard(Notice board) throws Exception;

   public int updatePhoto(Notice board) throws Exception;

   public void changeReply(Map<String, Integer> change) throws Exception;

   public int countUp(int board_num)throws Exception;
 
   public ArrayList<Notice> findNotice(Map<String, String> search)throws Exception;
  
   public ArrayList<Notice> getSort(Map sortNotice)throws Exception;

   public ArrayList<Notice> getNotice_home()throws Exception;
}