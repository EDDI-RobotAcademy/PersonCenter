package com.gun.board.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gun.board.repository.CustomerRepository;
import com.gun.board.repository.FriendRepository;
import com.gun.board.repository.NoticeRepository;
import com.gun.board.repository.ReplyRepository;
import com.gun.board.util.Configuration;
import com.gun.board.util.FileService;
import com.gun.board.util.N_Pagination;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Notice;
import com.gun.board.vo.Reply;

@RequestMapping(value = "/notice")
@Controller
public class N_BoardController {

   private static final Logger logger = LoggerFactory.getLogger(N_BoardController.class);
   
   
   @Inject
   HttpSession session;
   
   @Inject
   NoticeRepository nRepository;

   @Inject
   ReplyRepository rRepository;

   @Inject
   CustomerRepository cRepository;
   
   @Inject
   FriendRepository fRepository;

   N_Pagination Pagination = new N_Pagination();
   
   @RequestMapping(value = "", method = RequestMethod.GET)
   public String getBoards(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value = "friend_id", defaultValue = "") String friend_id) {
      
      String cus_id = (String) session.getAttribute("loginid");
      System.out.println("cus_id : " + cus_id);    
      if (friend_id.equals("") || friend_id.equals(cus_id)) {
         friend_id = cus_id;
         session.setAttribute("status", "myself");
      }
      
      else {
         String status = fRepository.getStatus(cus_id, friend_id);
         if (status == null || !status.equals("friend")) {
            session.setAttribute("status", "notYet");
         }
         else if (status.equals("friend")) {
            session.setAttribute("status", "friend");
         }
      }
      
      ArrayList<Notice> boards = nRepository.getBoards(cus_id);
      
      
      int totalPages = Pagination.totalPages(boards);
      page = Pagination.getCurrentPage(page, totalPages);
      boards = Pagination.totalPosts(boards, page);
      int endPage = Pagination.endPage(page, totalPages);
      logger.info("         : " + totalPages + ",          :  " + endPage + "            :  " + page + "  Խù     : " + boards.size()
            + " status: " + (String) session.getAttribute("status"));
      
   
      model.addAttribute("page", page);
      model.addAttribute("endPage", endPage);
      
      /*
      model.addAttribute("friend_id", friend_id);
      System.out.println("friend_id =" + friend_id);
      */
      
      logger.info("boards :  " , boards);
      System.out.println("boards :  " + boards);
      model.addAttribute("boards", boards);
      return "notice/n_home";
   }
    

   @RequestMapping(value = "/insert", method = RequestMethod.GET)
   public String insertBoard() {
      logger.info("글 작성 화면으로 이동");
      return "notice/n_insert";
   }

   @RequestMapping(value = "/insert", method = RequestMethod.POST)
   public String insertBoard(Notice board, MultipartFile upload, Model model) {
      
      
      String board_no_id = (String) session.getAttribute("loginid");
      String board_no_nickname = cRepository.selectNickname(board_no_id);
      board.setBoard_no_id(board_no_id);
      board.setBoard_no_nickname(board_no_nickname);
      nRepository.insertBoard(board);
      
      ArrayList<Notice> boards = nRepository.getBoards(board_no_id);
      
     
      int page = 1;
      
      int totalPages = Pagination.totalPages(boards);
      page = Pagination.getCurrentPage(page, totalPages);
      boards = Pagination.totalPosts(boards, page);
      int endPage = Pagination.endPage(page, totalPages);
      
      model.addAttribute("page", page);
      model.addAttribute("endPage", endPage);
      System.out.println("board_no_id : " + board_no_id);
		/* model.addAttribute("friend_id", friend_id); */
      
      logger.info("  Ʈ ѷ  insertBoard     @ ");
      
      
      model.addAttribute("boards", boards);
      
         return "notice/n_home";
   }

   @RequestMapping(value = "/get", method = RequestMethod.GET)
   public String getBoard(int board_num, Model model, int page, String friend_id) {
      String loginid = (String) session.getAttribute("loginid");
      int result  = nRepository.countUp(board_num);
      Notice board = nRepository.getBoard(board_num);
      
      model.addAttribute("board", board);
      model.addAttribute("page", page);
    
      return "notice/n_get";
   }

   @RequestMapping(value = "/download", method = RequestMethod.GET)
   public String download(int board_num, HttpServletResponse response) {

      Notice board = nRepository.getBoard(board_num);

      String originalfile = board.getBoard_fileid();

      try {
         response.setHeader("Content-Disposition",
               "attachment;filename=" + URLEncoder.encode(originalfile, "UTF-8"));
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      String fullpath = Configuration.PHOTOPATH + "/" + board.getBoard_uploadfileid();

      ServletOutputStream fileout = null;
      FileInputStream filein = null;

      try {
         filein = new FileInputStream(fullpath);
         fileout = response.getOutputStream();
         FileCopyUtils.copy(filein, fileout);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            if (filein != null) {
               filein.close();
            }
            if (fileout != null) {
               fileout.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return null;
   }


   @RequestMapping(value = "/delete", method = RequestMethod.POST)
   public @ResponseBody int deleteBoard(Model model, int board_num) {
      Notice board = nRepository.getBoard(board_num);
      boolean fileDeleteResult = false;
      if (board.getBoard_uploadfileid() != null) {
         fileDeleteResult = FileService.deleteFile(Configuration.PHOTOPATH + "/" + board.getBoard_uploadfileid());
      }
      int result = nRepository.deleteBoard(board_num);
      logger.info("        : " + result + " , " + fileDeleteResult);
      return result;
   }

   @RequestMapping(value = "/update", method = RequestMethod.GET)
   public String updateBoard(int board_num, Model model, int page, String friend_id) {
      Notice board = nRepository.getBoard(board_num);
      String loginid = (String) session.getAttribute("loginid");
      if (board.getBoard_fileid() != null) {
         FileService.deleteFile(board.getBoard_uploadfileid());
      }
      model.addAttribute("page", page);
      model.addAttribute("friend_id", friend_id);
      if (board.getBoard_no_id().equals(loginid)) {
         //  ۾     ̶   α                                  ϰ 
         model.addAttribute("board", board);
         return "notice/n_update";
      }
      return "notice/n_home";
   }

   @RequestMapping(value = "/update", method = RequestMethod.POST)
   public String updateBoard(int board_num, Notice board, int page, String friend_id, Model model) {
 
      String loginid = (String) session.getAttribute("loginid");

      int result = nRepository.updateBoard(board);
      logger.info("           : " + board.toString());
      ArrayList<Notice> boards = nRepository.getBoards(loginid);
      int totalPages = Pagination.totalPages(boards);
      page = Pagination.getCurrentPage(page, totalPages);
      boards = Pagination.totalPosts(boards, page);
      int endPage = Pagination.endPage(page, totalPages);
      model.addAttribute("boards", boards);
      model.addAttribute("page", page);
      model.addAttribute("endPage", endPage);
      model.addAttribute("friend_id", friend_id);
      return "notice/n_home";
   }
   
   @RequestMapping(value = "home", method = RequestMethod.GET)
   public String gethome(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value = "friend_id", defaultValue = "") String friend_id) {
      logger.info(" Խ    Ȩ");
      String cus_id = (String) session.getAttribute("loginid");
      if (friend_id.equals("") || friend_id.equals(cus_id)) {
         friend_id = cus_id;
         session.setAttribute("status", "myself");
      } else {
         String status = fRepository.getStatus(cus_id, friend_id);
         if (status == null || !status.equals("friend")) {
            session.setAttribute("status", "notYet");
         } else if (status.equals("friend")) {
            session.setAttribute("status", "friend");
         }
      }
      
      ArrayList<Notice> boards = nRepository.getBoards(friend_id);
      int totalPages = Pagination.totalPages(boards);
      page = Pagination.getCurrentPage(page, totalPages);
      boards = Pagination.totalPosts(boards, page);
      int endPage = Pagination.endPage(page, totalPages);
      logger.info("         : " + totalPages + ",          :  " + endPage + "            :  " + page + "  Խù     : " + boards.size()
            + " status: " + (String) session.getAttribute("status"));
      model.addAttribute("boards", boards);
      model.addAttribute("page", page);
      model.addAttribute("endPage", endPage);
      model.addAttribute("friend_id", friend_id);
      System.out.println("friend_id =" + friend_id);
      return "notice/n_home";
   }
   
     
   @RequestMapping(value = "/findNotice", method = RequestMethod.POST)
   public String findNotice(Model model, @RequestParam(value = "searchType", defaultValue = "" ) String searchType,
         @RequestParam(value = "searchContent", defaultValue = "") String searchContent) {
      
      int page = 1;
      int result = 0;
      ArrayList<Notice> searchNotice = new ArrayList();
      searchNotice = nRepository.findNotice(searchType, searchContent);
      int totalPages = Pagination.totalPages(searchNotice);
      page = Pagination.getCurrentPage(page, totalPages);
      searchNotice = Pagination.totalPosts(searchNotice, page);
      int endPage = Pagination.endPage(page, totalPages);
      
      logger.info("searchType: " + searchType + ", searchContent: " + searchContent + " , " + searchNotice.size());
      System.out.println("searchType: " + searchType + ", searchContent: " + searchContent + " , " + searchNotice.size());
      System.out.println("searchNotice : " + searchNotice);
      
      model.addAttribute("boards", searchNotice);
      model.addAttribute("page", page);
      model.addAttribute("endPage", endPage);
      
      result = searchNotice.size();
      return "notice/n_home";
   }

   
}