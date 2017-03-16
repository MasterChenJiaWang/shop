/**
 * 
 */
package com.chen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.pojo.TbItemCat;
import com.chen.service.ItemCatService;

/**
 *<p>标题: ItemCatController </p>
 *<p>描述：商品分类管理controller </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Resource
	private ItemCatService itemCatService;
	
	private static final Logger logger = Logger.getLogger(ItemCatController.class);
	
	
	
	/**
	 * 选择类目
	 * 
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	@RequestMapping("/list")
	@ResponseBody
	//如果id为null是使用默认值，也就是parentid为0的分类列表
	public List categoryList(@RequestParam(value="id",defaultValue="0") Long parentId)throws Exception{
	
		logger.info("选择类目 树形启动。。。");
		List catList = new ArrayList();
		
		List<TbItemCat> list = itemCatService.getItemCatList(parentId);
		
		for(TbItemCat  tbItemCat:list){
			Map<Object, Object>map = new HashMap<>();
			map.put("id", tbItemCat.getId());
			map.put("text", tbItemCat.getName());
			//如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
			map.put("state", tbItemCat.getIsParent()?"closed":"open");
			catList.add(map);
		}
		return catList;
	}
	
}
