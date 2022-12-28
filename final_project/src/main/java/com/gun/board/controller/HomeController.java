package com.gun.board.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.board.repository.AuctionRepository;
import com.gun.board.repository.BoardRepository;
import com.gun.board.repository.DataRepository;
import com.gun.board.repository.FreeRepository;
import com.gun.board.repository.NoticeRepository;
import com.gun.board.util.Pagination;
import com.gun.board.util.Pagination_Auction;
import com.gun.board.util.Pagination_Data;
import com.gun.board.util.Pagination_Free;
import com.gun.board.vo.Auction;
import com.gun.board.vo.Board;
import com.gun.board.vo.Data;
import com.gun.board.vo.Free;
import com.gun.board.vo.Notice;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	HttpSession session;

	@Inject
	BoardRepository bRepository;

	@Inject
	NoticeRepository nRepository;

	@Inject
	FreeRepository frRepository;
	
	@Inject
	DataRepository dRepository;
	
	@Inject
	AuctionRepository aRepository;

	Pagination Pagination = new Pagination();
	Pagination_Free Paginationf = new Pagination_Free();
	Pagination_Data Paginationd = new Pagination_Data();
	Pagination_Auction Paginationa = new Pagination_Auction();

	// 홈으로
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("게시판 홈");

		// 거래글(6개)
		int page = 1;
		for (int i = 1; i < 7; i++) {
			ArrayList<Board> boards = bRepository.getBoards_home(i);
			boards = Pagination.totalPosts_home(boards, page);
			model.addAttribute("boards_home_" + i, boards);
		}
		model.addAttribute("page", page);

		// 공지사항
		ArrayList<Notice> Notice_boards = nRepository.getNotice_home();
		model.addAttribute("Notice_boards", Notice_boards);

		// 자유게시판
		ArrayList<Free> free = frRepository.getFree_home();
		free = Paginationf.totalPosts_home_free(free, page);
		model.addAttribute("boards_free", free);

		// 정보게시판
		ArrayList<Data> data = dRepository.getData_home();
		data = Paginationd.totalPosts_home_data(data, page);
		model.addAttribute("boards_data", data);
		
		// 경매게시판
		ArrayList<Auction> auction = aRepository.getAuction_home();
		auction = Paginationa.totalPosts_home(auction, page);
		model.addAttribute("auction", auction);
		
		return "home";
	}
}
