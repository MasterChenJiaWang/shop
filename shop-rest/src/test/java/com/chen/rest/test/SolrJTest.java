/**
 * 
 */
package com.chen.rest.test;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 *<p>标题: SolrJTest </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public class SolrJTest {

	@Test
	public void addDocument() throws Exception{
		
		//创建一连接
	SolrServer solrServer = new HttpSolrServer("http://192.168.235.128:8080/solr");

	//创建一个文档对象
	SolrInputDocument document = new SolrInputDocument();
	document.addField("id", "test001");
	document.addField("item_title", "测试商品2");
	document.addField("item_price", 54321);
	
	//把文档对象写入索引库
	solrServer.add(document);
	//提交
	solrServer.commit();

	}
	
	
	
	@Test
	public void deleteDocument() throws Exception {
		//创建一连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.235.128:8080/solr");
		//solrServer.deleteById("test001");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}

}
