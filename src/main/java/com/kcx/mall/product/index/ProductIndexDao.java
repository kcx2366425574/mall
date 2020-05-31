package com.kcx.mall.product.index;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kcx.mall.product.pojo.Product;


/**
 * @date 2020-05-13
 * @author kcx
 * @description 
 */
@Repository
public class ProductIndexDao {

	// 员工索引目录，使用资源文件的键值对
		@Value("#{prop.pro_index_path}")
		private String proIndexPath;

		// 中文分词器
		public Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);

		/**
		 * 增加索引
		 * 
		 * @throws IOException
		 */
		public void create(Document document) throws IOException {
			// 设置索引的分词器
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
			// 索引目录对象
			Directory directory = FSDirectory.open(new File(proIndexPath));
			// 索引写入对象
			IndexWriter indexWriter = new IndexWriter(directory, config);
			// 增加索引
			indexWriter.addDocument(document);
			// 提交
			indexWriter.commit();
			// 关闭
			indexWriter.close();
		}

		/**
		 * 更新索引
		 * 
		 * @throws IOException
		 */
		public void update(Term term, Document document) throws IOException {
			// 设置索引的分词器
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
			// 索引目录对象
			Directory directory = FSDirectory.open(new File(proIndexPath));
			// 索引写入对象
			IndexWriter indexWriter = new IndexWriter(directory, config);
			// 修改索引
			indexWriter.updateDocument(term, document);
			// 提交
			indexWriter.commit();
			// 关闭
			indexWriter.close();
		}

		/**
		 * 删除索引
		 * 
		 * @throws IOException
		 */
		public void delete(Term term) throws IOException {
			// 设置索引的分词器
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
			// 索引目录对象
			Directory directory = FSDirectory.open(new File(proIndexPath));
			// 索引写入对象
			IndexWriter indexWriter = new IndexWriter(directory, config);
			// 删除索引
			indexWriter.deleteDocuments(term);
			// 提交
			indexWriter.commit();
			// 关闭
			indexWriter.close();
		}

		/**
		 * 全文检索
		 * 
		 * @throws IOException
		 * @throws InvalidTokenOffsetsException 
		 */
		public List<Product> search(Query query) throws IOException, InvalidTokenOffsetsException {

			// 设置索引的分词器
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
			// 索引目录对象
			Directory directory = FSDirectory.open(new File(proIndexPath));

			// 搜索对象
			IndexReader indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);

			// 过滤对象(附加的删选条件，效率比较低，因为需要二次遍历数据)
			Filter filter = null;

			// 排序对象（默认按照相关度排序，就是匹配的越多越靠前，类似于百度）
			Sort sort = new Sort();

			// 得到满足条件的前100行记录
			TopDocs topDocs = indexSearcher.search(query, 100, sort);

			// 总记录数
			int recordCount = topDocs.totalHits;

			// 文档集合
			ArrayList<Product> proList = new ArrayList<>();

			// ============高亮和截取某个字段的文本摘要设置=============
			// 设置环绕的首尾字符串
			SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=red>", "</font>");
			// 语法高亮显示设置
			Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));
			// 100是是表示摘要的字数
			highlighter.setTextFragmenter(new SimpleFragmenter(100));
			// ===================================================

			// 遍历文档
			for (int i = 0; i < recordCount; i++) {
				// 获得原始文档
				ScoreDoc scoreDoc = topDocs.scoreDocs[i];
				// 获得文档内部编号
				int docSn = scoreDoc.doc;
				// 获得Document文档
				Document document = indexSearcher.doc(docSn);

				// 获得商品编号
				Integer proId = Integer.parseInt(document.get("proId"));
				// 获得商品名
				String proName = document.get("proName");

				// 获得商品详细信息
				String proInfo = document.get("proInfo");
				
				//把商品详细信息进行截取摘要以及添加高亮
				TokenStream tream = analyzer.tokenStream("proInfo",	new StringReader(proInfo));
				String proInfoFragment = highlighter.getBestFragment(tream, proInfo);
				
				//把姓名增加高亮
				tream = analyzer.tokenStream("proName",	new StringReader(proName));
				String proNameFragment = highlighter.getBestFragment(tream, proName);
				
				//如果内容没有包含搜索关键字或原始内容不足100字
				if (proInfoFragment == null) {
					int minLen = proInfo.length() >= 100 ? 100 : proInfo.length();
					proInfoFragment = proInfo.substring(0, minLen);				
				}		
				
				if (proNameFragment == null) {
					int minLen = proName.length() >= 100 ? 100 : proName.length();
					proNameFragment = proName.substring(0, minLen);				
				}
				
				// 封装到Employee对象
				Product product = new Product();

				product.setProId(proId);
				product.setProName(proNameFragment);
				product.setProInfo(proInfoFragment);

				// 放到集合中
				proList.add(product);
			}

			return proList;
		}
}
