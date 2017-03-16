/**
 * 
 */
package com.chen.portal.service;

import com.chen.portal.pojo.ItemInfo;

/**
 *<p>标题: ItemService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface ItemService {

	ItemInfo getItemById(Long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);
}
