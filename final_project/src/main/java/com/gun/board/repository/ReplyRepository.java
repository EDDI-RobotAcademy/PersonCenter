package com.gun.board.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.ReplyDAO;
import com.gun.board.vo.Reply;

@Repository
public class ReplyRepository {

	@Inject
	private SqlSession sqlSession;

	ReplyDAO rdao;

	public ArrayList<Reply> getReplies(int board_num) {
		ArrayList<Reply> result = new ArrayList();
		rdao = sqlSession.getMapper(ReplyDAO.class);
		try {
			result = rdao.getReplies(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertReply(Reply reply) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDAO.class);
		try {
			result = rdao.insertReply(reply);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteReply(int reply_num) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDAO.class);
		try {
			result = rdao.deleteReply(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Reply getReply(int reply_num) {
		Reply result = new Reply();
		rdao = sqlSession.getMapper(ReplyDAO.class);
		try {
			result = rdao.getReply(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateReply(Reply reply) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDAO.class);
		try {
			result = rdao.updateReply(reply);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int recentlyAddedReplynum() {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyDAO.class);
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
		rdao = sqlSession.getMapper(ReplyDAO.class);
		try {
			result = rdao.updateRReply_num(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
