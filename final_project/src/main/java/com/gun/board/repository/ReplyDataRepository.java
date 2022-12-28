package com.gun.board.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.ReplyDataDAO;
import com.gun.board.vo.Reply_Data;

@Repository
public class ReplyDataRepository {

	@Inject
	private SqlSession sqlSession;

	ReplyDataDAO rdao;

	public ArrayList<Reply_Data> getReplies(int board_num) {
		ArrayList<Reply_Data> result = new ArrayList();
		rdao = sqlSession.getMapper(ReplyDataDAO.class);
		try {
			result = rdao.getReplies(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertReply(Reply_Data reply_data) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDataDAO.class);
		try {
			result = rdao.insertReply(reply_data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteReply(int reply_num) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDataDAO.class);
		try {
			result = rdao.deleteReply(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Reply_Data getReply(int reply_num) {
		Reply_Data result = new Reply_Data();
		rdao = sqlSession.getMapper(ReplyDataDAO.class);
		try {
			result = rdao.getReply(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateReply(Reply_Data reply_data) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDataDAO.class);
		try {
			result = rdao.updateReply(reply_data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int recentlyAddedReplynum() {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDataDAO.class);
		try {
			result = rdao.recentlyAddedReplynum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateRReply_num(int reply_num) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDataDAO.class);
		try {
			result = rdao.updateRReply_num(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
