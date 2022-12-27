package com.gun.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.board.dao.CustomerDAO;
import com.gun.board.vo.Customer;

@Repository
public class CustomerRepository {

	@Inject
	private SqlSession sqlSession;

	CustomerDAO cdao;

	private static final String namespace = "com.gun.board.mappers.customerMapper";

	public int insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		int result = 0;
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			result = cdao.insertCustomer(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Customer selectCustomer(String cus_id) {
		Customer customer = new Customer();
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			customer = cdao.selectCustomer(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public String selectNickname(String cus_id) {
		String nickname = "";
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			nickname = cdao.selectNickname(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nickname;
	}

	public String findPassword(Customer customer) {
		cdao = sqlSession.getMapper(CustomerDAO.class);
		String result = "";
		try {
			result = cdao.findPassword(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 사진 삽입
	public void insertPhoto(Customer customer) {
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			cdao.insertPhoto(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 사진 가져오기
	public Customer getPhoto(String cus_id) {
		Customer result = new Customer();
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			result = cdao.getPhoto(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Customer> updateCustomer(Customer customer) {

		ArrayList<Customer> result = new ArrayList();
		cdao = sqlSession.getMapper(CustomerDAO.class);

		try {
			cdao.updateCustomer(customer);

			cdao.updatePhoto(customer);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int joinIdCheck(String cus_id) {
		int result = 0;
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			result = cdao.joinIdCheck(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int passChk(Customer customer) {
		int result = 0;
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			result = cdao.passChk(customer);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public void deleteCustomer(String cus_id) {
		cdao = sqlSession.getMapper(CustomerDAO.class);
		try {
			cdao.deleteCustomer(cus_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 회원 전부 찾기
	public ArrayList<Customer> selectAllCustomer() {
		ArrayList<Customer> result = new ArrayList();
		cdao = sqlSession.getMapper(CustomerDAO.class);

		try {
			result = cdao.selectAllCustomer();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

}
