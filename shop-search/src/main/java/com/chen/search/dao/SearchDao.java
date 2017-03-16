/**
 * 
 */
package com.chen.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.chen.search.pojo.SearchResult;

/**
 *<p>标题: SearchDao </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
