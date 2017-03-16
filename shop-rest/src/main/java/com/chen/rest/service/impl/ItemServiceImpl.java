/**
 * 
 */
package com.chen.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.JsonUtils;
import com.chen.mapper.TbItemDescMapper;
import com.chen.mapper.TbItemMapper;
import com.chen.mapper.TbItemParamItemMapper;
import com.chen.pojo.TbItem;
import com.chen.pojo.TbItemDesc;
import com.chen.pojo.TbItemParamItem;
import com.chen.pojo.TbItemParamItemExample;
import com.chen.pojo.TbItemParamItemExample.Criteria;
import com.chen.rest.dao.JedisClient;
import com.chen.rest.service.ItemService;

/**
 *<p>标题: ItemServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月14日 上午10:35:50
 *@版本 
 */
@Service
public class ItemServiceImpl  implements ItemService{
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Autowired
	private JedisClient jedisClient;
	
	
	
	
	
	/* 
	 *根据商品id查询商品信息
	 *2017年3月14日上午10:37:26
	 */
	@Override
	public ShopResult getItemBaseInfo(long itemId) {
		
		try {
			//添加缓存逻辑
			//从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			//判断是否有值
			if (!StringUtils.isBlank(json)) {
				//把json转换成java对象
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return ShopResult.ok(item);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据商品id查询商品信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		//使用TaotaoResult包装一下
		try {
			//把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//使用TaotaoResult包装一下
		return ShopResult.ok(item);
	}

	/* 
	 *
	 *2017年3月14日上午10:37:26
	 */
	@Override
	public ShopResult getItemDesc(long itemId) {
		
		
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":desc");
			
			if(!StringUtils.isBlank(json)){
				//把json转换成java对象
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return ShopResult.ok(itemDesc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		
		try {
			//把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ShopResult.ok(itemDesc);
	}

	/* 
	 *得到商品规格
	 *2017年3月14日上午10:37:26
	 */
	@Override
	public ShopResult getItemParam(long itemId) {
		//添加缓存
				try {
					//添加缓存逻辑
					//从缓存中取商品信息，商品id对应的信息
					String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
					//判断是否有值
					if (!StringUtils.isBlank(json)) {
						//把json转换成java对象
						TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
						return ShopResult.ok(paramItem);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//根据商品id查询规格参数
				//设置查询条件
				TbItemParamItemExample example = new TbItemParamItemExample();
				Criteria criteria = example.createCriteria();
				criteria.andItemIdEqualTo(itemId);
				//执行查询
				List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
				if (list != null && list.size()>0) {
					TbItemParamItem paramItem = list.get(0);
					try {
						//把商品信息写入缓存
						jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(paramItem));
						//设置key的有效期
						jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ShopResult.ok(paramItem);
				}
				return ShopResult.build(400, "无此商品规格");
	}

}
