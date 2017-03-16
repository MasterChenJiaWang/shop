/**
 * 
 */
package com.chen.rest.service;

import com.chen.common.pojo.ShopResult;

/**
 *<p>标题: ItemService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月14日 上午10:35:34
 *@版本 
 */
public interface ItemService {

	ShopResult getItemBaseInfo(long itemId);
	ShopResult getItemDesc(long itemId);
	ShopResult getItemParam(long itemId);
}
