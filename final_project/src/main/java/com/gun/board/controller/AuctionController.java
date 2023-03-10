package com.gun.board.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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

import com.gun.board.repository.AuctionRepository;
import com.gun.board.repository.CustomerRepository;
import com.gun.board.repository.FriendRepository;
import com.gun.board.repository.ReplyRepository;
import com.gun.board.util.Configuration;
import com.gun.board.util.FileService;
import com.gun.board.util.Pagination_Auction;
import com.gun.board.vo.Auction;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Free;
import com.gun.board.vo.Reply;

@RequestMapping(value = "/boards_auction")
@Controller
public class AuctionController {

	private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);

	@Inject
	HttpSession session;

	@Inject
	AuctionRepository aRepository;

	@Inject
	ReplyRepository rRepository;

	@Inject
	CustomerRepository cRepository;

	@Inject
	FriendRepository fRepository;

	Pagination_Auction Paginationa = new Pagination_Auction();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getBoards(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "friend_id", defaultValue = "") String friend_id) {
		logger.info("????????? ???");
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

		ArrayList<Auction> auction = aRepository.getBoards(friend_id);
		int totalPages = Paginationa.totalPages(auction);
		page = Paginationa.getCurrentPage(page, totalPages);
		auction = Paginationa.totalPosts(auction, page);
		int endPage = Paginationa.endPage(page, totalPages);
		logger.info("??? ?????????: " + totalPages + ", ???????????? :  " + endPage + " ??????????????? :  " + page + " ????????? ??? : " + auction.size()
				+ " status: " + (String) session.getAttribute("status"));
		model.addAttribute("auction", auction);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		System.out.println("friend_id =" + friend_id);
		return "boards_auction/a_home";
	}

	@RequestMapping(value = "/a_insert", method = RequestMethod.GET)
	public String insertBoard() {
		logger.info("??? ?????? ???????????? ??????");
		return "boards_auction/a_insert";
	}

	@RequestMapping(value = "/a_insert", method = RequestMethod.POST)
	public String insertBoard(Auction auction, MultipartFile upload, Model model) {
		System.out.println("upload ?????????: " + upload);
		String board_id = (String) session.getAttribute("loginid");
		String board_nickname = cRepository.selectNickname(board_id);
		auction.setBoard_id(board_id);
		auction.setBoard_nickname(board_nickname);
		aRepository.insertBoard(auction);
		if (!upload.isEmpty()) {
			System.out.println("upload file's name: " + upload.getOriginalFilename());
			String board_uploadfileid = FileService.saveFile(upload, Configuration.PHOTOPATH);
			auction.setBoard_fileid(upload.getOriginalFilename());
			auction.setBoard_uploadfileid(board_uploadfileid);
			aRepository.insertPhoto(auction);
		}
		String friend_id = (String) session.getAttribute("loginid");
		int page = 1;
		ArrayList<Auction> auctions = aRepository.getBoards(friend_id);
		int totalPages = Paginationa.totalPages(auctions);
		page = Paginationa.getCurrentPage(page, totalPages);
		auctions = Paginationa.totalPosts(auctions, page);
		int endPage = Paginationa.endPage(page, totalPages);
		model.addAttribute("auction", auctions);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);

		return "redirect:/boards_auction";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getBoard(Model model, int page, String friend_id, int board_num) {
		String loginid = (String) session.getAttribute("loginid");

		Auction auction = aRepository.getBoard(board_num);
		
		// ???????????? ????????? ????????? ?????? ???????????? ????????? ?????????
		if (!loginid.equals(auction.getBoard_id())) {
			aRepository.upHits(board_num);
			auction = aRepository.getBoard(board_num);
		}
		
		// ???????????? ????????? ????????? ????????????
		String seller = auction.getBoard_id();
		String buyer = fRepository.getfriend_2(loginid, board_num);

		// ???????????? ????????? ????????? ?????????????????? ????????? ????????? ???????????????.
		String board_status = fRepository.getStatus_board(seller, board_num); //????????? ????????? ????????????
		String status_seller = fRepository.getStatus_2(loginid, seller, board_num);
		String status_buyer = fRepository.getStatus_2(loginid, buyer, board_num);
		
		// ????????? ??? ???????????? ????????? ????????????(???????????? ???)
		Customer seller_info = cRepository.selectCustomer(seller);
		Customer buyer_info = cRepository.selectCustomer(buyer);
		
		//????????? ????????? ???????????? seller??? buyer??? ????????? ??????
		model.addAttribute("status_seller", status_seller);
		model.addAttribute("status_buyer", status_buyer);
		
		// ?????? ??????????????? ???????????? null?????? failed ???????????? successed
		if (status_seller == null || !status_seller.equals("friend")) {
			session.setAttribute("agree", "failed");
		} else if (status_seller.equals("friend")) {
			session.setAttribute("agree", "successed");
		}
		System.out.println("board_status : " + board_status);
		System.out.println("status_buyer : " + status_buyer);
		System.out.println("status_seller : " + status_seller);

		if (status_buyer == null || !status_buyer.equals("friend")) {
			session.setAttribute("agree2", "failed");
		} else if (status_buyer.equals("friend")) {
			session.setAttribute("agree2", "successed");
		}

		// ???????????? ?????? ??????(???????????? ????????? ???????????? ????????? ??????)
		ArrayList<String> request = fRepository.getRequestList(loginid, board_num);

		// ???????????? ??????
		ArrayList<Reply> reply = rRepository.getReplies(board_num);

		
		model.addAttribute("board_status", board_status);
		model.addAttribute("request", request);
		model.addAttribute("auction", auction);
		model.addAttribute("page", page);
		model.addAttribute("friend_id", friend_id);
		model.addAttribute("seller_info", seller_info);
		model.addAttribute("buyer_info", buyer_info);
		model.addAttribute("reply", reply);
		logger.info("??????????????? : " + auction);

		return "boards_auction/a_get";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(int board_num, HttpServletResponse response) {

		Auction auction = aRepository.getBoard(board_num);

		String originalfile = auction.getBoard_fileid();
		System.out.println("??????????????? : " + auction.getBoard_fileid());

		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fullpath = Configuration.PHOTOPATH + "/" + auction.getBoard_uploadfileid();

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
			// ??? ????????? ??????
			newReply.setReply_id(reply_Id);
			newReply.setReply_nickname(reply_Nickname);
		} else {
			// ???????????? ??????
			newReply.setReply_id(reply.getReply_id());
			newReply.setReply_nickname(reply.getReply_nickname());
			newReply.setRreply_id(reply_Id);
			newReply.setRreply_nickname(reply_Nickname);
			newReply.setRreply_num(reply_num);
		}
		result = rRepository.insertReply(newReply);
		// ???????????? ?????? rreply num ????????? ????????????
		if (reply_num == -1) {
			reply_num = rRepository.recentlyAddedReplynum();
			logger.info("????????? ????????? ?????? ?????? : " + reply_num);
			rRepository.updateRReply_num(reply_num);
		}
		// 0 : ????????????
		aRepository.changeReply(board_num, 0);
		logger.info("?????? ?????? ?????? : " + result);
		return board_num;
	}

	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	public @ResponseBody int deleteReply(int reply_num, int board_num) {
		int result = rRepository.deleteReply(reply_num);
		// 1 : ?????? ??????
		aRepository.changeReply(board_num, 1);
		logger.info("?????? ?????? ?????? : " + result);
		return board_num;
	}

	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	public @ResponseBody int updateReply(int reply_num, String reply_content, int board_num) {
		Reply reply = rRepository.getReply(reply_num);
		reply.setReply_content(reply_content);
		int result = rRepository.updateReply(reply);
		logger.info("?????? ??????");
		return board_num;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody int deleteBoard(Model model, int board_num) {
		Auction auction = aRepository.getBoard(board_num);
		boolean fileDeleteResult = false;
		if (auction.getBoard_uploadfileid() != null) {
			fileDeleteResult = FileService.deleteFile(Configuration.PHOTOPATH + "/" + auction.getBoard_uploadfileid());
		}
		int result = aRepository.deleteBoard(board_num);
		logger.info("??? ?????? : " + result + " , " + fileDeleteResult);
		return result;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public @ResponseBody String accept(String friend_id, int board_num) {

		System.out.println("board_num : " + board_num);

		String cus_id = (String) session.getAttribute("loginid");
		int result = fRepository.accept_2(cus_id, friend_id, board_num);
		int numofFriendRequest = fRepository.numofFriendRequest(cus_id);
		session.setAttribute("numofFriendRequest", numofFriendRequest);
		logger.info("?????? ?????? ?????? : " + numofFriendRequest);
		return friend_id;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateBoard(int board_num, Model model, int page, String friend_id) {
		Auction auction = aRepository.getBoard(board_num);
		String loginid = (String) session.getAttribute("loginid");
		if (auction.getBoard_fileid() != null) {
			FileService.deleteFile(auction.getBoard_uploadfileid());
		}
		model.addAttribute("page", page);
		model.addAttribute("friend_id", friend_id);
		if (auction.getBoard_id().equals(loginid)) {
			// ?????????????????? ???????????? ????????? ?????? ?????? ????????? ????????????
			model.addAttribute("auction", auction);
			return "boards_auction/a_update";
		}
		return "boards_auction/a_home";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBoard(int board_num, Auction auction, int page, String friend_id, Model model,
			MultipartFile upload) {
		Auction originalBoard = aRepository.getBoard(board_num);
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
			auction.setBoard_fileid(board_fileid);
			auction.setBoard_uploadfileid(board_uploadfileid);
		}
		int result = aRepository.updateBoard(auction);
		logger.info("??? ?????? ??????: " + auction.toString());
		ArrayList<Auction> boards = aRepository.getBoards(friend_id);
		int totalPages = Paginationa.totalPages(boards);
		page = Paginationa.getCurrentPage(page, totalPages);
		boards = Paginationa.totalPosts(boards, page);
		int endPage = Paginationa.endPage(page, totalPages);
		model.addAttribute("auction", auction);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		return "boards_auction/a_home";
	}

	@RequestMapping(value = "/friendRequest", method = RequestMethod.POST)
	public String friendRequest(String friend_id, Model model,int board_num,
			@RequestParam(value = "searchType", defaultValue = "") String searchType,
			@RequestParam(value = "searchContent", defaultValue = "") String searchContent,
			HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		
		String cus_id = (String) session.getAttribute("loginid");
		String checkRelationship = fRepository.getStatus_2(cus_id, friend_id, board_num);
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		 
		if (checkRelationship == null) {
			int result = fRepository.friendRequest_2(cus_id, friend_id,board_num);
			logger.info("?????? ?????? : " + result);
			out.println("<script>" + "alert('??????????????? ???????????????.');"+ "history.go(-1);"+ "</script>"); 
			out.flush();
			
		} else if (checkRelationship.equals("request")) {
			out.println("<script>" + "alert('????????? ?????? ?????? ????????? ?????????????????????.');"+ "history.go(-1);"+ "</script>"); 
			out.flush();
			
		} else if (checkRelationship.equals("friend")) {
			out.println("<script>" + "alert('???????????????????????????.');"+ "history.go(-1);"+ "</script>"); 
			out.flush();
		}
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchContent", searchContent);
		ArrayList<Customer> friends = fRepository.findFriends(searchType, searchContent);
		model.addAttribute("friends", friends);
		
		System.out.println(checkRelationship);
		 //????????? ????????????
	

	
		return null;
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String gethome(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "friend_id", defaultValue = "") String friend_id) {
		logger.info("????????? ???");
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

		ArrayList<Auction> auction = aRepository.getBoards(friend_id);
		int totalPages = Paginationa.totalPages(auction);
		page = Paginationa.getCurrentPage(page, totalPages);
		auction = Paginationa.totalPosts(auction, page);
		int endPage = Paginationa.endPage(page, totalPages);
		logger.info("??? ?????????: " + totalPages + ", ???????????? :  " + endPage + " ??????????????? :  " + page + " ????????? ??? : " + auction.size()
				+ " status: " + (String) session.getAttribute("status"));
		model.addAttribute("auction", auction);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		System.out.println("friend_id =" + friend_id);
		return "boards_auction/a_home";
	}
	
	@RequestMapping(value = "/findauction", method = RequestMethod.POST)
	   public String findauction(Model model, @RequestParam(value = "searchType", defaultValue = "" ) String searchType,
	         @RequestParam(value = "searchContent", defaultValue = "") String searchContent) {
	      
	      int page = 1;
	      int result = 0;
	      ArrayList<Auction> findAuction = new ArrayList();
	      findAuction = aRepository.findauction(searchType, searchContent);
	      int totalPages = Paginationa.totalPages(findAuction);
	      page = Paginationa.getCurrentPage(page, totalPages);
	      findAuction = Paginationa.totalPosts(findAuction, page);
	      int endPage = Paginationa.endPage(page, totalPages);
	      
	      logger.info("searchType: " + searchType + ", searchContent: " + searchContent + " , " + findAuction.size());
	      System.out.println("searchType: " + searchType + ", searchContent: " + searchContent + " , " + findAuction.size());
	      System.out.println("searchFree : " + findAuction);
	      
	      model.addAttribute("auction", findAuction);
	      model.addAttribute("page", page);
	      model.addAttribute("endPage", endPage);
	      
	      result = findAuction.size();
	      return "boards_auction/a_home";
	   }
	   
	   // ??????????????????????????? ????????????
	   @RequestMapping(value ="/SortAuction", method = RequestMethod.GET)
	   public  String SortAuction(String sortValue, Model model) {   
	 
	      System.out.println("??????????????????????????? ???????????? ????????????~?~!? @@ sortValue : " + sortValue);
	      ArrayList<Auction> auction = aRepository.getSort(sortValue);
	      
	      int page = 1;
	     
	     int totalPages = Paginationa.totalPages(auction);
	     page = Paginationa.getCurrentPage(page, totalPages);
	     auction = Paginationa.totalPosts(auction, page);
	     int endPage = Paginationa.endPage(page, totalPages);
	     
	     model.addAttribute("page", page);
	     model.addAttribute("endPage", endPage);
	     model.addAttribute("auction", auction);
	     
	        return "boards_auction/a_home";
	   }
}