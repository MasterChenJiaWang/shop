/**
 * 
 */
package com.chen.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.ExceptionUtil;
import com.chen.search.pojo.SearchResult;
import com.chen.search.service.SearchService;

/**
 *<p>标题: SearchController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	
	/**
	 * 
	 * 
	 */
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public ShopResult search(@RequestParam("q")String queryString, 
			@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="60")Integer rows) {
		//查询条件不能为空
		if (StringUtils.isBlank(queryString)) {
			return ShopResult.build(400, "查询条件不能为空");
		}
		SearchResult searchResult =null;
		try {
			//解决get乱码
			queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
			searchResult = searchService.search(queryString, page, rows);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ShopResult.build(500,ExceptionUtil.getStackTrace(e));
		}
		
		return ShopResult.ok(searchResult);
		
		
	}
}
