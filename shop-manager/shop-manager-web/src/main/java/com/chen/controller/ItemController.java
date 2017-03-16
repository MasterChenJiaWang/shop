/**
 * 
 */
package com.chen.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.EUDataGridResult;
import com.chen.common.pojo.ShopResult;
import com.chen.pojo.TbItem;
import com.chen.service.ItemService;

/**
 *<p>标题: ItemController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月8日 下午7:19:38
 *@版本 
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId){
		TbItem item = itemService.geTbItemId(itemId);
		return item;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows){
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public ShopResult saveItem(TbItem item,String desc,String itemParams ) throws Exception{
		
		itemService.createItem(item, desc, itemParams);
		return ShopResult.ok();
	}
	
	
	 
}
