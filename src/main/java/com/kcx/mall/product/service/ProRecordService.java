package com.kcx.mall.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcx.mall.product.dao.ProRecordMapper;

/**
 * @date 2020-04-16
 * @author kcx
 * @description 
 */
@Service
public class ProRecordService {

	@Autowired ProRecordMapper mapper;
}
