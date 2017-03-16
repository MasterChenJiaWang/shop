/**
 * 
 */
package com.chen.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.EUTreeNode;
import com.chen.common.pojo.ShopResult;
import com.chen.service.ContentCategoryService;

/**
 *<p>标题: ContentCategoryController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Resource
	private ContentCategoryService contentCategoryService;
	
	private static final Logger logger = Logger.getLogger(ItemCatController.class);
	
	@RequestMapping("/list")
	@ResponseBody
	public  List<EUTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId ){
		
		logger.info("内容分类列表查询。。。。。");
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		logger.info("内容分类列表查询完成>>>>>>>>>>>>");
		return list;

	}
	
	
	/**
	 * 添加内容分类
	 * 
	 */
	@RequestMapping("/create")
	@ResponseBody
	public ShopResult insertContentCat(Long parentId, String name){
		logger.info("正在进行内容分类添加。。。。。");
		ShopResult shopResult = contentCategoryService.insertContentCat(parentId, name);
		logger.info("内容分类添加完成。。。。。");
		return shopResult;
	}
	
	
	/**
	 * 删除
	 * 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ShopResult insertContentCat(Long parentId, Long id){
		logger.info("正在进行内容分类删除。。。。。");
		ShopResult shopResult = contentCategoryService.deleteContentCat(parentId,id);
		logger.info("内容分类删除完成。。。。。");
		return shopResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ShopResult updateContentCat(String name, Long id){
		logger.info("正在进行内容分类修改。。。。。");
		ShopResult shopResult = contentCategoryService.updateContentCat(name,id);
		logger.info("内容分类修改完成。。。。。");
		return shopResult;
	}
	
	
	
}
