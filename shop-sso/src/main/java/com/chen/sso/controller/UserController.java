/**
 * 
 */
package com.chen.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.ExceptionUtil;
import com.chen.pojo.TbUser;
import com.chen.sso.service.UserService;

/**
 *<p>标题: UserController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月14日 下午5:21:35
 *@版本 
 */
@Controller
public class UserController {

	private UserService userService;
	
	public Object checkData(@PathVariable String param, 
			@PathVariable Integer type, String callback){
		
		ShopResult shopResult=null;
		if(StringUtils.isBlank(param)){
			shopResult = ShopResult.build(400, "校验内容不能为空");
		}
		if(type==null){
			shopResult=ShopResult.build(400,"校验内容类型不能为空");
		}
		if(type!=1 && type!=2 && type!=3){
			shopResult=shopResult.build(400, "校验内容类型错误");
		}
		if(null!=shopResult){
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(shopResult);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return shopResult; 
			}

		}
		
		try {
			shopResult=userService.checkData(param, type);
		} catch (Exception e) {
			shopResult=ShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(shopResult);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return shopResult; 
		}

	}
	
	/**
	 *创建用户 
	 * 
	 *@时间 2017年3月14日 下午6:03:02
	 */
	@RequestMapping("/register")
	public ShopResult createUser(TbUser user) {
		
		try {
			ShopResult result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			return  ShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	
	
	
	
}
