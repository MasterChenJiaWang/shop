/**
 * 
 */
package com.chen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.ShopResult;
import com.chen.rest.service.ItemService;

/**
 *<p>标题: ItemController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月14日 上午10:42:58
 *@版本 
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	
	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public ShopResult getItemBaseInfo(@PathVariable Long itemId) {
		ShopResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}

	
	/**
	 * 接收商品id根据商品id查询商品描述
	 * 
	 *@时间 2017年3月14日 上午11:22:30
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public ShopResult getItemDesc(@PathVariable Long itemId) {
		ShopResult result = itemService.getItemDesc(itemId);
		return result;
	}

	/**
	 * 
	 * 取商品规格参数
	 *@时间 2017年3月14日 上午11:23:37
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public ShopResult getItemParam(@PathVariable Long itemId) {
		 ShopResult result = itemService.getItemParam(itemId);
		return result;
	}

	
	
}
