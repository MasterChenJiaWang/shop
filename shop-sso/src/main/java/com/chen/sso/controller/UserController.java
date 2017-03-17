/**
 * 
 */
package com.chen.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, 
			@PathVariable Integer type, String callback){
		
		ShopResult shopResult=null;
		//参数有效性校验
		if(StringUtils.isBlank(param)){
			shopResult = ShopResult.build(400, "校验内容不能为空");
		}
		if(type==null){
			shopResult=ShopResult.build(400,"校验内容类型不能为空");
		}
		if(type!=1 && type!=2 && type!=3){
			shopResult=shopResult.build(400, "校验内容类型错误");
		}
		
		//校验出错
		if(null!=shopResult){
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(shopResult);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return shopResult; 
			}

		}
		//调用服务
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
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public ShopResult createUser(TbUser user) {
		
		try {
			ShopResult result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			return  ShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

			//用户登录
		@RequestMapping(value="/login", method=RequestMethod.POST)
		@ResponseBody
		public ShopResult userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response) {
			try {
				
				ShopResult result = userService.userLogin(username, password, request, response);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return ShopResult.build(500, ExceptionUtil.getStackTrace(e));
			}
		}

		@RequestMapping("/token/{token}")
		@ResponseBody
		public Object getUserByToken(@PathVariable String token, String callback) {
			ShopResult result = null;
			try {
				result = userService.getUserByToken(token);
			} catch (Exception e) {
				e.printStackTrace();
				result = ShopResult.build(500, ExceptionUtil.getStackTrace(e));
			}
			
			//判断是否为jsonp调用
			if (StringUtils.isBlank(callback)) {
				return result;
			} else {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			
		}
	
		
		//退出登录
		@RequestMapping("/logout/{token}")
		@ResponseBody
		public Object userLogout(@PathVariable String token, String callback) {
			ShopResult result = null;
			try {
				result = userService.userLogout(token);
			} catch (Exception e) {
				e.printStackTrace();
				result = ShopResult.build(500, ExceptionUtil.getStackTrace(e));
			}
			

			if (StringUtils.isBlank(callback)) {
				return result;
			} else {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
						result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
		}
	
	
}
