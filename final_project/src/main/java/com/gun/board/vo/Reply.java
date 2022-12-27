package com.gun.board.vo;

public class Reply {
	// 댓글

	// 댓글이 속한 게시글 번호
	private int board_num;
	// 댓글의 고유 번호
	private int reply_num;
	// 댓글 작성자 아이디
	private String reply_id;
	// 댓글 내용
	private String reply_content;
	// 댓글 작성 시간
	private String reply_date;
	// 댓글 작성자 닉네임
	private String reply_nickname;
	// 대댓글 작성자 아이디
	private String rreply_id;
	// 대댓글 작성자 닉네임
	private String rreply_nickname;
	// 대댓글이 속한 댓글 번호
	private int rreply_num;

	public int getRreply_num() {
		return rreply_num;
	}

	public void setRreply_num(int rreply_num) {
		this.rreply_num = rreply_num;
	}

	public String getRreply_id() {
		return rreply_id;
	}

	public void setRreply_id(String rreply_id) {
		this.rreply_id = rreply_id;
	}

	public String getRreply_nickname() {
		return rreply_nickname;
	}

	public void setRreply_nickname(String rreply_nickname) {
		this.rreply_nickname = rreply_nickname;
	}

	public String getReply_nickname() {
		return reply_nickname;
	}

	public void setReply_nickname(String reply_nickname) {
		this.reply_nickname = reply_nickname;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

	@Override
	public String toString() {
		return "Reply [board_num=" + board_num + ", reply_num=" + reply_num + ", reply_id=" + reply_id
				+ ", reply_content=" + reply_content + ", reply_date=" + reply_date + "]";
	}

}
