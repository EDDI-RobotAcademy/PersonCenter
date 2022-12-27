package com.gun.board.dao;

import java.util.ArrayList;

import com.gun.board.vo.Reply_Data;

public interface ReplyDataDAO {

	// ��ü ��� ���� ��������
	public ArrayList<Reply_Data> getReplies(int board_num) throws Exception;

	public int insertReply(Reply_Data reply_data) throws Exception;

	public int deleteReply(int reply_num) throws Exception;

	public Reply_Data getReply(int reply_num) throws Exception;

	public int updateReply(Reply_Data reply_data) throws Exception;

	// ���� �ֱٿ� ��ϵ� ����� reply_num �̾ƿ���
	public int recentlyAddedReplynum() throws Exception;

	public int updateRReply_num(int reply_num) throws Exception;

}
