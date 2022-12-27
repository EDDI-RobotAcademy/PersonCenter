package com.gun.board.util;

import java.util.ArrayList;

import com.gun.board.vo.Notice;
import com.gun.board.vo.Message;

public class N_Pagination {
           
   public int totalPages(ArrayList<Notice> boards) {
      int result = 0;
      result = (boards.size() / Configuration.POSTS);
      if (boards.size() % Configuration.POSTS != 0 || result == 0) {
         result += 1;
      }
      return result;
   }
   
   public int totalPagesMessage(ArrayList<Message> message) {
      int result = 0;
      result = (message.size() / Configuration.POSTS);
      if (message.size() % Configuration.POSTS != 0 || result == 0) {
         result += 1;
      }
      return result;
   }

   //                  ϱ 
   public int endPage(int page, int totalPages) {
      int endPage = 0;
      endPage = page + Configuration.PAGES;
      if (endPage > totalPages) {
         endPage = totalPages;
      }
      return endPage;
   }

   //               Ÿ      Խñ 
   public ArrayList<Notice> totalPosts(ArrayList<Notice> boards, int page) {
      ArrayList<Notice> result = new ArrayList();
      int startPost = (page - 1) * Configuration.POSTS;
      int endPost = startPost + Configuration.POSTS;
      if (endPost > boards.size()) {
         endPost = boards.size();
      }
      for (int a = startPost; a < endPost; a++) {
         result.add(boards.get(a));
      }
      return result;
   }

   public ArrayList<Message> totalPostsMessage(ArrayList<Message> message, int page) {
      ArrayList<Message> result = new ArrayList();
      int startPost = (page - 1) * Configuration.POSTS;
      int endPost = startPost + Configuration.POSTS;
      if (endPost > message.size()) {
         endPost = message.size();
      }
      for (int a = startPost; a < endPost; a++) {
         result.add(message.get(a));
      }
      return result;
   }

   //               
   public int getCurrentPage(int page, int totalPages) {
      if (page <= 0) {
         page = 1;
      } else if (page > totalPages && totalPages != 0) {
         page = totalPages;
      }
      return page;
   }

}