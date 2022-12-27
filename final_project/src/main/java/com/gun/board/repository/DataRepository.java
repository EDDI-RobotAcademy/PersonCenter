package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.DataDAO;
import com.gun.board.dao.FreeDAO;
import com.gun.board.vo.Data;
import com.gun.board.vo.Free;

@Repository
public class DataRepository {

	@Inject
	private SqlSession sqlSession;

	DataDAO ddao;

	public int insertBoards_data(Data data) {
		int result = 0;
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			result = ddao.insertBoards_data(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertPhoto_data(Data data) {
		int result = 0;
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			result = ddao.insertPhoto_data(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Data> getData(String friend_id) {
		ArrayList<Data> result = new ArrayList();
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			result = ddao.getData(friend_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Data> getData_home() {
	      ArrayList<Data> result = new ArrayList();
	      ddao = sqlSession.getMapper(DataDAO.class);
	      try {
	         result = ddao.getData_home();
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return result;
	   }
	
	public Data getBoards_data(int board_num) {
		Data result = new Data();
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			result = ddao.getBoards_data(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void upHits_data(int board_num) {
		// 議고쉶�닔 �삱由ш린
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			ddao.upHits_data(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int deleteBoards_data(int board_num) {
		int result = 0;
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			result = ddao.deleteBoards_data(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateBoards_data(Data data) {
		int result = 0;
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			result = ddao.updateBoards_data(data);
			ddao.updateBoards_data(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updatePhoto_data(Data data) {
		int result = 0;
		ddao = sqlSession.getMapper(DataDAO.class);
		try {
			result = ddao.updatePhoto_data(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void changeReply_data(int board_num, int type) {
		ddao = sqlSession.getMapper(DataDAO.class);
		Map<String, Integer> change = new HashMap();
		change.put("board_num", board_num);
		change.put("type", type);
		try {
			ddao.changeReply_data(change);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Data> finddata(String searchType, String searchContent) {
		Map<String, String> search = new HashMap();
	      
	      search.put("searchType", searchType);
	      search.put("searchContent", searchContent);
	      System.out.println("�옄�쑀寃뚯떆�뙋 寃��깋 :  " + searchType);
	      ArrayList<Data> result = new ArrayList();
	      ddao = sqlSession.getMapper(DataDAO.class);
	      try {
	         result = ddao.finddata(search);
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return result;
	   }
	
	public ArrayList<Data> getSort(String sortValue) {
	      Map sortData = new HashMap();
	      sortData.put("sortValue", sortValue);
	      
	      ArrayList<Data> result = new ArrayList();
	      System.out.println("sortValue : " + sortValue);
	      ddao = sqlSession.getMapper(DataDAO.class);
	      try {
	         result = ddao.getSort(sortData);
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return result;
	   }
}