package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.BoardDAO;
import com.gun.board.dao.FriendDAO;
import com.gun.board.dao.NoticeDAO;
import com.gun.board.vo.Board;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Notice;

@Repository
public class NoticeRepository {
   @Inject
   private SqlSession sqlSession;

   NoticeDAO bdao;

   public int insertBoard(Notice board) {
      int result = 0;
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.insertBoard(board);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public int insertPhoto(Notice board) {
      int result = 0;
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.insertPhoto(board);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public ArrayList<Notice> getBoards(String cus_id) {
      ArrayList<Notice> result = new ArrayList();
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.getBoards(cus_id);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public Notice getBoard(int board_num) {
      Notice result = new Notice();
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.getBoard(board_num);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public void upHits(int board_num) {
      // 조회       리기
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         bdao.upHits(board_num);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public int deleteBoard(int board_num) {
      int result = 0;
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.deleteBoard(board_num);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public int updateBoard(Notice board) {
      int result = 0;
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.updateBoard(board);
         bdao.updatePhoto(board);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public int updatePhoto(Notice board) {
      int result = 0;
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.updatePhoto(board);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public void changeReply(int board_num, int type) {
      bdao = sqlSession.getMapper(NoticeDAO.class);
      Map<String, Integer> change = new HashMap();
      change.put("board_num", board_num);
      change.put("type", type);
      try {
         bdao.changeReply(change);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public int countUp(int board_num) {
      int result = 0;
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.countUp(board_num);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public ArrayList<Notice> findNotice(String searchType, String searchContent) {
      Map<String, String> search = new HashMap();
      
      search.put("searchType", searchType);
      search.put("searchContent", searchContent);
      System.out.println("searchType @@@ :  " + searchType);
      ArrayList<Notice> result = new ArrayList();
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.findNotice(search);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public ArrayList<Notice> getSort(String sortValue) {
      Map sortNotice = new HashMap();
      sortNotice.put("sortValue", sortValue);
      
      ArrayList<Notice> result = new ArrayList();
      System.out.println("sortValue : " +sortValue);
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.getSort(sortNotice);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }

   public ArrayList<Notice> getNotice_home() {
      ArrayList<Notice> result = new ArrayList();
      bdao = sqlSession.getMapper(NoticeDAO.class);
      try {
         result = bdao.getNotice_home();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
   }
   
   
   
   
   
   
   
   
   
}