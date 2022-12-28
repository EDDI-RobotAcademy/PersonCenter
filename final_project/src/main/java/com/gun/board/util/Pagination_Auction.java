package com.gun.board.util;

import java.util.ArrayList;

import com.gun.board.vo.Auction;
import com.gun.board.vo.Customer;
import com.gun.board.vo.Data;
import com.gun.board.vo.Message;

public class Pagination_Auction {
	// ����¡ó�� ���� Ŭ����

	// Ȩ�� ��Ÿ�� �Խù�(7���� �ڸ� ��)	
	public ArrayList<Auction> totalPosts_home(ArrayList<Auction> auction, int page) {
        ArrayList<Auction> result = new ArrayList();
            int startPost = (page - 1) * Configuration.POSTS_HOME;
            int endPost = startPost + Configuration.POSTS_HOME;
            if (endPost > auction.size()) {
                endPost = auction.size();
             }
             for (int a = startPost; a < endPost; a++) {
                   result.add(auction.get(a));
             }
             return result;
    }

	// �� ������ ���
	public int totalPages(ArrayList<Auction> auction) {
		int result = 0;
		result = (auction.size() / Configuration.POSTS);
		if (auction.size() % Configuration.POSTS != 0 || result == 0) {
			result += 1;
		}
		return result;
	}

	public int totalPagesMessage(ArrayList<Message> message) {
		int result = 0;
		result = (message.size() / Configuration.POSTS);
		if (message.size() % Configuration.POSTS != 0 || result == 0) {
			result += 1;
		}
		return result;
	}

	// ������ ������ ���ϱ�
	public int endPage(int page, int totalPages) {
		int endPage = 0;
		endPage = page + Configuration.PAGES;
		if (endPage > totalPages) {
			endPage = totalPages;
		}
		return endPage;
	}

	// �� �������� ��Ÿ���� �Խñ�
	public ArrayList<Auction> totalPosts(ArrayList<Auction> auction, int page) {
		ArrayList<Auction> result = new ArrayList();
		int startPost = (page - 1) * Configuration.POSTS;
		int endPost = startPost + Configuration.POSTS;
		if (endPost > auction.size()) {
			endPost = auction.size();
		}
		for (int a = startPost; a < endPost; a++) {
			result.add(auction.get(a));
		}
		return result;
	}

	public ArrayList<Message> totalPostsMessage(ArrayList<Message> message, int page) {
		ArrayList<Message> result = new ArrayList();
		int startPost = (page - 1) * Configuration.POSTS;
		int endPost = startPost + Configuration.POSTS;
		if (endPost > message.size()) {
			endPost = message.size();
		}
		for (int a = startPost; a < endPost; a++) {
			result.add(message.get(a));
		}
		return result;
	}

	// ������ �� ����
	public int getCurrentPage(int page, int totalPages) {
		if (page <= 0) {
			page = 1;
		} else if (page > totalPages && totalPages != 0) {
			page = totalPages;
		}
		return page;
	}

	// ������ ������ ���������̼� �߰��Ȱ�
	public static int cus_totalPages(ArrayList<Customer> customers) {
		int result = 0;
		result = (customers.size() / Configuration.POSTS);
		if (customers.size() % Configuration.POSTS != 0 || result == 0) {
			result += 1;
		}
		return result;
	}

	public static ArrayList<Customer> cus_totalPosts(ArrayList<Customer> customers, int page) {
		ArrayList<Customer> result = new ArrayList();
		int startPost = (page - 1) * Configuration.POSTS;
		int endPost = startPost + Configuration.POSTS;
		if (endPost > customers.size()) {
			endPost = customers.size();
		}
		for (int a = startPost; a < endPost; a++) {
			result.add(customers.get(a));
		}
		return result;
	}

	public static int cus_getCurrentPage(int page, int totalPages) {
		if (page <= 0) {
			page = 1;
		} else if (page > totalPages && totalPages != 0) {
			page = totalPages;
		}
		return page;
	}

	public static int cus_endPage(int page, int totalPages) {
		int endPage = 0;
		endPage = page + Configuration.PAGES;
		if (endPage > totalPages) {
			endPage = totalPages;
		}
		return endPage;
	}

}
