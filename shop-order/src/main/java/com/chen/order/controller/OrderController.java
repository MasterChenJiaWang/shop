/**
 * 
 */
package com.chen.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.ExceptionUtil;
import com.chen.order.pojo.Order;
import com.chen.order.service.OrderService;

/**
 *<p>标题: OrderController </p>
 *<p>描述：订单Controller</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午4:51:28
 *@版本 
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 创建订单
	 * 
	 *@时间 2017年3月17日 下午4:52:40
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public ShopResult createOrder(@RequestBody Order order) {
		try {
			ShopResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
