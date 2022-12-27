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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gun.board.repository.CustomerRepository;
import com.gun.board.repository.FriendRepository;
import com.gun.board.vo.Customer;

@RequestMapping(value = "/friend")
@Controller
public class FriendController {

	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

	@Inject
	FriendRepository fRepository;

	@Inject
	HttpSession session;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("친구관리");
		return "friend/home";
	}

	@RequestMapping(value = "/findFriends", method = {RequestMethod.POST, RequestMethod.GET} )
	public String findFriends(Model model, @RequestParam(value = "searchType", defaultValue = "") String searchType,
			@RequestParam(value = "searchContent", defaultValue = "") String searchContent) {
		int result = 0;
		ArrayList<Customer> friends = new ArrayList();
		friends = fRepository.findFriends(searchType, searchContent);
		logger.info("searchType: " + searchType + ", searchContent: " + searchContent + " , " + friends.size());
		model.addAttribute("friends", friends);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchContent", searchContent);
		result = friends.size();
		return "friend/home";
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
			model.addAttribute("requestResult", friend_id + " 님에게 거래를 요청했습니다 " );
		} else if (checkRelationship.equals("request")) {
			model.addAttribute("requestResult", "이미 거래를 요청했습니다. " + friend_id);
		} else if (checkRelationship.equals("friend")) {
			model.addAttribute("requestResult", friend_id + " 님에게 이미 거래를 요청했습니다. ");
		}
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchContent", searchContent);
		ArrayList<Customer> friends = fRepository.findFriends(searchType, searchContent);
		model.addAttribute("friends", friends);
		return "boards/get";
	}
	

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(Model model) {
		String cus_id = (String) session.getAttribute("loginid");
		ArrayList<String> friends = fRepository.getList(cus_id);
		model.addAttribute("friends", friends);
		ArrayList<String> request = fRepository.getRequestList(cus_id);
		model.addAttribute("request", request);
		logger.info("friends: " + friends.size() + " , request: " + request.size());
		return "friend/management";
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public @ResponseBody String accept(String friend_id) {
		String cus_id = (String) session.getAttribute("loginid");
		int result = fRepository.accept(cus_id, friend_id);
		int numofFriendRequest = fRepository.numofFriendRequest(cus_id);
		session.setAttribute("numofFriendRequest", numofFriendRequest);
		logger.info("친구 수락 결과 : " + numofFriendRequest);
		logger.info("결과 : " + result);
		return friend_id;
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public @ResponseBody String remove(String friend_id) {
		String cus_id = (String) session.getAttribute("loginid");
		int result = fRepository.remove(cus_id, friend_id);
		logger.info("친구 삭제 결과 : " + result);
		int numofFriendRequest = fRepository.numofFriendRequest(cus_id);
		session.setAttribute("numofFriendRequest", numofFriendRequest);
		return friend_id;
	}
}
