/**
 * 
 */
package com.chen.search.service;

import com.chen.search.pojo.SearchResult;

/**
 *<p>标题: SearchService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface SearchService {

	SearchResult search(String queryString, int page, int rows) throws Exception;
}
