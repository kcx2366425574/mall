package com.kcx.mall.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kcx.mall.customer.service.CustomerService;

/**
 * @date 2020-04-04
 * @author kcx
 * @description 
 */
@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	
}
