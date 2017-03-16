/**
 * 
 */
package com.chen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *<p>标题: PageController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月8日 下午8:20:44
 *@版本 
 */
@Controller
public class PageController {

	/**
	 * 跳转首页
	 * 
	 *@时间 2017年3月8日 下午8:26:24
	 */
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public  String showPage(@PathVariable String page){
		
		return page;
	}
}
