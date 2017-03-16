/**
 * 
 */
package com.chen.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.common.pojo.ShopResult;
import com.chen.mapper.TbItemParamMapper;
import com.chen.pojo.TbItemParam;
import com.chen.pojo.TbItemParamExample;
import com.chen.pojo.TbItemParamExample.Criteria;
import com.chen.service.ItemParamService;

/**
 *<p>标题: ItemParamServiceImpl </p>
 *<p>描述： 商品规格参数模板管理</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月11日 上午8:30:19
 *@版本 
 */
@Service
@Transactional
public class ItemParamServiceImpl  implements ItemParamService{

	@Resource
	private TbItemParamMapper itemParamMapper;
	
	
	/* 
	 *判断商品模板是否已经创建
	 *通过商品Id 获得对应的商品模板
	 */
	@Override
	public ShopResult getItemParamByCid(long cid) {
		
		TbItemParamExample example = new TbItemParamExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		//包含大文本列
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null && list.size()>0){
			return ShopResult.ok(list.get(0));
		}
		return ShopResult.ok();
		
	}

	/* 
	 *添加商品模板
	 */
	@Override
	public ShopResult insertItemParam(TbItemParam itemParam) {

		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		
		return ShopResult.ok();
		
	}

}
