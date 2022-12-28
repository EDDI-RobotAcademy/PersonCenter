package com.gun.board.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gun.board.repository.DataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gun.board.repository.CustomerRepository;
import com.gun.board.repository.FriendRepository;
import com.gun.board.repository.ReplyDataRepository;
import com.gun.board.util.Configuration;
import com.gun.board.util.FileService;
import com.gun.board.util.Pagination_Data;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Data;
import com.gun.board.vo.Free;
import com.gun.board.vo.Notice;
import com.gun.board.vo.Reply_Data;

@RequestMapping(value = "/boards_data")
@Controller
public class DataController {

	private static final Logger logger = LoggerFactory.getLogger(DataController.class);

	@Inject
	HttpSession session;

	@Inject
	DataRepository dRepository;

	@Inject
	ReplyDataRepository rRepository;

	@Inject
	CustomerRepository cRepository;

	@Inject
	FriendRepository fRepository;

	Pagination_Data Paginationd = new Pagination_Data();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getBoards(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "friend_id", defaultValue = "") String friend_id) {
		logger.info("寃뚯떆�뙋 �솃");
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

		ArrayList<Data> boards_data = dRepository.getData(friend_id);
		int totalPages = Paginationd.totalPages(boards_data);
		page = Paginationd.getCurrentPage(page, totalPages);
		boards_data = Paginationd.totalPosts(boards_data, page);
		int endPage = Paginationd.endPage(page, totalPages);
		logger.info("珥� �럹�씠吏� : " + totalPages + ", �걹�럹�씠吏� :  " + endPage + "�쁽�옱 �럹�씠吏� :  " + page + "寃뚯떆臾� �닔 : "
				+ boards_data.size() + " status: " + (String) session.getAttribute("status"));
		model.addAttribute("data", boards_data);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		System.out.println("friend_id =" + friend_id);
		return "boards_data/data";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertBoards_data() {
		logger.info("湲� �옉�꽦 �솕硫댁쑝濡� �씠�룞");
		return "boards_data/insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBoards_data(Data data, MultipartFile upload, Model model) {
		String board_id = (String) session.getAttribute("loginid");
		String board_nickname = cRepository.selectNickname(board_id);
		data.setBoard_id(board_id);
		data.setBoard_nickname(board_nickname);
		dRepository.insertBoards_data(data);
		
		if (!upload.isEmpty()) {
			System.out.println("upload file's name @@@: " + upload.getOriginalFilename());
			
			String board_uploadfileid = FileService.saveFile(upload, Configuration.PHOTOPATH);
			
			data.setBoard_fileid(upload.getOriginalFilename());
			data.setBoard_uploadfileid(board_uploadfileid);
			
			dRepository.insertPhoto_data(data);
		}
		String friend_id = (String) session.getAttribute("loginid");
		int page = 1;
		ArrayList<Data> boards_data = dRepository.getData(friend_id);
		int totalPages = Paginationd.totalPages(boards_data);
		page = Paginationd.getCurrentPage(page, totalPages);
		boards_data = Paginationd.totalPosts(boards_data, page);
		int endPage = Paginationd.endPage(page, totalPages);
		model.addAttribute("data", boards_data);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		return "boards_data/data";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getBoards_data(int board_num, Model model, int page, String friend_id) {
		String loginid = (String) session.getAttribute("loginid");
		Data data = dRepository.getBoards_data(board_num);
		System.out.println(" data.getBoard_id() : " + data.getBoard_id());
		if (!loginid.equals(data.getBoard_id())) {
			// 조회수
			dRepository.upHits_data(board_num);
			data = dRepository.getBoards_data(board_num);
		}

		model.addAttribute("data", data);
		model.addAttribute("page", page);
		model.addAttribute("friend_id", friend_id);
		logger.info("date" + data);

		// 由ы뵆�씪�씠 遺�遺�
		ArrayList<Reply_Data> reply = rRepository.getReplies(board_num);
		model.addAttribute("reply", reply);
		return "boards_data/get";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(int board_num, HttpServletResponse response) {

		Data data = dRepository.getBoards_data(board_num);

		String originalfile = data.getBoard_fileid();

		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String fullpath = Configuration.PHOTOPATH + "/" + data.getBoard_uploadfileid();

		ServletOutputStream fileout = null;
		FileInputStream filein = null;

		try {
			filein = new FileInputStream(fullpath);
			fileout = response.getOutputStream();
			FileCopyUtils.copy(filein, fileout);
		} catch (Exception e) {
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
		Reply_Data newReply = new Reply_Data();
		newReply.setBoard_num(board_num);
		newReply.setReply_content(reply_content);
		System.out.println("蹂대뱶�꽆 �솕�땲?" + board_num);
		Reply_Data reply = rRepository.getReply(reply_num);
		int result = 0;
		if (reply_num == -1) {
			// �썝 �뙎湲��씤 寃쎌슦
			newReply.setReply_id(reply_Id);
			newReply.setReply_nickname(reply_Nickname);
		} else {
			// ���뙎湲��씤 寃쎌슦
			newReply.setReply_id(reply.getReply_id());
			newReply.setReply_nickname(reply.getReply_nickname());
			newReply.setRreply_id(reply_Id);
			newReply.setRreply_nickname(reply_Nickname);
			newReply.setRreply_num(reply_num);
		}
		result = rRepository.insertReply(newReply);
		// �썝�뙎湲��씪 寃쎌슦 rreply num 異붽�濡� �꽔�뼱二쇨린
		if (reply_num == -1) {
			reply_num = rRepository.recentlyAddedReplynum();
			logger.info("理쒓렐�뿉 �벑濡앺븳 �뙎湲� 踰덊샇 : " + reply_num);
			rRepository.updateRReply_num(reply_num);
		}
		// 0 : �뙎湲�異붽�
		dRepository.changeReply_data(board_num, 0);
		logger.info("�뙎湲� �옉�꽦 寃곌낵 : " + result);
		return board_num;
	}

	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	public @ResponseBody int deleteReply(int reply_num, int board_num) {
		int result = rRepository.deleteReply(reply_num);
		// 1 : �뙎湲� �궘�젣
		dRepository.changeReply_data(board_num, 1);
		logger.info("�뙎湲� �궘�젣 寃곌낵 : " + result);
		return board_num;
	}

	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	public @ResponseBody int updateReply(int reply_num, String reply_content, int board_num) {
		Reply_Data reply = rRepository.getReply(reply_num);
		reply.setReply_content(reply_content);
		int result = rRepository.updateReply(reply);
		logger.info("�뙎湲� �닔�젙");
		return board_num;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody int deleteBoards_data(Model model, int board_num) {
		Data data = dRepository.getBoards_data(board_num);
		boolean fileDeleteResult = false;
		if (data.getBoard_uploadfileid() != null) {
			fileDeleteResult = FileService.deleteFile(Configuration.PHOTOPATH + "/" + data.getBoard_uploadfileid());
		}
		int result = dRepository.deleteBoards_data(board_num);
		logger.info("湲� �궘�젣 : " + result + " , " + fileDeleteResult);
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateBoard(int board_num, Model model, int page, String friend_id) {
		Data data = dRepository.getBoards_data(board_num);
		String loginid = (String) session.getAttribute("loginid");
		if (data.getBoard_fileid() != null) {
			FileService.deleteFile(data.getBoard_uploadfileid());
		}
		model.addAttribute("page", page);
		model.addAttribute("friend_id", friend_id);
		if (data.getBoard_id().equals(loginid)) {
			// 湲��벖�궗�엺�씠�옉 濡쒓렇�씤�븳 �궗�엺�씠 媛숈쓣 �븣留� �뾽�럠�씠 媛��뒫�븯寃�
			model.addAttribute("data", data);
			return "boards_data/update";
		}
		return "boards_data/data";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBoard(int board_num, Data data, int page, String friend_id, Model model, MultipartFile upload) {
		Data originalBoard = dRepository.getBoards_data(board_num);
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
			data.setBoard_fileid(board_fileid);
			data.setBoard_uploadfileid(board_uploadfileid);
		}
		int result = dRepository.updateBoards_data(data);
		logger.info("湲� �닔�젙 寃곌낵: " + data.toString());
		ArrayList<Data> boards_data = dRepository.getData(friend_id);
		int totalPages = Paginationd.totalPages(boards_data);
		page = Paginationd.getCurrentPage(page, totalPages);
		boards_data = Paginationd.totalPosts(boards_data, page);
		int endPage = Paginationd.endPage(page, totalPages);
		model.addAttribute("data", data);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		return "boards_data/data";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String gethome(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "friend_id", defaultValue = "") String friend_id) {
		logger.info("寃뚯떆�뙋 �솃");
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

		ArrayList<Data> data = dRepository.getData(friend_id);
		int totalPages = Paginationd.totalPages(data);
		page = Paginationd.getCurrentPage(page, totalPages);
		data = Paginationd.totalPosts(data, page);
		int endPage = Paginationd.endPage(page, totalPages);
		logger.info("珥� �럹�씠吏�: " + totalPages + ", �걹�럹�씠吏� :  " + endPage + " �쁽�옱�럹�씠吏� :  " + page + " 寃뚯떆臾� �닔 : "
				+ data.size() + " status: " + (String) session.getAttribute("status"));
		model.addAttribute("boards", data);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("friend_id", friend_id);
		System.out.println("friend_id =" + friend_id);
		return "boards_data/data";
	}

	@RequestMapping(value = "/finddata", method = RequestMethod.POST)
	public String finddata(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType,
			@RequestParam(value = "searchContent", defaultValue = "") String searchContent) {

		int page = 1;
		int result = 0;
		ArrayList<Data> searchData = new ArrayList();
		searchData = dRepository.finddata(searchType, searchContent);
		int totalPages = Paginationd.totalPages(searchData);
		page = Paginationd.getCurrentPage(page, totalPages);
		searchData = Paginationd.totalPosts(searchData, page);
		int endPage = Paginationd.endPage(page, totalPages);

		logger.info("searchType: " + searchType + ", searchContent: " + searchContent + " , " + searchData.size());
		System.out
				.println("searchType: " + searchType + ", searchContent: " + searchContent + " , " + searchData.size());
		System.out.println("searchData : " + searchData);

		model.addAttribute("data", searchData);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);

		result = searchData.size();
		return "boards_data/data";
	}

	// �젙蹂닿쾶�떆�뙋 �젙�젹
	@RequestMapping(value = "/SortData", method = RequestMethod.GET)
	public String SortData(String sortValue, Model model) {

		System.out.println("�젙蹂닿쾶�떆�뙋 �젙�젹 �릱�굹~?~!? @@ sortValue : " + sortValue);
		ArrayList<Data> data = dRepository.getSort(sortValue);

		int page = 1;

		int totalPages = Paginationd.totalPages(data);
		page = Paginationd.getCurrentPage(page, totalPages);
		data = Paginationd.totalPosts(data, page);
		int endPage = Paginationd.endPage(page, totalPages);

		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("data", data);

		return "boards_data/data";
	}
}