package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.gun.board.vo.Board;

public interface BoardDAO {

	// 거래게시판으로 추가
	public ArrayList<Board> getTransaction_0(int transaction_name) throws Exception;

	public ArrayList<Board> getTransaction(int transaction_name) throws Exception;

	public int insertBoard(Board board) throws Exception;

	public int insertPhoto(Board board) throws Exception;

	public ArrayList<Board> getBoards(String friend_id) throws Exception;

	public ArrayList<Board> getBoards_home() throws Exception;

	public Board getBoard(int board_num) throws Exception;

	public void upHits(int board_num) throws Exception;

	public int deleteBoard(int board_num) throws Exception;

	public int deletePhoto(Board board) throws Exception;

	public int updateBoard(Board board) throws Exception;

	public int updatePhoto(Board board) throws Exception;

	public void changeReply(Map<String, Integer> change) throws Exception;

	public ArrayList<Board> getBoards_home(int i) throws Exception;


}
