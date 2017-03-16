/**
 * 
 */
package com.chen.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.ExceptionUtil;
import com.chen.rest.dao.JedisClient;
import com.chen.rest.service.RedisService;

/**
 *<p>标题: RedisServiceImpl </p>
 *<p>描述： 缓存同步</p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
//@Transactional
public class RedisServiceImpl  implements RedisService{

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	/* 
	 * 同步缓存中的内容信息
	 *接收内容分类id，调用dao
	 *删除 redis中对应的hash中key为分类id的项。
	 */
	@Override
	public ShopResult syncContent(long contentCid) {
		
	 try {
		jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
	} catch (Exception e) {
		e.printStackTrace();
		return ShopResult.build(500, ExceptionUtil.getStackTrace(e));
	}
	 return ShopResult.ok();
	}

}
