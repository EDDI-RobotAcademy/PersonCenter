package com.gun.board.util;

import java.util.ArrayList;

import com.gun.board.vo.Free;
import com.gun.board.vo.Message;

public class Pagination_Free {
	// 페이징 처리 위한 클래스


	// 총 페이지 계산
   public int totalPages(ArrayList<Free> boards_free) {
      int result = 0;
      result = (boards_free.size() / Configuration.POSTS);
      if (boards_free.size() % Configuration.POSTS != 0 || result == 0) {
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

   // 마지막 페이지 구하기
   public int endPage(int page, int totalPages) {
      int endPage = 0;
      endPage = page + Configuration.PAGES;
      if (endPage > totalPages) {
         endPage = totalPages;
      }
      return endPage;
   }

   // 한 페이지에 나타나는 게시물
   public ArrayList<Free> totalPosts(ArrayList<Free> free, int page) {
      ArrayList<Free> result = new ArrayList();
      int startPost = (page - 1) * Configuration.POSTS;
      int endPost = startPost + Configuration.POSTS;
      if (endPost > free.size()) {
         endPost = free.size();
      }
      for (int a = startPost; a < endPost; a++) {
         result.add(free.get(a));
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

   // 페이지 수 조정
   public int getCurrentPage(int page, int totalPages) {
      if (page <= 0) {
         page = 1;
      } else if (page > totalPages && totalPages != 0) {
         page = totalPages;
      }
      return page;
   }

   public ArrayList<Free> totalPosts_home_free(ArrayList<Free> free, int page) {
          ArrayList<Free> result = new ArrayList();
              int startPost = (page - 1) * Configuration.POSTS_HOME;
              int endPost = startPost + Configuration.POSTS_HOME;
              if (endPost > free.size()) {
                  endPost = free.size();
               }
               for (int a = startPost; a < endPost; a++) {
                     result.add(free.get(a));
               }
               return result;
      }
}