/**
 * 
 */
package com.chen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.mapper.TbItemCatMapper;
import com.chen.pojo.TbItemCat;
import com.chen.pojo.TbItemCatExample;
import com.chen.pojo.TbItemCatExample.Criteria;
import com.chen.service.ItemCatService;

/**
 *<p>标题: ItemCatServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月8日 下午9:36:52
 *@版本 
 */
@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {

	@Resource
	private  TbItemCatMapper itemCatMapper;

	/* 
	 *
	 */
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception {
		
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
	    Criteria criteria= example.createCriteria();
		//根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;

		
	}
	
	
}
