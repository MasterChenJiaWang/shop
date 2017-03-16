/**
 * 
 */
package com.chen.rest.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *<p>标题: testSpringJedisSingle </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public class testSpringJedisSingle {

	@Test
	public void testSpringJedisSpring(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
		JedisPool pool = (JedisPool)applicationContext.getBean("redisClient");
		
		Jedis resource = pool.getResource();
		String string = resource.get("key1");
		System.out.println(string);
		resource.close();
		
		pool.close();
	}
}
