/**
 * 
 */
package com.chen.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.ShopResult;
import com.chen.search.service.ItemService;

/**
 *<p>标题: ItemController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/importall")
	@ResponseBody
	public ShopResult importAllItem(){
		
		ShopResult shopResult = itemService.importAllItems();
		return shopResult;
	}
}
