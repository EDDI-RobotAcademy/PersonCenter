package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.BoardDAO;
import com.gun.board.dao.FriendDAO;
import com.gun.board.vo.Board;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Friend;

@Repository
public class FriendRepository {
	@Inject
	private SqlSession sqlSession;

	FriendDAO fdao;

	public ArrayList<Customer> findFriends(String searchType, String searchContent) {
		Map<String, String> search = new HashMap();
		search.put("searchType", searchType);
		search.put("searchContent", searchContent);
		ArrayList<Customer> result = new ArrayList();
		fdao = sqlSession.getMapper(FriendDAO.class);
		try {
			result = fdao.findFriends(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int friendRequest(String cus_id, String friend_id) {
		Map<String, String> request = new HashMap();
		request.put("cus_id", cus_id);
		request.put("friend_id", friend_id);
		fdao = sqlSession.getMapper(FriendDAO.class);
		int result = 0;
		try {
			result = fdao.friendRequest(request);
			fdao.friendRequestFriend(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getStatus(String cus_id, String friend_id) {
		fdao = sqlSession.getMapper(FriendDAO.class);
		String result = "";
		Map<String, String> search = new HashMap();
		search.put("cus_id", cus_id);
		search.put("friend_id", friend_id);
		try {
			result = fdao.getStatus(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getList(String cus_id) {
		fdao = sqlSession.getMapper(FriendDAO.class);
		ArrayList<String> result = new ArrayList();
		try {
			result = fdao.getList(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
		

	public ArrayList<String> getRequestList(String cus_id, int board_num) {
		fdao = sqlSession.getMapper(FriendDAO.class);
		ArrayList<String> result = new ArrayList();
		try {
			result = fdao.getRequestList(cus_id, board_num);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int accept(String cus_id, String friend_id) {
		fdao = sqlSession.getMapper(FriendDAO.class);
		int result = 0;
		Map<String, String> accept = new HashMap();
		accept.put("cus_id", cus_id);
		accept.put("friend_id", friend_id);
		try {
			result = fdao.accept(accept);
			fdao.acceptFriend(accept);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int remove(String cus_id, String friend_id) {
		fdao = sqlSession.getMapper(FriendDAO.class);
		int result = 0;
		Map<String, String> remove = new HashMap();
		remove.put("cus_id", cus_id);
		remove.put("friend_id", friend_id);
		try {
			result = fdao.remove(remove);
			fdao.removeFriend(remove);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int numofFriendRequest(String cus_id) {
		fdao = sqlSession.getMapper(FriendDAO.class);
		int result = 0;
		try {
			result = fdao.numofFriendRequest(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	public String getfriend(String login_id) {
		fdao = sqlSession.getMapper(FriendDAO.class);
		String result = "";
		try {
			result = fdao.getfriend(login_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	// (     Ѻκ )      ϱ         friend     ٲٰ  board_num     ֱ  
		public int accept_2(String cus_id, String friend_id, int board_num) {
			fdao = sqlSession.getMapper(FriendDAO.class);
			int result = 0;
			Map<String, Object> accept = new HashMap();
			accept.put("cus_id", cus_id);
			accept.put("friend_id", friend_id);
			accept.put("board_num", board_num);

			try {
				result = fdao.accept_2(accept);
				fdao.acceptFriend_2(accept);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		// (     Ѻκ )  Ǹ   ,       ,  Խñ    ȣ             
		public String getStatus_2(String cus_id, String friend_id, int board_num) {
			fdao = sqlSession.getMapper(FriendDAO.class);
			String result = "";
			Map<String,  Object> search = new HashMap();
			search.put("cus_id", cus_id);
			search.put("friend_id", friend_id);
			search.put("board_num", board_num);
			try {
				result = fdao.getStatus_2(search);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		public int friendRequest_2(String cus_id, String friend_id, int board_num) {
			Map<String, Object> request = new HashMap();
			
			request.put("cus_id", cus_id);
			request.put("friend_id", friend_id);
			request.put("board_num", board_num);
			fdao = sqlSession.getMapper(FriendDAO.class);
			int result = 0;
		
			try {
				result = fdao.friendRequest_2(request);
				fdao.friendRequestFriend_2(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		public String getfriend_2(String login_id, int board_num) {
			Map<String, Object> request = new HashMap();
			
			request.put("cus_id", login_id);
			request.put("board_num", board_num);
			fdao = sqlSession.getMapper(FriendDAO.class);
			String result = "";
			try {
				result = fdao.getfriend_2(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		public String getStatus_board(String cus_id, int board_num) {
			fdao = sqlSession.getMapper(FriendDAO.class);
			String result = "";
			Map<String, Object> search = new HashMap();
			search.put("cus_id", cus_id);
			search.put("board_num", board_num);
			try {
				result = fdao.getStatus_board(search);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}

		public Friend getTrader(int board_num, String cus_id) {
			Friend result = new Friend();
			fdao = sqlSession.getMapper(FriendDAO.class);
			Map<String, Object> search = new HashMap();
			search.put("cus_id", cus_id);
			search.put("board_num", board_num);
			try {
				result = fdao.getTrader(search);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}


}
