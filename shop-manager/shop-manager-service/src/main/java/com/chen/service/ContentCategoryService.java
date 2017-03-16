/**
 * 
 */
package com.chen.service;

import java.util.List;

import com.chen.common.pojo.EUTreeNode;
import com.chen.common.pojo.ShopResult;

/**
 *<p>标题: ContentCategoryService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface ContentCategoryService {

	public List<EUTreeNode> getCategoryList(long parentId);
	public ShopResult insertContentCat(long parentId, String name);
	
	public ShopResult deleteContentCat(long parentId,long id);
	public ShopResult updateContentCat(String name,long id);
}
