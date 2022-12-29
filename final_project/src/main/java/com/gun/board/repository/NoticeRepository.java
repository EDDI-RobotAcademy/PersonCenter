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
         
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
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