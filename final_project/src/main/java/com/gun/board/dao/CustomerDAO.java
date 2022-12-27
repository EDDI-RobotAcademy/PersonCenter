package com.gun.board.dao;

import java.util.ArrayList;

import com.gun.board.vo.Customer;

public interface CustomerDAO {

	// ȸ������
	public int insertCustomer(Customer customer) throws Exception;

	// ȸ���˻�
	public Customer selectCustomer(String cus_id) throws Exception;

	// �г��Ӱ˻�
	public String selectNickname(String cus_id) throws Exception;

	// ��й�ȣ ã��
	public String findPassword(Customer customer) throws Exception;
	
	//���� ����
	public void insertPhoto(Customer customer) throws Exception;
	
	//���� �ҷ�����
	public Customer getPhoto(String cus_id) throws Exception;
	
	//ȸ�� ������Ʈ
	public void updateCustomer(Customer customer) throws Exception;
	
	//���� ������Ʈ
	public void updatePhoto(Customer customer) throws Exception;
	
	//ȸ������ ���̵� üũ
	public int joinIdCheck(String cus_id) throws Exception;
	
	//��й�ȣ �ߺ�üũ
	public int passChk(Customer customer) throws Exception;
	
	//ȸ��Ż��
	public void deleteCustomer(String cus_id) throws Exception;
	
	//ȸ��������ȸ
	public ArrayList<Customer> selectAllCustomer() throws Exception;

	
	

}
