/**
 * 
 */
package com.chen.portal.service;

import com.chen.pojo.TbUser;

/**
 *<p>标题: UserService </p>
 *<p>描述：用户管理Service接口 </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午3:49:48
 *@版本 
 */
public interface UserService {

	TbUser getUserByToken(String token);
}
