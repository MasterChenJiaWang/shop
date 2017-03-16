/**
 * 
 */
package com.chen.service;

import com.chen.common.pojo.EUDataGridResult;
import com.chen.common.pojo.ShopResult;
import com.chen.pojo.TbItem;

/**
 *<p>标题: ItemService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月7日 下午4:56:37
 *@版本 
 */
public interface ItemService {

	TbItem geTbItemId(long itemId);
	EUDataGridResult getItemList(int page, int rows);
	ShopResult createItem(TbItem item, String desc, String itemParam) throws Exception;
	
	
}
