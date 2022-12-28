package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.gun.board.vo.Auction;
import com.gun.board.vo.Free;

public interface AuctionDAO {

	// �ŷ��Խ������� �߰�
	public ArrayList<Auction> getTransaction_0(int transaction_name) throws Exception;

	public ArrayList<Auction> getTransaction(int transaction_name) throws Exception;

	public int insertBoard(Auction auction) throws Exception;

	public int insertPhoto(Auction auction) throws Exception;

	public ArrayList<Auction> getBoards(String friend_id) throws Exception;

	public ArrayList<Auction> getAuction_home() throws Exception;

	public Auction getBoard(int board_num) throws Exception;

	public void upHits(int board_num) throws Exception;

	public int deleteBoard(int board_num) throws Exception;

	public int deletePhoto(Auction auction) throws Exception;

	public int updateBoard(Auction auction) throws Exception;

	public int updatePhoto(Auction auction) throws Exception;

	public void changeReply(Map<String, Integer> change) throws Exception;

	public ArrayList<Auction> getBoards_home(int i) throws Exception;
	
	public ArrayList<Auction> findauction(Map<String, String> search)throws Exception;
	   
	public ArrayList<Auction> getSort(Map sortNotice)throws Exception;
}
