package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gun.board.vo.Customer;
import com.gun.board.vo.Friend;

public interface FriendDAO {

	// ģ   ã  
	public ArrayList<Customer> findFriends(Map<String, String> search) throws Exception;

	// ģ     û       
	public int numofFriendRequest(String cus_id) throws Exception;

	// ģ            Ȯ  (Ȥ    ̹    û           )
	public String getStatus(Map<String, String> search) throws Exception;

	// ģ     û
	public int friendRequest(Map<String, String> request) throws Exception;

	public int friendRequestFriend(Map<String, String> request) throws Exception;

	// ģ       Ʈ  ̱ 
	public ArrayList<String> getList(String cus_id) throws Exception;

	// ģ     û           ̱ 
	public ArrayList<String> getRequestList(@Param("cus_id")String cus_id, @Param("board_num")int board_num) throws Exception;

	// ģ        ϱ 
	public int accept(Map<String, String> accept) throws Exception;

	public int acceptFriend(Map<String, String> accept) throws Exception;

	// ģ       
	public int remove(Map<String, String> remove) throws Exception;

	public int removeFriend(Map<String, String> remove) throws Exception;

	public String getfriend(String login_id) throws Exception;
	
	//  ŷ               Ʈ (     Ѻκ )
	public int accept_2(Map<String, Object> accept)throws Exception;

	public void acceptFriend_2(Map<String, Object> accept)throws Exception;

	public String getStatus_2(Map<String, Object> search)throws Exception;

	public int friendRequest_2(Map<String, Object> request)throws Exception;

	public void friendRequestFriend_2(Map<String, Object> request)throws Exception;

	public String getfriend_2(Map<String, Object> request)throws Exception;

	public String getStatus_board(Map<String, Object> search)throws Exception;

	public Friend getTrader(Map<String, Object> search)throws Exception;
	
	
}
