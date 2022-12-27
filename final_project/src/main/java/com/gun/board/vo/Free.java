package com.gun.board.vo;

import java.util.Arrays;
import java.util.Date;

public class Free {
	// �Խ���

	// �Խ��� ���� ��ȣ
	private int board_num;
	// ����
	private String board_title;
	// ����
	private String board_content;
	// �ۼ� ��¥
	private Date board_date;
	// ���̵�
	private String board_id;
	// ��ȸ��
	private int board_hits;
	// �г���
	private String board_nickname;
	// ��� ��
	private int board_replies;
	// ���� ����
	private String board_see;
	// ���� ���� �̸�
	private String board_fileid;
	// ����Ǵ� ���� �̸�
	private String board_uploadfileid;

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

	public String getBoard_see() {
		return board_see;
	}

	public void setBoard_see(String board_see) {
		this.board_see = board_see;
	}

	public int getBoard_replies() {
		return board_replies;
	}

	public void setBoard_replies(int board_replies) {
		this.board_replies = board_replies;
	}

	public Free() {
		super();
	}

	public int getBoard_hits() {
		return board_hits;
	}

	public void setBoard_hits(int board_hits) {
		this.board_hits = board_hits;
	}

	public String getBoard_nickname() {
		return board_nickname;
	}

	public void setBoard_nickname(String board_nickname) {
		this.board_nickname = board_nickname;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	@Override
	public String toString() {
		return "Free [board_num=" + board_num + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_date=" + board_date + ", board_id=" + board_id + ", board_hits=" + board_hits
				+ ", board_nickname=" + board_nickname + ", board_replies=" + board_replies + ", board_see=" + board_see
				+ ", board_fileid=" + board_fileid + ", board_uploadfileid=" + board_uploadfileid + "]";
	}

}