package com.gun.board.dao;

import java.util.ArrayList;

import com.gun.board.vo.Reply_Free;

public interface ReplyFreeDAO {

	// ��ü ��� ���� ��������
	public ArrayList<Reply_Free> getReplies(int board_num) throws Exception;

	public int insertReply(Reply_Free reply_free) throws Exception;

	public int deleteReply(int reply_num) throws Exception;

	public Reply_Free getReply(int reply_num) throws Exception;

	public int updateReply(Reply_Free reply_free) throws Exception;

	// ���� �ֱٿ� ��ϵ� ����� reply_num �̾ƿ���
	public int recentlyAddedReplynum() throws Exception;

	public int updateRReply_num(int reply_num) throws Exception;
}