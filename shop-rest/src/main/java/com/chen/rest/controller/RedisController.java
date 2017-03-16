/**
 * 
 */
package com.chen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chen.common.pojo.ShopResult;
import com.chen.rest.service.RedisService;

/**
 *<p>标题: RedisController </p>
 *<p>描述：缓存同步Controller </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */

@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping("/content/{contentCid}")
	public ShopResult contentCacheSync(@PathVariable Long contentCid) {
		ShopResult result = redisService.syncContent(contentCid);
		return result;
	}

	
}
