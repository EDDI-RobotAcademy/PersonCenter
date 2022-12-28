package com.gun.board.vo;

public class Message {
	// 번호
	private int message_num;

	// 본인
	private String cus_id;

	// 상대방
	private String friend_id;

	// 제목
	private String message_title;

	// 내용
	private String message;

	// 상태->내가 보냈을 떄
	private String cus_status;

	// 상대방의 상태->확인했는지 아직 확인 안했는지
	private String friend_status;

	// 보낸 날짜
	private String message_date;

	public int getMessage_num() {
		return message_num;
	}

	public void setMessage_num(int message_num) {
		this.message_num = message_num;
	}

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

	public String getMessage_date() {
		return message_date;
	}

	public void setMessage_date(String message_date) {
		this.message_date = message_date;
	}

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCus_status() {
		return cus_status;
	}

	public void setCus_status(String cus_status) {
		this.cus_status = cus_status;
	}

	public String getFriend_status() {
		return friend_status;
	}

	public void setFriend_status(String friend_status) {
		this.friend_status = friend_status;
	}

	@Override
	public String toString() {
		return "Message [message_num=" + message_num + ", cus_id=" + cus_id + ", friend_id=" + friend_id
				+ ", message_title=" + message_title + ", message=" + message + ", cus_status=" + cus_status
				+ ", friend_status=" + friend_status + ", message_date=" + message_date + "]";
	}
}
