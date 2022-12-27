package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.gun.board.vo.Customer;

public interface FriendDAO {

	// 친구 찾기
	public ArrayList<Customer> findFriends(Map<String, String> search) throws Exception;

	// 친구 요청의 개수
	public int numofFriendRequest(String cus_id) throws Exception;

	// 친구인지 여부 확인(혹은 이미 신청한 상태인지)
	public String getStatus(Map<String, String> search) throws Exception;

	// 친구 신청
	public int friendRequest(Map<String, String> request) throws Exception;

	public int friendRequestFriend(Map<String, String> request) throws Exception;

	// 친구 리스트 뽑기
	public ArrayList<String> getList(String cus_id) throws Exception;

	// 친구 신청 받은 목록 뽑기
	public ArrayList<String> getRequestList(String cus_id) throws Exception;

	// 친구 수락하기
	public int accept(Map<String, String> accept) throws Exception;

	public int acceptFriend(Map<String, String> accept) throws Exception;

	// 친구 삭제
	public int remove(Map<String, String> remove) throws Exception;

	public int removeFriend(Map<String, String> remove) throws Exception;

	public String getfriend(String login_id) throws Exception;
}
