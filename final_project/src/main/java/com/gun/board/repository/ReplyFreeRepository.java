package com.gun.board.repository;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.ReplyFreeDAO;
import com.gun.board.vo.Reply_Free;

@Repository
public class ReplyFreeRepository {

	@Inject
	private SqlSession sqlSession;

	ReplyFreeDAO rdao;

	public ArrayList<Reply_Free> getReplies(int board_num) {
		ArrayList<Reply_Free> result = new ArrayList();
		rdao = sqlSession.getMapper(ReplyFreeDAO.class);
		try {
			result = rdao.getReplies(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertReply(Reply_Free reply_free) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyFreeDAO.class);
		try {
			result = rdao.insertReply(reply_free);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteReply(int reply_num) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyFreeDAO.class);
		try {
			result = rdao.deleteReply(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Reply_Free getReply(int reply_num) {
		Reply_Free result = new Reply_Free();
		rdao = sqlSession.getMapper(ReplyFreeDAO.class);
		try {
			result = rdao.getReply(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateReply(Reply_Free reply_free) {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyFreeDAO.class);
		try {
			result = rdao.updateReply(reply_free);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int recentlyAddedReplynum() {
		int result = 0;
		rdao = sqlSession.getMapper(ReplyFreeDAO.class);
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
		rdao = sqlSession.getMapper(ReplyFreeDAO.class);
		try {
			result = rdao.updateRReply_num(reply_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
