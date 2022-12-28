package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.BoardDAO;
import com.gun.board.dao.CustomerDAO;
import com.gun.board.vo.Board;

@Repository
public class BoardRepository {

	@Inject
	private SqlSession sqlSession;

	BoardDAO bdao;

	public int insertBoard(Board board) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.insertBoard(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertPhoto(Board board) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.insertPhoto(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// �ŷ��Խ��� (�߰�)
	public ArrayList<Board> getTransaction(int transaction_name) {
		ArrayList<Board> result = new ArrayList();
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			if (transaction_name == 0) {
				result = bdao.getTransaction_0(transaction_name);
			} else {
				result = bdao.getTransaction(transaction_name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// �Խñ� ��ȸ(����)
	public ArrayList<Board> getBoards(String friend_id) {
		ArrayList<Board> result = new ArrayList();
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.getBoards(friend_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// �Խñ� ��ȸ(Ȩ��)
	public ArrayList<Board> getBoards_home(int i) {
		ArrayList<Board> result = new ArrayList();
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.getBoards_home(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Board getBoard(int board_num) {
		Board result = new Board();
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.getBoard(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void upHits(int board_num) {
		// ��ȸ�� �ø���
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			bdao.upHits(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int deleteBoard(int board_num) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.deleteBoard(board_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateBoard(Board board) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.updateBoard(board);
			bdao.updatePhoto(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updatePhoto(Board board) {
		int result = 0;
		bdao = sqlSession.getMapper(BoardDAO.class);
		try {
			result = bdao.updatePhoto(board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void changeReply(int board_num, int type) {
		bdao = sqlSession.getMapper(BoardDAO.class);
		Map<String, Integer> change = new HashMap();
		change.put("board_num", board_num);
		change.put("type", type);
		try {
			bdao.changeReply(change);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
