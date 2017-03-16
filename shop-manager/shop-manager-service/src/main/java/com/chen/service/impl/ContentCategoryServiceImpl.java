/**
 * 
 */
package com.chen.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.NodeList;

import com.chen.common.pojo.EUTreeNode;
import com.chen.common.pojo.ShopResult;
import com.chen.mapper.TbContentCategoryMapper;
import com.chen.pojo.TbContentCategory;
import com.chen.pojo.TbContentCategoryExample;
import com.chen.pojo.TbContentCategoryExample.Criteria;
import com.chen.service.ContentCategoryService;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 *<p>标题: ContentCategoryServiceImpl </p>
 *<p>描述：内容分类管理 </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Resource
	private TbContentCategoryMapper contentCategoryMapper;
	/* 
	 *获取内容分类
	 */
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据parentid查询节点列表
		TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
		Criteria criteria = contentCategoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(contentCategoryExample);
		List<EUTreeNode> resultList = new ArrayList<>();
		
		for(TbContentCategory contentCategory:list){
			
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
		
	}
	/* 
	 *添加内容分类
	 *parentId:父节点id
	 *name：当前节点的名称
	 */
	@Override
	public ShopResult insertContentCat(long parentId, String name) {
		
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		
		contentCategory.setName(name);
		//该类目是否为父类目
		contentCategory.setIsParent(false);
		//'状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		//父类目ID=0时，代表的是一级的类目'
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		contentCategoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
				if(!parentCat.getIsParent()) {
					parentCat.setIsParent(true);
					//更新父节点
					contentCategoryMapper.updateByPrimaryKey(parentCat);
				}
				//返回结果
				return ShopResult.ok(contentCategory);
		
	}
	/* 
	 *删除内容分类
	 */
	@Override
	public ShopResult deleteContentCat(long parentId, long id) {
		
		contentCategoryMapper.deleteByPrimaryKey(id);
		TbContentCategoryExample categoryExample = new TbContentCategoryExample();
		Criteria criteria = categoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(categoryExample);
		
		// 父节点
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		// 如果没有子节点，设置为false
		if(list!=null && list.size()>0){
			contentCategory.setIsParent(true);
		}else{
			contentCategory.setIsParent(false);
		}
		return ShopResult.ok();
	}
	/* 
	 *修改
	 */
	@Override
	public ShopResult updateContentCat(String name, long id) {
		
		TbContentCategory contentCategory= contentCategoryMapper.selectByPrimaryKey(id);
		TbContentCategory tbContentCategory = new  TbContentCategory();
		
		tbContentCategory.setIsParent(contentCategory.getIsParent());
		tbContentCategory.setName(name);
		tbContentCategory.setId(id);
		tbContentCategory.setParentId(contentCategory.getParentId());
		tbContentCategory.setSortOrder(contentCategory.getSortOrder());
		tbContentCategory.setStatus(contentCategory.getStatus());
		tbContentCategory.setUpdated(new Date());
		tbContentCategory.setCreated(contentCategory.getCreated());
		
		contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
		return ShopResult.ok();
	}

	
}
