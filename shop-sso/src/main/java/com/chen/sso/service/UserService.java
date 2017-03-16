/**
 * 
 */
package com.chen.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.common.pojo.ShopResult;
import com.chen.pojo.TbUser;

/**
 *<p>标题: UserService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月14日 下午5:07:58
 *@版本 
 */
public interface UserService {

	ShopResult checkData(String content, Integer type);
	ShopResult createUser(TbUser user);
	ShopResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	ShopResult getUserByToken(String token);
}
