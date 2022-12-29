package com.gun.board.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gun.board.repository.CustomerRepository;
import com.gun.board.util.Configuration;
import com.gun.board.util.Pagination;
import com.gun.board.vo.Customer;

@RequestMapping(value = "/admin")
@Controller

public class AdminController {

	@Inject
	CustomerRepository cRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String selectAllCustomer(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		ArrayList<Customer> customers = cRepository.selectAllCustomer();

		System.out.println("selectAllCustomer : " + customers);
		model.addAttribute("customers", customers);

		int totalPages = Pagination.cus_totalPages(customers);
		page = Pagination.cus_getCurrentPage(page, totalPages);
		customers = Pagination.cus_totalPosts(customers, page);
		int endPage = Pagination.cus_endPage(page, totalPages);

		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);
		model.addAttribute("customers", customers);

		return "admin/adminView";
	}

	// 회원선택삭제
	@RequestMapping(value = "/delete")
	public String ajaxTest(HttpServletRequest request, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) throws Exception {

		System.out.println("하이루");
		System.out.println("delete �룄�떖@@@");
		String[] ajaxMsg = request.getParameterValues("valueArr");

		int size = ajaxMsg.length;

		for (int i = 0; i < size; i++) {

			cRepository.deleteCustomer(ajaxMsg[i]);
		}

		ArrayList<Customer> customers = cRepository.selectAllCustomer();

		System.out.println("selectAllCustomer : " + customers);

		//
		int totalPages = Pagination.cus_totalPages(customers);
		page = Pagination.cus_getCurrentPage(page, totalPages);
		customers = Pagination.cus_totalPosts(customers, page);
		int endPage = Pagination.cus_endPage(page, totalPages);

		model.addAttribute("page", page);
		model.addAttribute("endPage", endPage);

		model.addAttribute("customers", customers);

		return "admin/adminView";
	}

	// 회원상세보기
	@RequestMapping(value = "/detailView", method = RequestMethod.GET)
	public String vertify_info(String customer_id, Model model) {

		System.out.println("customer_id : " + customer_id);
		String cus_id = customer_id;

		Customer cusCompare = cRepository.selectCustomer(cus_id);
		System.out.println("cusCompare : " + cusCompare);
		model.addAttribute("customer", cusCompare);

		return "admin/detailView";
	}

	// 파일선택
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(HttpServletResponse response, String customer_id) {

		String cus_id = customer_id;
		Customer customer = cRepository.getPhoto(cus_id);

		String originalfile = customer.getBoard_fileid();

		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fullpath = Configuration.PHOTOPATH + "/" + customer.getBoard_uploadfileid();

		ServletOutputStream fileout = null;
		FileInputStream filein = null;

		try {
			filein = new FileInputStream(fullpath);
			fileout = response.getOutputStream();
			FileCopyUtils.copy(filein, fileout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (filein != null) {
					filein.close();
				}
				if (fileout != null) {
					fileout.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
