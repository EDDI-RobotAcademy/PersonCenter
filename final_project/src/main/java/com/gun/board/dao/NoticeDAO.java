package com.gun.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.gun.board.vo.Notice;

public interface NoticeDAO {
	
	public int insertBoard(Notice board) throws Exception;

	public ArrayList<Notice> getBoards(String friend_id) throws Exception;

	public Notice getBoard(int board_num) throws Exception;

	public int deleteBoard(int board_num) throws Exception;

	public int updateBoard(Notice board) throws Exception;

	public int countUp(int board_num) throws Exception;

	public ArrayList<Notice> findNotice(Map<String, String> search) throws Exception;

	public ArrayList<Notice> getNotice_home() throws Exception;
	
}