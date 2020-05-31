package com.kcx.mall.product.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcx.mall.common.Pager;
import com.kcx.mall.product.dao.ProductMapper;
import com.kcx.mall.product.index.ProductIndexDao;
import com.kcx.mall.product.pojo.Product;

/**
 * @date 2020-04-16
 * @author kcx
 * @description 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ProductService {

	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private ProductIndexDao indexDao;
	
	//增加产品
	public void addProduct(Product product) {
		mapper.addProduct(product);
		// 获得插入员工的自动编号
		int proId = mapper.getLastInsertId();

		try {
			/********** 生成索引 *************/
		    // 创建索引文档
			Document document = new Document();
			document.add(new TextField("proId", String.valueOf(proId), Store.YES));
			document.add(new TextField("proName", product.getProName(), Store.YES));
			document.add(new TextField("proInfo", product.getProInfo(), Store.YES));

			// 调用索引dao
			indexDao.create(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除产品
	public void deleteById(int proId) {
		mapper.deleteProduct(proId);
		
		// 索引
		try {
			Term term = new Term("proId", String.valueOf(proId));
			// 调用索引dao
			indexDao.delete(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//批量删除
	public void deleteMany(int[] ids) {
		for(int  i = 0;i<ids.length;i++){
			int proId = ids[i];
			// 索引
			try {
				Term term = new Term("proId", String.valueOf(proId));
				// 调用索引dao
				indexDao.delete(term);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mapper.deleteMany(ids);
	}
	
	//批量审核通过
	public void updateMany(int[] ids,int proManaId) {
		mapper.updateMany(ids, proManaId);
	}
	
	//查询商品
	@Transactional(readOnly=true)
	public Product queryPro(int proId) {
		return mapper.queryById(proId);
	}
	
	//查询商品
	@Transactional(readOnly=true)
	public Product queryTotalPro(int proId) {
		return mapper.queryTotalById(proId);
	}
	
	//修改商品信息
	public void updatePro(Product product) {
		mapper.updateProduct(product);
		// 索引
		try {
			Term term = new Term("proId", String.valueOf(product.getProId()));

			// 创建索引文档
			Document document = new Document();
			document.add(new TextField("proId", String.valueOf(product.getProId()), Store.YES));
			document.add(new TextField("proName", product.getProName(), Store.YES));
			document.add(new TextField("proInfo", product.getProInfo(), Store.YES));

			// 调用索引dao
			indexDao.update(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改商品状态
	public void UpdateStatement(int proId) {
		mapper.updateState(proId);
	}
	
	//得到条件查询员工数量
	@Transactional(readOnly = true)
	public int getCountByCondition(Integer proCusId, Integer proShopId, Integer proPtId,String proState) {
		return mapper.getCountByCondition(proCusId, proShopId, proPtId, proState);
	}
	
	//动态查询
	@Transactional(readOnly = true)
	public List<Product> queryByCondition(Pager pager,Integer proCusId, Integer proShopId, Integer proPtId,String proState){
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(), proCusId, proShopId, proPtId,proState);
	}
	
	//动态查询无分页
	@Transactional(readOnly = true)
	public List<Product> queryByConditionNoPager(Integer proCusId, Integer proShopId, Integer proPtId,String proState){
		return mapper.queryByConditionNoPager( proCusId, proShopId, proPtId,proState);
	}
	
	//查询商品图片
	@Transactional(readOnly = true)
	public String getPic(int proId){
		return mapper.queryPhoto(proId);
	}
	
	//查询所有
	@Transactional(readOnly = true)
	public List<Product> query(Pager pager){
		return mapper.query(pager.getStart(), pager.getPageSize());
	}
	
	//查询发布总数（不含买了的）
	@Transactional(readOnly = true)
	public int getAllCount(){
		return mapper.getAllCount();
	}
	
	
	/**
	 * 全文检索商品
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	@Transactional(readOnly = true)
	public List<Product> queryProByIndex(String queryStr) throws ParseException, IOException, InvalidTokenOffsetsException {

		// 设置常见停用词（的，么，啊，着之类的东东）
		String[] self_stop_words = { "的", "着", "啊", "么", "嘛" , "是" ,"和"};
		CharArraySet cas = new CharArraySet(Version.LUCENE_47, 0, true);
		for (int i = 0; i < self_stop_words.length; i++) {
			cas.add(self_stop_words[i]);
		}

		// 加入系统默认停用词
		Iterator<Object> itor = StandardAnalyzer.STOP_WORDS_SET.iterator();
		while (itor.hasNext()) {
			cas.add(itor.next());
		}

		// 中文分词器（设置停用词）
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47,cas);

		// 创建查询解析器对象，多字段搜索
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
				new String[] { "proId", "proName", "proInfo" }, analyzer);

		// 创建查询对象
		Query query = queryParser.parse(queryStr);

		// 调用索引dao
		List<Product> list = indexDao.search(query);

		return list;
	}
	
}
