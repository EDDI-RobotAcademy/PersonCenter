package com.gun.board.dao;

import java.util.ArrayList;

import com.gun.board.vo.Customer;

public interface CustomerDAO {

	// 회원가입
	public int insertCustomer(Customer customer) throws Exception;

	// 회원검색
	public Customer selectCustomer(String cus_id) throws Exception;

	// 닉네임검색
	public String selectNickname(String cus_id) throws Exception;

	// 비밀번호 찾기
	public String findPassword(Customer customer) throws Exception;
	
	//사진 삽입
	public void insertPhoto(Customer customer) throws Exception;
	
	//사진 불러오기
	public Customer getPhoto(String cus_id) throws Exception;
	
	//회원 업데이트
	public void updateCustomer(Customer customer) throws Exception;
	
	//사진 업데이트
	public void updatePhoto(Customer customer) throws Exception;
	
	//회원가입 아이디 체크
	public int joinIdCheck(String cus_id) throws Exception;
	
	//비밀번호 중복체크
	public int passChk(Customer customer) throws Exception;
	
	//회원탈퇴
	public void deleteCustomer(String cus_id) throws Exception;
	
	//회원정보조회
	public ArrayList<Customer> selectAllCustomer() throws Exception;

	
	

}
