/**
 * 
 */
package com.chen.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.HttpClientUtil;
import com.chen.portal.pojo.SearchResult;
import com.chen.portal.service.SearchService;

/**
 *<p>标题: SearchServiceImpl </p>
 *<p>描述：商品搜索Service </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
public class SearchServiceImpl  implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	
	/* 
	 *
	 */
	@Override
	public SearchResult search(String queryString, int page) {
		
		//调用shop-search服务
		Map<String, String> map = new HashMap<>();
		map.put("q",queryString);
		map.put("page", page+"");
	
		
		try {
			//调用服务
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, map);
			//把字符串转换成java对象
			ShopResult shopResult = ShopResult.formatToPojo(json, SearchResult.class);
			//如果调用服务正常
			if(shopResult.getStatus()==200){
				//
				SearchResult searchResult = (SearchResult)shopResult.getData();
				return searchResult;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
