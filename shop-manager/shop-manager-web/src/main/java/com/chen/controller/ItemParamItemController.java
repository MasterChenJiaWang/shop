/**
 * 
 */
package com.chen.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chen.service.ItemParamItemService;

/**
 *<p>标题: ItemParamItemController </p>
 *<p>描述： 展示商品规格参数</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月11日 上午9:16:24
 *@版本 
 */
@Controller

public class ItemParamItemController {

	@Resource
	private ItemParamItemService itemParamItemService;
	
	
	
	/**
	 * 展示商品参数
	 * 通过商品id 获得对应的规格参数，参数都包含在string中，里面含有html语句
	 */
	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}

}
