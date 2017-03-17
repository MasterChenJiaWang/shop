/**
 * 
 */
package com.chen.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chen.common.pojo.ShopResult;
import com.chen.portal.pojo.CartItem;
import com.chen.portal.service.CartService;

/**
 *<p>标题: CartController </p>
 *<p>描述： 购物车Controller</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月17日 下午4:19:53
 *@版本 
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	/**
	 * 添加购物车
	 * 
	 *@时间 2017年3月17日 下午4:21:15
	 */
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, 
			@RequestParam(defaultValue="1")Integer num, 
			HttpServletRequest request, HttpServletResponse response) {
		ShopResult result = cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/success.html";
	}
	
	
	/**
	 * 
	 * 
	 *@时间 2017年3月17日 下午4:21:30
	 */
	@RequestMapping("/success")
	public String showSuccess() {
		return "cartSuccess";
	}
	
	/**
	 * 从cookie中把购物车列表取出来
	 * 没有参数，返回购物车中的商品列表。
	 * 
	 *@时间 2017年3月17日 下午4:21:53
	 */
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}
	
	/**
	 * 删除购物车
	 * 
	 *@时间 2017年3月17日 下午4:25:21
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}
}
