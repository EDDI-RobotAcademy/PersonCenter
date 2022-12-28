package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.gun.board.vo.Message;

public interface MessageDAO {

	// 내가 받은 메세지 모두 가져오기
	public ArrayList<Message> getMessage(Map<String, String> message) throws Exception;

	// 메시지 보내기(나)
	public int sendMessage(Message message) throws Exception;

	// 메시지 보내기(상대방)
	public int sendMessageFriend(Message message) throws Exception;

	// 메시지 읽기
	public Message get(int message_num) throws Exception;

	// 메시지 읽은 후 read로 상태 변경하기
	public int readMessage(Map<String, Integer> read) throws Exception;

	// 메시지 완전 삭제
	public int delete(int message_num) throws Exception;

	// 메시지 삭제로 상태만 변경
	public int deleteStatus(Map<String, Integer> change) throws Exception;

	// 메세지 수 확인
	public int numofMessage(Map<String, String> num) throws Exception;
}
