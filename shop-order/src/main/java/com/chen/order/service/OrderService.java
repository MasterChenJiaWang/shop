/**
 * 
 */
package com.chen.order.service;

import java.util.List;

import com.chen.common.pojo.ShopResult;
import com.chen.pojo.TbOrder;
import com.chen.pojo.TbOrderItem;
import com.chen.pojo.TbOrderShipping;

/**
 *<p>标题: OrderService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午4:41:34
 *@版本 
 */
public interface OrderService {

	ShopResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
