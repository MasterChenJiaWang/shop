/**
 * 
 */
package com.chen.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.mapper.TbItemCatMapper;
import com.chen.pojo.TbItemCat;
import com.chen.pojo.TbItemCatExample;
import com.chen.pojo.TbItemCatExample.Criteria;
import com.chen.rest.pojo.CatNode;
import com.chen.rest.pojo.CatResult;
import com.chen.rest.service.ItemCatService;

/**
 *<p>标题: ItemCatServiceImpl </p>
 *<p>描述： 商品分类服务</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月11日 下午2:42:17
 *@版本 
 */
@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {

	@Resource
	private TbItemCatMapper itemCatMapper;
	
	
	/* 
	 *
	 */
	@Override
	public CatResult getItemCatList() {
		
		
		CatResult catResult = new CatResult();
		
		catResult.setData(getCatList(0));
		
		return catResult;
	}
	
	
	/**
	 * 查询分类列表
	 */
	private List<?> getCatList(long parentId){
		//创建查询条件
		TbItemCatExample catExample = new TbItemCatExample();
		Criteria criteria = catExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(catExample);
		
		List<Object> resultList = new ArrayList<>();
		int count=0;
		
		for(TbItemCat tbItemCat:list){
			
			//判断是否为父节点
			if(tbItemCat.getIsParent()){
				CatNode catNode = new CatNode();
				if(parentId==0){
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				}else{
					catNode.setName(tbItemCat.getName());
				}
				
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count++;
				
				//第一层只取14条记录
				if(parentId==0 &&count>=14){
					break;
				}
				
			}
			//不是父节点 即 是 叶子节点
			else{
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

}
