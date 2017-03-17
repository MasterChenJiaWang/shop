/**
 * 
 */
package com.chen.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.HttpClientUtil;
import com.chen.pojo.TbUser;
import com.chen.portal.service.UserService;

/**
 *<p>标题: UserServiceImpl </p>
 *<p>描述： 用户管理Service</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午3:50:53
 *@版本 
 */
@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_DOMAIN_BASE_USRL}")
	public String SSO_DOMAIN_BASE_USRL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	
	/* 
	 * 根据token换取用户信息，需要调用sso系统的服务
	 *获得用户信息
	 *2017年3月17日下午3:51:12
	 */
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//调用sso系统的服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			//把json转换成TaotaoREsult
			ShopResult result = ShopResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
