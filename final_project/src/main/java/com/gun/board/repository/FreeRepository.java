package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.FreeDAO;
import com.gun.board.dao.FriendDAO;
import com.gun.board.dao.NoticeDAO;
import com.gun.board.dao.BoardDAO;
import com.gun.board.dao.CustomerDAO;
import com.gun.board.vo.Board;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Free;
import com.gun.board.vo.Notice;

@Repository
public class FreeRepository {

   @Inject
   private SqlSession sqlSession;

   FreeDAO fdao;

   public int insertBoards_free(Free free) {
      int result = 0;
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.insertBoards_free(free);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public int insertPhoto_free(Free free) {
      int result = 0;
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.insertPhoto_free(free);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public ArrayList<Free> getFree(String friend_id) {
      ArrayList<Free> result = new ArrayList();
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.getFree(friend_id);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }
   
   public ArrayList<Free> getFree_home() {
      ArrayList<Free> result = new ArrayList();
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.getFree_home();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }
   
   public Free getBoards_free(int board_num) {
      Free result = new Free();
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.getBoards_free(board_num);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public void upHits_free(int board_num) {
	   // 조회수 올리기
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         fdao.upHits_free(board_num);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public int deleteBoards_free(int board_num) {
      int result = 0;
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.deleteBoards_free(board_num);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public int updateBoards_free(Free free) {
      int result = 0;
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.updateBoards_free(free);
         fdao.updatePhoto_free(free);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public int updatePhoto_free(Free free) {
      int result = 0;
      fdao = sqlSession.getMapper(FreeDAO.class);
      try {
         result = fdao.updatePhoto_free(free);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public void changeReply_free(int board_num, int type) {
      fdao = sqlSession.getMapper(FreeDAO.class);
      Map<String, Integer> change = new HashMap();
      change.put("board_num", board_num);
      change.put("type", type);
      try {
         fdao.changeReply_free(change);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   //게시판 검색
   public ArrayList<Free> findfree(String searchType, String searchContent) {
	      Map<String, String> search = new HashMap();
	      
	      search.put("searchType", searchType);
	      search.put("searchContent", searchContent);
	      System.out.println("자유게시판 검색 :  " + searchType);
	      ArrayList<Free> result = new ArrayList();
	      fdao = sqlSession.getMapper(FreeDAO.class);
	      try {
	         result = fdao.findfree(search);
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return result;
	   }
   
   public ArrayList<Free> getSort(String sortValue) {
	      Map sortFree = new HashMap();
	      sortFree.put("sortValue", sortValue);
	      
	      ArrayList<Free> result = new ArrayList();
	      System.out.println("sortValue : " +sortValue);
	      fdao = sqlSession.getMapper(FreeDAO.class);
	      try {
	         result = fdao.getSort(sortFree);
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return result;
	   }
}