/**
 * 
 */
package com.chen.sso.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.chen.common.pojo.ShopResult;
import com.chen.mapper.TbUserMapper;
import com.chen.pojo.TbUser;
import com.chen.pojo.TbUserExample;
import com.chen.pojo.TbUserExample.Criteria;
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
		}else if(2==type){
			criteria.andUsernameEqualTo(content);
		}else{
			criteria.andUsernameEqualTo(content);
		}
		List<TbUser> list = userMapper.selectByExample(userExample);
		
		if(list==null || list.size()==0){
			return ShopResult.ok(true);
		}
		
		return ShopResult.ok(false);
	}
	

	/* 
	 *
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
	 *
	 *2017年3月14日下午5:10:04
	 */
	@Override
	public ShopResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		return null;
	}

	/* 
	 *
	 *2017年3月14日下午5:10:04
	 */
	@Override
	public ShopResult getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
