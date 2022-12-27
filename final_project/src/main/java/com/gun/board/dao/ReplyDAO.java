package com.gun.board.dao;

import java.util.ArrayList;

import com.gun.board.vo.Reply;

public interface ReplyDAO {

	// 전체 댓글 내용 가져오기
	public ArrayList<Reply> getReplies(int board_num) throws Exception;

	public int insertReply(Reply reply) throws Exception;

	public int deleteReply(int reply_num) throws Exception;

	public Reply getReply(int reply_num) throws Exception;

	public int updateReply(Reply reply) throws Exception;

	// 가장 최근에 등록된 댓글의 reply_num 뽑아오기
	public int recentlyAddedReplynum() throws Exception;

	public int updateRReply_num(int reply_num) throws Exception;

}
