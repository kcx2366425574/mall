package com.kcx.mall.product.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.Constant;
import com.kcx.mall.common.Pager;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.product.pojo.ProRecord;
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
	
	// 增加商品记录
	@RequestMapping("/proRecord/add")
	public void addProrecod(HttpServletRequest request, HttpServletResponse response, ProRecord proRecord) {
		service.insertProRecord(proRecord);
	}
	
	// 根据用户id查询
	@RequestMapping("/proRecord/get")
	@ResponseBody
	public HashMap<String, Object> getProById(HttpServletRequest request, HttpServletResponse response, int cusId,Integer pageNum,
			Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		int count = service.getCountByCusId(cusId);
		Pager pager = new Pager(count, pageSize, pageNum);
		List<ProRecord> list = service.queryByCusId(cusId, pager);
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);		
		return map;
	}
	

}
