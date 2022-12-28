package com.gun.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gun.board.vo.Customer;
import com.gun.board.vo.Data;
import com.gun.board.vo.Free;

public interface DataDAO {

	public int insertBoards_data(Data data) throws Exception;

	public int insertPhoto_data(Data data) throws Exception;

	public ArrayList<Data> getData(String friend_id) throws Exception;
	
	public ArrayList<Data> getData_home() throws Exception;

	public Data getBoards_data(int board_num) throws Exception;

	public void upHits_data(int board_num) throws Exception;

	public int deleteBoards_data(int board_num) throws Exception;

	public int deletePhoto_data(Data data) throws Exception;

	public int updateBoards_data(Data data) throws Exception;

	public int updatePhoto_data(Data data) throws Exception;

	public void changeReply_data(Map<String, Integer> change) throws Exception;

	public ArrayList<Data> finddata(Map<String, String> search) throws Exception;
	  
	public ArrayList<Data> getSort(Map sortNotice)throws Exception;
}