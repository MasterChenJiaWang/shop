/**
 * 
 */
package com.chen.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.rest.dao.JedisClient;

import redis.clients.jedis.JedisCluster;

/**
 *<p>标题: JedisClientSingle </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Repository
public class JedisClientCluster  implements JedisClient{

	@Autowired
	private JedisCluster jedisCluster;
	
	/* 
	 *
	 */
	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	/* 
	 *
	 */
	@Override
	public String set(String key, String value) {
	
		return jedisCluster.set(key, value);
	}

	/* 
	 *
	 */
	@Override
	public String hget(String hkey, String key) {
		
		return jedisCluster.hget(hkey, key);
	}

	/* 
	 *
	 */
	@Override
	public long hset(String hkey, String key, String value) {
		
		return jedisCluster.hset(hkey, key, value);
	}

	/* 
	 *
	 */
	@Override
	public long incr(String key) {
		
		return jedisCluster.incr(key);
	}

	/* 
	 *设置获取时间
	 */
	@Override
	public long expire(String key, int second) {
		
		
		return jedisCluster.expire(key, second);
	}

	/* 
	 *看 key  是否过期
	 */
	@Override
	public long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	/* 
	 *
	 */
	@Override
	public long del(String key) {
		
		return jedisCluster.del(key);
	}

	/* 
	 *
	 */
	@Override
	public long hdel(String hkey, String key) {
		return jedisCluster.hdel(hkey, key);
	}

}
