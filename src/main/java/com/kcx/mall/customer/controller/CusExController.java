package com.kcx.mall.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.customer.pojo.CusEx;
import com.kcx.mall.customer.service.CusExService;

/**
 * @date 2020-04-04
 * @author kcx
 * @description 
 */

@Controller
public class CusExController {

	@Autowired
	private CusExService service;
	
	// 通过id查找
	@RequestMapping("/cusEx/getCusExById")
	@ResponseBody
	public CusEx getCusExById(HttpServletRequest request, HttpServletResponse response, int ceCusId) {
		return service.queryById(ceCusId);
	}
}
