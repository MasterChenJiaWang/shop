/**
 * 
 */
package com.chen.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.ShopResult;
import com.chen.pojo.TbItemParam;
import com.chen.service.ItemParamService;

/**
 *<p>标题: ItemParamController </p>
 *<p>描述：商品规格参数模板管理Controller </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Resource
	private ItemParamService itemParamService;
	
	
	
	/**
	 * 新增商品规格模板  
	 * cid   paramData 对应item-param-add.jsp中返回的模板参数
	 * 
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public ShopResult insertItemParam(@PathVariable Long cid,String paramData){
		
		TbItemParam itemParam = new TbItemParam();
		
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		ShopResult shopResult = itemParamService.insertItemParam(itemParam);
		
		
		return shopResult;
	}
	
	
	/**
	 * 判断商品模板是否已经创建
	 * 通过商品Id 获得对应的商品模板
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public ShopResult getItemParamByCid(@PathVariable Long itemCatId) {
		ShopResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
}
