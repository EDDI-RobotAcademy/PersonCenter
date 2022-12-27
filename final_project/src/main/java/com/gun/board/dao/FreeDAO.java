package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;
import com.gun.board.vo.Free;
import com.gun.board.vo.Notice;

public interface FreeDAO {

   public int insertBoards_free(Free free) throws Exception;

   public int insertPhoto_free(Free free) throws Exception;

   public ArrayList<Free> getFree(String friend_id) throws Exception;
   
   public ArrayList<Free> getFree_home() throws Exception;

   public Free getBoards_free(int board_num) throws Exception;

   public void upHits_free(int board_num) throws Exception;

   public int deleteBoards_free(int board_num) throws Exception;

   public int deletePhoto_free(Free free) throws Exception;

   public int updateBoards_free(Free free) throws Exception;

   public int updatePhoto_free(Free free) throws Exception;

   public void changeReply_free(Map<String, Integer> change) throws Exception;
   
   public ArrayList<Free> findfree(Map<String, String> search)throws Exception;
   
   public ArrayList<Free> getSort(Map sortNotice)throws Exception;
}