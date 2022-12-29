package com.gun.board.vo;



public class Friend {

	private String cus_id;
	
	private String friend_id;
	
	private String cus_status;
	
	private int board_num;

	public String getCus_id() {
		return cus_id;
	}

	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	public String getCus_status() {
		return cus_status;
	}

	public void setCus_status(String cus_status) {
		this.cus_status = cus_status;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	@Override
	public String toString() {
		return "Friend [cus_id=" + cus_id + ", friend_id=" + friend_id + ", cus_status=" + cus_status + ", board_num="
				+ board_num + "]";
	}
	
	
	

}
