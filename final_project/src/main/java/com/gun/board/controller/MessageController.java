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

import com.gun.board.repository.MessageRepository;
import com.gun.board.util.Pagination;
import com.gun.board.vo.Board;
import com.gun.board.vo.Message;

@RequestMapping(value = "/message")
@Controller
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Inject
	HttpSession session;

	@Inject
	MessageRepository mRepository;

	Pagination pagination = new Pagination();

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(Model model, @RequestParam(value = "page", defaultValue = "1") int page, String category) {
		logger.info("메세지관리" + category);
		String cus_id = (String) session.getAttribute("loginid");
		ArrayList<Message> message = mRepository.getMessage(cus_id, category);
		int totalPages = pagination.totalPagesMessage(message);
		page = pagination.getCurrentPage(page, totalPages);
		message = pagination.totalPostsMessage(message, page);
		int endPage = pagination.endPage(page, totalPages);
		logger.info("총 페이지 : " + totalPages + ", 끝 페이지 : " + endPage);
		model.addAttribute("message", message);
		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("category", category);
		return "message/management";
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(String cus_id, String loginid, Model model) {
		model.addAttribute("cus_id", cus_id);
		System.out.println(cus_id);
		return "message/send";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public @ResponseBody String send(Message message, Model model) {
		mRepository.sendMessage(message);
		logger.info("메세지를 전송했습니다");
		String cus_id = (String) session.getAttribute("loginid");
		int numofSentMessage = mRepository.numofMessage(cus_id, "sent");
		session.setAttribute("numofSentMessage", numofSentMessage);
		return message.getFriend_id();
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(Model model, int message_num, String category, int page) {
		Message message = mRepository.get(message_num);
		String cus_id = (String) session.getAttribute("loginid");
		int num = 0;
		if (category.equals("read") && !message.getFriend_status().equals("read")) {
			// 받은 메세지를 읽을 떄 이미 읽은 상태가 아닐 경우 변경
			num = 1;
			mRepository.readMessage(message_num, num);
			int numofReadMessage = mRepository.numofMessage(cus_id, category);
			session.setAttribute("numofReadMessage", numofReadMessage);

		} else if (category.equals("sent") && !message.getCus_status().equals("read")) {
			mRepository.readMessage(message_num, num);
			int numofSentMessage = mRepository.numofMessage(cus_id, category);
			session.setAttribute("numofSentMessage", numofSentMessage);
		}
		model.addAttribute("message", message);
		model.addAttribute("category", category);
		model.addAttribute("page", page);
		return "message/get";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody int delete(int message_num, String category, Model model) {
		int result = 0;
		Message message = mRepository.get(message_num);
		if (message.getCus_status().equals("delete") || message.getFriend_status().equals("delete")) {
			// 한 쪽이 이미 삭제한 상태에서 다른 쪽이 삭제 -> 아예 삭제
			result = mRepository.delete(message_num);
		} else {
			// 받은 쪽지함 쪽지 삭제시 -> 0, 보낸 쪽지함 삭제시 ->1
			int num = 0;
			if (category.equals("sent")) {
				num = 1;
			}
			result = mRepository.deleteStatus(message_num, num);
		}
		logger.info("메세지 삭제: " + result);
		model.addAttribute("category", category);
		return result;
	}

}
