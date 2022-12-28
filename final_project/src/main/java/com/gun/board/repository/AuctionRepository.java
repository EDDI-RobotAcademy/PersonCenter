package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.AuctionDAO;
import com.gun.board.dao.CustomerDAO;
import com.gun.board.dao.FreeDAO;
import com.gun.board.vo.Auction;
import com.gun.board.vo.Free;

@Repository
public class AuctionRepository {

	@Inject
	private SqlSession sqlSession;

	AuctionDAO adao;

	public int insertBoard(Auction auction) {
		int result = 0;
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.insertBoard(auction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertPhoto(Auction auction) {
		int result = 0;
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.insertPhoto(auction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// �ŷ��Խ��� (�߰�)
	public ArrayList<Auction> getTransaction(int transaction_name) {
		ArrayList<Auction> result = new ArrayList();
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			if (transaction_name == 0) {
				result = adao.getTransaction_0(transaction_name);
			} else {
				result = adao.getTransaction(transaction_name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// �Խñ� ��ȸ(����)
	public ArrayList<Auction> getBoards(String friend_id) {
		ArrayList<Auction> result = new ArrayList();
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.getBoards(friend_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// �Խñ� ��ȸ(Ȩ��)
	public ArrayList<Auction> getAuction_home() {
		ArrayList<Auction> result = new ArrayList();
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.getAuction_home();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Auction getBoard(int board_num) {
		Auction result = new Auction();
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.getBoard(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void upHits(int board_num) {
		// ��ȸ�� �ø���
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			adao.upHits(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int deleteBoard(int board_num) {
		int result = 0;
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.deleteBoard(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateBoard(Auction auction) {
		int result = 0;
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.updateBoard(auction);
			adao.updatePhoto(auction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updatePhoto(Auction auction) {
		int result = 0;
		adao = sqlSession.getMapper(AuctionDAO.class);
		try {
			result = adao.updatePhoto(auction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void changeReply(int board_num, int type) {
		adao = sqlSession.getMapper(AuctionDAO.class);
		Map<String, Integer> change = new HashMap();
		change.put("board_num", board_num);
		change.put("type", type);
		try {
			adao.changeReply(change);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	   //게시판 검색
	   public ArrayList<Auction> findauction(String searchType, String searchContent) {
		   Map<String, String> search = new HashMap();
		      
		      search.put("searchType", searchType);
		      search.put("searchContent", searchContent);
		      System.out.println("경매게시판 검색 :  " + searchType);
		      ArrayList<Auction> result = new ArrayList();
		      adao = sqlSession.getMapper(AuctionDAO.class);
		      try {
		         result = adao.findauction(search);
		      } catch (Exception e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		      return result;
		   }
	   
	   public ArrayList<Auction> getSort(String sortValue) {
		      Map sortAuction = new HashMap();
		      sortAuction.put("sortValue", sortValue);
		      
		      ArrayList<Auction> result = new ArrayList();
		      System.out.println("sortValue : " +sortValue);
		      adao = sqlSession.getMapper(AuctionDAO.class);
		      try {
		         result = adao.getSort(sortAuction);
		      } catch (Exception e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		      return result;
		   }
}
