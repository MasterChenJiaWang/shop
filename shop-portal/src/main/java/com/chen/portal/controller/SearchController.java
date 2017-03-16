/**
 * 
 */
package com.chen.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.portal.pojo.SearchResult;
import com.chen.portal.service.SearchService;

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
	
	
	@RequestMapping("/search")
	@ResponseBody
	public String search(@RequestParam("q")String queryString, 
			@RequestParam(defaultValue="1")Integer page, Model model){
		
		if(queryString!=null){
			try {
				queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		SearchResult search = searchService.search(queryString, page);
		model.addAttribute("query", queryString);
		model .addAttribute("totalPages",search.getPageCount());
		model.addAttribute("itemList",search.getItemList());
		model.addAttribute("page", page);
		return "search";
		
	}
	
}
