/**
 * 
 */
package com.chen.search;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.aop.ThrowsAdvice;

/**
 *<p>标题: SolrCloudTest </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public class SolrCloudTest {

	@Test
	public void queryDocument() throws Exception{
		
		HttpSolrServer solrServer = new HttpSolrServer("http://192.168.235.128:8080/solr");
		//创建一个查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(50);
		//执行查询
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("共查询到记录：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
				System.out.println(solrDocument.get("id"));
				System.out.println(solrDocument.get("item_title"));
				System.out.println(solrDocument.get("item_price"));
				System.out.println(solrDocument.get("item_image"));
					
				}

		
	}
	
}
