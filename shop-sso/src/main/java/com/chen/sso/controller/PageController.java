/**
 * 
 */
package com.chen.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *<p>标题: PageController </p>
 *<p>描述：页面跳转Controller </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午2:44:18
 *@版本 
 */
@Controller
@RequestMapping("/page")
public class PageController {

	
	/**
	 * 跳转注册
	 * 
	 *@时间 2017年3月17日 下午2:55:25
	 */
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}

	/**
	 * 跳转登录
	 * 
	 *@时间 2017年3月17日 下午2:55:07
	 */
	@RequestMapping("/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	
}
