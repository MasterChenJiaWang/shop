/**
 * 
 */
package com.chen.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.HttpClientUtil;
import com.chen.common.utils.JsonUtils;
import com.chen.portal.pojo.Order;
import com.chen.portal.service.OrderService;

/**
 *<p>标题: OrderServiceImpl </p>
 *<p>描述：订单处理Service </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午4:58:25
 *@版本 
 */
@Service
public class OrderServiceImpl  implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	

	
	/**
	 * 创建订单
	 * 先要获取用户
	 */
	@Override
	public String createOrder(Order order) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
		
		//调用taotao-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json转换成taotaoResult
		ShopResult taotaoResult = ShopResult.format(json);
		if (taotaoResult.getStatus() == 200) {
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}

}