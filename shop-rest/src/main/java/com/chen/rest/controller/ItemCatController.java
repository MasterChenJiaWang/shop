/**
 * 
 */
package com.chen.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.rest.pojo.CatResult;
import com.chen.rest.service.ItemCatService;

/**
 *<p>标题: ItemCatController </p>
 *<p>描述：商品分类列表 </p>
 *
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {

	@Resource
	private ItemCatService itemCatService;
	
	
	/**
	 * 接收页面传递过来的参数。参数就是方法的名称。
	 * 返回一个json数据，需要把json数据包装成一句js代码。
	 * 返回一个字符串。
	 */
	@RequestMapping(value="/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback){
		
		CatResult catResult = itemCatService.getItemCatList();
		//转换成json
		MappingJacksonValue jacksonValue = new MappingJacksonValue(catResult);
		//转化成json样式  防止乱码
		jacksonValue.setJsonpFunction(callback);
		
		return jacksonValue;
		
	}
	
}
