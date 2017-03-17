/**
 * 
 */
package com.chen.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.common.pojo.ShopResult;
import com.chen.portal.pojo.CartItem;

/**
 *<p>标题: CartService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午4:08:19
 *@版本 
 */
public interface CartService {

	ShopResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	ShopResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
