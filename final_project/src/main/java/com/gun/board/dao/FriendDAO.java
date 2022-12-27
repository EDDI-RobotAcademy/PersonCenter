package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.gun.board.vo.Customer;

public interface FriendDAO {

	// ģ�� ã��
	public ArrayList<Customer> findFriends(Map<String, String> search) throws Exception;

	// ģ�� ��û�� ����
	public int numofFriendRequest(String cus_id) throws Exception;

	// ģ������ ���� Ȯ��(Ȥ�� �̹� ��û�� ��������)
	public String getStatus(Map<String, String> search) throws Exception;

	// ģ�� ��û
	public int friendRequest(Map<String, String> request) throws Exception;

	public int friendRequestFriend(Map<String, String> request) throws Exception;

	// ģ�� ����Ʈ �̱�
	public ArrayList<String> getList(String cus_id) throws Exception;

	// ģ�� ��û ���� ��� �̱�
	public ArrayList<String> getRequestList(String cus_id) throws Exception;

	// ģ�� �����ϱ�
	public int accept(Map<String, String> accept) throws Exception;

	public int acceptFriend(Map<String, String> accept) throws Exception;

	// ģ�� ����
	public int remove(Map<String, String> remove) throws Exception;

	public int removeFriend(Map<String, String> remove) throws Exception;

	public String getfriend(String login_id) throws Exception;
}
