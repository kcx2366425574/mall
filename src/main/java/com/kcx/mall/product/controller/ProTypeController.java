package com.kcx.mall.product.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.common.Pager;
import com.kcx.mall.product.pojo.ProType;
import com.kcx.mall.product.service.ProTypeService;

/**
 * @date 2020-04-02
 * @author kcx
 * @description 
 */
@Controller
public class ProTypeController {

	@Autowired
	private ProTypeService service;
	
	// 增加
	@RequestMapping("/proType/add")
	public void addProType(HttpServletRequest request, HttpServletResponse response, ProType proType) {
		service.addProType(proType);
	}
	
	// 删除
	@RequestMapping("/proType/delete")
	public void deleteProType(HttpServletRequest request, HttpServletResponse response, int ptId) {
		service.deleteById(ptId);
	}
	
	// 根据id批量删除
	@RequestMapping("/proType/deleteMany")
	public void deleteMany(HttpServletRequest request, HttpServletResponse response, int[] ids) {
		service.deleteMany(ids);
	}
	
	// 根据id查询
	@RequestMapping("/proType/get")
	@ResponseBody
	public ProType getProTypeById(HttpServletRequest request, HttpServletResponse response, int ptId) {
		return service.queryById(ptId);
	}
	
	// 修改
	@RequestMapping("/proType/update")
	public void updateProType(HttpServletRequest request, HttpServletResponse response, ProType proType) {
		service.updateProType(proType);
	}
	
	// 查询所有
	@RequestMapping("/proType/getAll")
	@ResponseBody
	public HashMap<String, Object> getAll(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		int count = service.getAllAcount();
		Pager pager = new Pager(count, pageSize, pageNum);
		List<ProType> list = service.queryAll(pager);
		
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
}
