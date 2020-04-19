package com.kcx.mall.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kcx.mall.product.service.ProRecordService;

/**
 * @date 2020-04-16
 * @author kcx
 * @description 
 */
@Controller
public class ProRecordController {

	@Autowired
	private ProRecordService service;
}
