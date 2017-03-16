/**
 * 
 */
package com.chen.rest.service;

import com.chen.common.pojo.ShopResult;

/**
 *<p>标题: RedisService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface RedisService {

	ShopResult syncContent(long contentCid);
}
