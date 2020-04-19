package com.kcx.mall.pic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcx.mall.pic.pojo.Pic;
import com.kcx.mall.pic.service.PicService;

/**
 * @date 2020-04-15
 * @author kcx
 * @description 
 */
@Controller
public class PicController {

	@Autowired
	private PicService service;
	
	// 增加图片
	@RequestMapping("/pic/add")
	public void addPic(HttpServletRequest request, HttpServletResponse response, Pic pic) {
		service.addPic(pic);
	}
	
	//删除图片
	@RequestMapping("/pic/delete")
	public void deletePic(HttpServletRequest request, HttpServletResponse response, int picId) {
		service.deleteById(picId);
	}
	
	//删除图片
	@RequestMapping("/pic/deleteMany")
	public void deleteMany(HttpServletRequest request, HttpServletResponse response, int[] ids) {
		service.deleteMany(ids);
	}
	
	// 根据id查询
	@RequestMapping("/pic/queryByPicProId")
	@ResponseBody
	public List<Pic> queryByPicProId(HttpServletRequest request, HttpServletResponse response, int picProId) {
		return service.queryByProId(picProId);
	}
	
}
