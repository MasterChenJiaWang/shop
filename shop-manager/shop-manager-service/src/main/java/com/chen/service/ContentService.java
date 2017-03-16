/**
 * 
 */
package com.chen.service;

import java.util.List;

import com.chen.common.pojo.EUDataGridResult;
import com.chen.common.pojo.ShopResult;
import com.chen.pojo.TbContent;

/**
 *<p>标题: ContentService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface ContentService {

	
	public EUDataGridResult getContentList(int page, int rows);
	ShopResult insertContent(TbContent content);
}
