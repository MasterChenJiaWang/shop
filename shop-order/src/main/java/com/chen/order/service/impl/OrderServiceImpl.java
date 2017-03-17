/**
 * 
 */
package com.chen.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chen.common.pojo.ShopResult;
import com.chen.mapper.TbOrderItemMapper;
import com.chen.mapper.TbOrderMapper;
import com.chen.mapper.TbOrderShippingMapper;
import com.chen.order.dao.JedisClient;
import com.chen.order.service.OrderService;
import com.chen.pojo.TbOrder;
import com.chen.pojo.TbOrderItem;
import com.chen.pojo.TbOrderShipping;

/**
 *<p>标题: OrderServiceImpl </p>
 *<p>描述：订单管理Service </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午4:42:30
 *@版本 
 */
@Service
public class OrderServiceImpl implements  OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;
	
	/**
	 * 添加订单
	 */
	@Override
	public ShopResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		//向订单表中插入记录
		//获得订单号
		String string = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(string)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		//使用redis的incr命令生成订单号
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		//补全pojo的属性
		order.setOrderId(orderId + "");
		//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		//0：未评价 1：已评价
		order.setBuyerRate(0);
		//向订单表插入数据
		orderMapper.insert(order);
		//插入订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			//补全订单明细
			//取订单明细id 使用redis的incr命令生成订单明细id
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId + "");
			tbOrderItem.setOrderId(orderId + ""); 
			//向订单明细插入记录
			orderItemMapper.insert(tbOrderItem);
		}
		//插入物流表
		//补全物流表的属性
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		
		return ShopResult.ok(orderId);
	}
}
