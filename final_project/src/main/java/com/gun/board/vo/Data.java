package com.gun.board.vo;

import java.util.Arrays;
import java.util.Date;

public class Data {
	// 게시판

	// 게시판 고유 번호
	private int board_num;
	// 제목
	private String board_title;
	// 내용
	private String board_content;
	// 작성 날짜
	private Date board_date;
	// 아이디
	private String board_id;
	// 조회수
	private int board_hits;
	// 닉네임
	private String board_nickname;
	// 댓글 수
	private int board_replies;
	// 공개 범위
	private String board_see;
	// 원본 파일 이름
	private String board_fileid;
	// 저장되는 파일 이름
	private String board_uploadfileid;
	
	private String type;
	 
	private String[] typeArr;
    
    
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

	public Data() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeArr = type.split("");
	}

	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}

	@Override
	public String toString() {
		return "Data [board_num=" + board_num + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_id=" + board_id + ", board_hits=" + board_hits + ", board_nickname=" + board_nickname
				+ ", board_replies=" + board_replies + ", board_see=" + board_see + ", board_fileid=" + board_fileid
				+ ", board_uploadfileid=" + board_uploadfileid + ", type=" + type + ", typeArr="
				+ Arrays.toString(typeArr) + "]";
	}
}