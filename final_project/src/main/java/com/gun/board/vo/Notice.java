package com.gun.board.vo;

import java.util.Date;

public class Notice {

	private int board_num;

	private String board_no_title;

	private String board_no_content;

	private Date board_no_date;

	private String board_no_id;

	private String board_no_nickname;

	private int board_no_replies;

	private String board_no_seq;

	private String board_fileid;

	private String board_uploadfileid;

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_no_title() {
		return board_no_title;
	}

	public void setBoard_no_title(String board_no_title) {
		this.board_no_title = board_no_title;
	}

	public String getBoard_no_content() {
		return board_no_content;
	}

	public void setBoard_no_content(String board_no_content) {
		this.board_no_content = board_no_content;
	}

	public Date getBoard_no_date() {
		return board_no_date;
	}

	public void setBoard_no_date(Date board_no_date) {
		this.board_no_date = board_no_date;
	}

	public String getBoard_no_id() {
		return board_no_id;
	}

	public void setBoard_no_id(String board_no_id) {
		this.board_no_id = board_no_id;
	}

	public String getBoard_no_nickname() {
		return board_no_nickname;
	}

	public void setBoard_no_nickname(String board_no_nickname) {
		this.board_no_nickname = board_no_nickname;
	}

	public int getBoard_no_replies() {
		return board_no_replies;
	}

	public void setBoard_no_replies(int board_no_replies) {
		this.board_no_replies = board_no_replies;
	}

	public String getBoard_no_seq() {
		return board_no_seq;
	}

	public void setBoard_no_seq(String board_no_seq) {
		this.board_no_seq = board_no_seq;
	}

	public String getBoard_fileid() {
		return board_fileid;
	}

	public void setBoard_fileid(String board_fileid) {
		this.board_fileid = board_fileid;
	}

	public String getBoard_uploadfileid() {
		return board_uploadfileid;
	}

	public void setBoard_uploadfileid(String board_uploadfileid) {
		this.board_uploadfileid = board_uploadfileid;
	}

	@Override
	public String toString() {
		return "Notice [board_num=" + board_num + ", board_no_title=" + board_no_title + ", board_no_content="
				+ board_no_content + ", board_no_date=" + board_no_date + ", board_no_id=" + board_no_id
				+ ", board_no_nickname=" + board_no_nickname + ", board_no_replies=" + board_no_replies
				+ ", board_no_seq=" + board_no_seq + ", board_fileid=" + board_fileid + ", board_uploadfileid="
				+ board_uploadfileid + "]";
	}

}