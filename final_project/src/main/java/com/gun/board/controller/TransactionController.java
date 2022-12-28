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

import com.gun.board.repository.BoardRepository;
import com.gun.board.repository.CustomerRepository;
import com.gun.board.repository.FriendRepository;
import com.gun.board.repository.ReplyRepository;
import com.gun.board.util.Configuration;
import com.gun.board.util.FileService;
import com.gun.board.util.Pagination;
import com.gun.board.vo.Board;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Reply;

@RequestMapping(value = "/boards_transaction")
@Controller
public class TransactionController {

   private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

   @Inject
   HttpSession session;
   
   @Inject
   BoardRepository bRepository;

   @Inject
   ReplyRepository rRepository;

   @Inject
   CustomerRepository cRepository;

   @Inject
   FriendRepository fRepository;

   Pagination Pagination = new Pagination();
    
   @RequestMapping(value = "/transaction", method = RequestMethod.GET)
   public String getTransaction(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value = "board_category_name", defaultValue = "") int board_category_name) {
      logger.info("거래게시판");
      String board_category = (String) session.getAttribute("게시판 카테고리");

      
      ArrayList<Board> boards = bRepository.getTransaction(board_category_name);
      int totalPages = Pagination.totalPages(boards);
      page = Pagination.getCurrentPage(page, totalPages);
      boards = Pagination.totalPosts(boards, page);
      int endPage = Pagination.endPage(page, totalPages);
      logger.info("총 페이지: " + totalPages + ", 끝페이지 :  " + endPage + " 현재페이지 :  " + page + " 게시물 수 : " + boards.size()
            + " status: " + (String) session.getAttribute("status"));
      model.addAttribute("boards", boards);
      model.addAttribute("page", page);
      model.addAttribute("endPage", endPage);
      model.addAttribute("board_category_name", board_category_name);
      System.out.println("board_category_name =" + board_category_name);
      return "boards/home";
   }

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertBoard() {
		logger.info("글 작성 화면으로 이동");
		return "boards/insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBoard(Board board, MultipartFile upload, Model model) {
		System.out.println("upload 파일명: " + upload);
		String board_id = (String) session.getAttribute("loginid");
		String board_nickname = cRepository.selectNickname(board_id);
		board.setBoard_id(board_id);
		board.setBoard_nickname(board_nickname);
		bRepository.insertBoard(board);
		if (!upload.isEmpty()) {
			System.out.println("upload file's name: " + upload.getOriginalFilename());
			String board_uploadfileid = FileService.saveFile(upload, Configuration.PHOTOPATH);
			board.setBoard_fileid(upload.getOriginalFilename());
			board.setBoard_uploadfileid(board_uploadfileid);
			bRepository.insertPhoto(board);
		}
		String friend_id = (String) session.getAttribute("loginid");
		int page = 1;
		ArrayList<Board> boards = bRepository.getBoards(friend_id);
		int totalPages = Pagination.totalPages(boards);
		page = Pagination.getCurrentPage(page, totalPages);
		boards = Pagination.totalPosts(boards, page);
		int endPage = Pagination.endPage(page, totalPages);
		model.addAttribute("boards", boards);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		return "boards/home";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getBoard(int board_num, Model model, int page, String friend_id) {
		String loginid = (String) session.getAttribute("loginid");

		Board board = bRepository.getBoard(board_num);
		if (!loginid.equals(board.getBoard_id())) {
			// 작성자는 자신이 작성한 글의 조회수를 올리지 못한다
			bRepository.upHits(board_num);
			board = bRepository.getBoard(board_num);
		}

		// 판매자와 로그인한 아이디를 비교
		String seller = board.getBoard_id();
		String status = fRepository.getStatus(loginid, seller);
		Customer seller_info = cRepository.selectCustomer(seller);

		// 만약 친구가 아니거나 null이면 failed 친구이면 successed
		if (status == null || !status.equals("friend")) {
			session.setAttribute("agree", "failed");
		} else if (status.equals("friend")) {
			session.setAttribute("agree", "successed");
		}

		// 구매자를 통해 가져온 친구 아이디와 구매자를 비교
		String buyer = fRepository.getfriend(loginid);
		String status2 = fRepository.getStatus(loginid, buyer);
		Customer buyer_info = cRepository.selectCustomer(buyer);

		if (status2 == null || !status2.equals("friend")) {
			session.setAttribute("agree2", "failed");
		} else if (status2.equals("friend")) {
			session.setAttribute("agree2", "successed");
		}

		ArrayList<String> request = fRepository.getRequestList(loginid, board_num);
		model.addAttribute("request", request);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("friend_id", friend_id);

		// 계좌랑 주소뽑을때
		model.addAttribute("seller_info", seller_info);
		model.addAttribute("buyer_info", buyer_info);
		logger.info("글읽기" + board);

		// 리플라이 부분
		ArrayList<Reply> reply = rRepository.getReplies(board_num);
		model.addAttribute("reply", reply);
		return "boards/get";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(int board_num, HttpServletResponse response) {

		Board board = bRepository.getBoard(board_num);

		String originalfile = board.getBoard_fileid();
		System.out.println("파일아이디 : " + board.getBoard_fileid());

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

	@RequestMapping(value = "/insertReply", method = RequestMethod.POST)
	public @ResponseBody int insertReply(int board_num, String reply_content,
			@RequestParam(value = "reply_num", defaultValue = "-1") int reply_num) {
		String reply_Id = (String) session.getAttribute("loginid");
		String reply_Nickname = (String) session.getAttribute("loginNickname");
		Reply newReply = new Reply();
		newReply.setBoard_num(board_num);
		newReply.setReply_content(reply_content);
		Reply reply = rRepository.getReply(reply_num);
		int result = 0;
		if (reply_num == -1) {
			// 원 댓글인 경우
			newReply.setReply_id(reply_Id);
			newReply.setReply_nickname(reply_Nickname);
		} else {
			// 대댓글인 경우
			newReply.setReply_id(reply.getReply_id());
			newReply.setReply_nickname(reply.getReply_nickname());
			newReply.setRreply_id(reply_Id);
			newReply.setRreply_nickname(reply_Nickname);
			newReply.setRreply_num(reply_num);
		}
		result = rRepository.insertReply(newReply);
		// 원댓글일 경우 rreply num 추가로 넣어주기
		if (reply_num == -1) {
			reply_num = rRepository.recentlyAddedReplynum();
			logger.info("최근에 등록한 댓글 번호 : " + reply_num);
			rRepository.updateRReply_num(reply_num);
		}
		// 0 : 댓글추가
		bRepository.changeReply(board_num, 0);
		logger.info("댓글 작성 결과 : " + result);
		return board_num;
	}

	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	public @ResponseBody int deleteReply(int reply_num, int board_num) {
		int result = rRepository.deleteReply(reply_num);
		// 1 : 댓글 삭제
		bRepository.changeReply(board_num, 1);
		logger.info("댓글 삭제 결과 : " + result);
		return board_num;
	}

	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	public @ResponseBody int updateReply(int reply_num, String reply_content, int board_num) {
		Reply reply = rRepository.getReply(reply_num);
		reply.setReply_content(reply_content);
		int result = rRepository.updateReply(reply);
		logger.info("댓글 수정");
		return board_num;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody int deleteBoard(Model model, int board_num) {
		Board board = bRepository.getBoard(board_num);
		boolean fileDeleteResult = false;
		if (board.getBoard_uploadfileid() != null) {
			fileDeleteResult = FileService.deleteFile(Configuration.PHOTOPATH + "/" + board.getBoard_uploadfileid());
		}
		int result = bRepository.deleteBoard(board_num);
		logger.info("글 삭제 : " + result + " , " + fileDeleteResult);
		return result;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public @ResponseBody String accept(String friend_id) {
		String cus_id = (String) session.getAttribute("loginid");
		int result = fRepository.accept(cus_id, friend_id);
		int numofFriendRequest = fRepository.numofFriendRequest(cus_id);
		session.setAttribute("numofFriendRequest", numofFriendRequest);
		logger.info("친구 수락 결과 : " + numofFriendRequest);
		return friend_id;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateBoard(int board_num, Model model, int page, String friend_id) {
		Board board = bRepository.getBoard(board_num);
		String loginid = (String) session.getAttribute("loginid");
		if (board.getBoard_fileid() != null) {
			FileService.deleteFile(board.getBoard_uploadfileid());
		}
		model.addAttribute("page", page);
		model.addAttribute("friend_id", friend_id);
		if (board.getBoard_id().equals(loginid)) {
			// 글쓴사람이랑 로그인한 사람이 같을 때만 업뎃이 가능하게
			model.addAttribute("board", board);
			return "boards/update";
		}
		return "boards/home";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBoard(int board_num, Board board, int page, String friend_id, Model model,
			MultipartFile upload) {
		Board originalBoard = bRepository.getBoard(board_num);
		String board_fileid = "";
		String board_uploadfileid = "";
		if (originalBoard.getBoard_fileid() != null) {
			board_fileid = originalBoard.getBoard_fileid();
			board_uploadfileid = originalBoard.getBoard_uploadfileid();
		}
		if (upload.getOriginalFilename() != null) {
			if (originalBoard.getBoard_uploadfileid() != null) {
				board_uploadfileid = originalBoard.getBoard_uploadfileid();
				FileService.deleteFile(board_uploadfileid);
			}
			board_uploadfileid = FileService.saveFile(upload, Configuration.PHOTOPATH);
			System.out.println("after adjustment: " + board_uploadfileid);
		}
		if (originalBoard.getBoard_fileid() != null || upload.getOriginalFilename() != null) {
			board.setBoard_fileid(board_fileid);
			board.setBoard_uploadfileid(board_uploadfileid);
		}
		int result = bRepository.updateBoard(board);
		logger.info("글 수정 결과: " + board.toString());
		ArrayList<Board> boards = bRepository.getBoards(friend_id);
		int totalPages = Pagination.totalPages(boards);
		page = Pagination.getCurrentPage(page, totalPages);
		boards = Pagination.totalPosts(boards, page);
		int endPage = Pagination.endPage(page, totalPages);
		model.addAttribute("boards", boards);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		return "boards/home";
	}

	@RequestMapping(value = "/friendRequest", method = RequestMethod.POST)
	public String friendRequest(String friend_id, Model model,
			@RequestParam(value = "searchType", defaultValue = "") String searchType,
			@RequestParam(value = "searchContent", defaultValue = "") String searchContent) {
		String cus_id = (String) session.getAttribute("loginid");
		String checkRelationship = fRepository.getStatus(cus_id, friend_id);
		if (checkRelationship == null) {
			int result = fRepository.friendRequest(cus_id, friend_id);
			logger.info("친구 추가 : " + result);
			model.addAttribute("requestResult", friend_id + "님에게 거래 요청을 전송하였습니다");
		} else if (checkRelationship.equals("request")) {
			model.addAttribute("requestResult", friend_id + "님에게 이미 거래 요청을 전송하였습니다.");
		} else if (checkRelationship.equals("friend")) {
			model.addAttribute("requestResult", friend_id + "거래수락 상태입니다.");
		}
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchContent", searchContent);
		ArrayList<Customer> friends = fRepository.findFriends(searchType, searchContent);
		model.addAttribute("friends", friends);
		return "friend/home";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String gethome(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "friend_id", defaultValue = "") String friend_id) {
		logger.info("게시판 홈");
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

		ArrayList<Board> boards = bRepository.getBoards(friend_id);
		int totalPages = Pagination.totalPages(boards);
		page = Pagination.getCurrentPage(page, totalPages);
		boards = Pagination.totalPosts(boards, page);
		int endPage = Pagination.endPage(page, totalPages);
		logger.info("총 페이지: " + totalPages + ", 끝페이지 :  " + endPage + " 현재페이지 :  " + page + " 게시물 수 : " + boards.size()
				+ " status: " + (String) session.getAttribute("status"));
		model.addAttribute("boards", boards);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		System.out.println("friend_id =" + friend_id);
		return "boards/home";
	}

}