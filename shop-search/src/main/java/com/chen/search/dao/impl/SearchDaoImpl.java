/**
 * 
 */
package com.chen.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chen.search.dao.SearchDao;
import com.chen.search.pojo.Item;
import com.chen.search.pojo.SearchResult;

/**
 *<p>标题: SearchDaoImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private SolrServer solrServer;

	/* 
	 *
	 */
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		//返回值对象
		SearchResult searchResult = new SearchResult();
		//根据查询条件查询索引库
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		//取查询结果总数量,并存入searchResult中
		searchResult.setRecordCount(solrDocumentList.getNumFound());
		//商品列表
		List<Item> itemList = new ArrayList<>();
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		for(SolrDocument solrDocument:solrDocumentList){
			
			Item item = new Item();
			
			item.setId((String)solrDocument.get("id"));
			//取高亮显示的结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			
			String title = "";
			if (list != null && list.size()>0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			//添加的商品列表
			itemList.add(item);

		}
		//存入searchResult中
		searchResult.setItemList(itemList);
		return searchResult;

	}

}
