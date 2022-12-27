package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.gun.board.dao.MessageDAO;
import com.gun.board.vo.Message;

@Repository
public class MessageRepository {

	@Inject
	private SqlSession sqlSession;

	MessageDAO mdao;

	public int sendMessage(Message message) {
		int result = 0;
		mdao = sqlSession.getMapper(MessageDAO.class);
		try {
			result = mdao.sendMessage(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Message> getMessage(String cus_id, String category) {
		ArrayList<Message> result = new ArrayList();
		mdao = sqlSession.getMapper(MessageDAO.class);
		Map<String, String> message = new HashMap();
		message.put("cus_id", cus_id);
		message.put("category", category);
		try {
			result = mdao.getMessage(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Message get(int message_num) {
		Message result = new Message();
		mdao = sqlSession.getMapper(MessageDAO.class);
		try {
			result = mdao.get(message_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int delete(int message_num) {
		int result = 0;
		mdao = sqlSession.getMapper(MessageDAO.class);
		try {
			result = mdao.delete(message_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int readMessage(int message_num, int category) {
		int result = 0;
		mdao = sqlSession.getMapper(MessageDAO.class);
		Map<String, Integer> read = new HashMap();
		read.put("message_num", message_num);
		read.put("category", category);
		try {
			result = mdao.readMessage(read);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteStatus(int message_num, int num) {
		Map<String, Integer> change = new HashMap();
		mdao = sqlSession.getMapper(MessageDAO.class);
		int result = 0;
		change.put("message_num", message_num);
		change.put("category", num);
		try {
			result = mdao.deleteStatus(change);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int numofMessage(String cus_id, String category) {
		Map<String, String> num = new HashMap();
		mdao = sqlSession.getMapper(MessageDAO.class);
		int result = 0;
		num.put("cus_id", cus_id);
		num.put("category", category);
		try {
			result = mdao.numofMessage(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
