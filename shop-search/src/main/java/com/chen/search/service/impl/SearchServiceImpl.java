/**
 * 
 */
package com.chen.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.search.dao.SearchDao;
import com.chen.search.pojo.SearchResult;
import com.chen.search.service.SearchService;

/**
 *<p>标题: SearchServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	/* 
	 *
	 */
	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		//创建查询对象
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery(queryString);
		//设置分页
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		
		//设置默认搜索域
		solrQuery.set("df", "item_keywords");
		//设置高亮显示
		solrQuery.setHighlight(true);
		//设置格式： 红色
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		
		//执行查询
		SearchResult searchResult = searchDao.search(solrQuery);
		//计算查询结果总页数
		long recordCount = searchResult.getRecordCount();
		//总页数(舍去小数点)
		long pageCount=recordCount/rows;
		//不能整除 加1
		if(recordCount%rows>0){
			pageCount++;
		}
		
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		return searchResult;
	}

}
