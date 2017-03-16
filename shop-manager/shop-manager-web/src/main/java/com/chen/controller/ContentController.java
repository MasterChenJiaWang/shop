/**
 * 
 */
package com.chen.controller;

import javax.annotation.Resource;

import org.apache.taglibs.standard.lang.jstl.Literal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.EUDataGridResult;
import com.chen.service.ContentService;

/**
 *<p>标题: ContentController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Resource
	private ContentService contentService;
	
	//加载列表
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(int page, int  rows){
		
		EUDataGridResult result = contentService.getContentList(page, rows);
		return  result;
	}
	
	
}
