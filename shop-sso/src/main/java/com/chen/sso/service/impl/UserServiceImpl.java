/**
 * 
 */
package com.chen.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.CookieUtils;
import com.chen.common.utils.JsonUtils;
import com.chen.mapper.TbUserMapper;
import com.chen.pojo.TbUser;
import com.chen.pojo.TbUserExample;
import com.chen.pojo.TbUserExample.Criteria;
import com.chen.sso.dao.JedisClient;
import com.chen.sso.service.UserService;

/**
 *<p>标题: UserServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月14日 下午5:08:11
 *@版本 
 */
@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	/* 
	 *
	 *2017年3月14日下午5:10:04
	 */
	@Override
	public ShopResult checkData(String content, Integer type) {
		
		TbUserExample userExample = new TbUserExample();
		Criteria criteria = userExample.createCriteria();
		//对数据进行校验：1、2、3分别代表username、phone、email
		//用户名校验
		if(1==type){
			criteria.andUsernameEqualTo(content);
		}
		//电话校验
		else if(2==type){
			criteria.andUsernameEqualTo(content);
		}
		//email校验
		else{
			criteria.andUsernameEqualTo(content);
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(userExample);
		
		if(list==null || list.size()==0){
			return ShopResult.ok(true);
		}
		
		return ShopResult.ok(false);
	}
	

	/* 
	 *注册
	 *2017年3月14日下午5:10:04
	 */
	@Override
	public ShopResult createUser(TbUser user) {
		
		user.setUpdated(new Date());
		user.setCreated(new Date());
		//md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return ShopResult.ok();

	}

	/* 
	 *用户登录
	 *2017年3月14日下午5:10:04
	 */
	@Override
	public ShopResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		
		TbUserExample userExample = new TbUserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(userExample);
		
		if(null ==list && list.size()==0){
			return ShopResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		//比对密码
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
			return ShopResult.build(400, "用户名或密码错误");
		}
		//生成token
		String token = UUID.randomUUID().toString();
		//保存用户之前，把用户对象中的密码清空。
		user.setPassword(null);
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		//设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		
		//添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		
		//返回token
		return ShopResult.ok(token);
	}

	/* 
	 *根据token 查询用户
	 *2017年3月14日下午5:10:04
	 */
	@Override
	public ShopResult getUserByToken(String token) {
		//根据token从redis中查询用户信息
				String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
				//判断是否为空
				if (StringUtils.isBlank(json)) {
					return ShopResult.build(400, "此session已经过期，请重新登录");
				}
				//更新过期时间
				jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
				//返回用户信息
				return ShopResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}
	
	
		// 退出登录
		@Override
		public ShopResult userLogout(String token) {
			// 根据token从redis删除用户信息
			jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
			// 返回用户信息
			return ShopResult.ok();
		}
	
	
}
