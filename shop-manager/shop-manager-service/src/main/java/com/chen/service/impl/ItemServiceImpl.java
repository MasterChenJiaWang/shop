/**
 * 
 */
package com.chen.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.common.pojo.EUDataGridResult;
import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.IDUtils;
import com.chen.mapper.TbItemDescMapper;
import com.chen.mapper.TbItemMapper;
import com.chen.mapper.TbItemParamItemMapper;
import com.chen.pojo.TbItem;
import com.chen.pojo.TbItemDesc;
import com.chen.pojo.TbItemExample;
import com.chen.pojo.TbItemExample.Criteria;
import com.chen.pojo.TbItemParamItem;
import com.chen.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *<p>标题: ItemServiceImpl </p>
 *<p>描述： 商品管理</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月7日 下午4:57:10
 *@版本 
 */

@Service
@Transactional
public  class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;

	
	@Resource
	private TbItemDescMapper itemDescMapper;
	
	@Resource
	private TbItemParamItemMapper itemParamItemMapper;
	
	/* 
	 *
	 *2017年3月8日下午7:13:07
	 */
	@Override
	public TbItem geTbItemId(long itemId) {
	//	TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		TbItemExample example = new TbItemExample();
		//创建查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	
	/* 
	 *商品列表查询
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//查询商品列表
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/* 
	 *添加商品信息
	 *2017年3月10日下午2:42:55
	 * desc  商品描述
	 *itemParam 商品规格参数
	 */
	@Override
	public ShopResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		
		Date date = new Date();
		long itemId = IDUtils.genItemId();
		//添加商品信息
		item.setId(itemId);
		
		item.setStatus((byte)1);
		item.setCreated(date);
		
		item.setUpdated(date);
		
		itemMapper.insert(item);
		
		//添加商品描述
		//创建TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		
		//获得一个商品id
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//插入数据
		itemDescMapper.insert(itemDesc);
		
		//上面是 商品的基本信息 对应 item表
		
		//下面是商品的描述信息和详细规格参数，对应表是ItemDesc 和 ItemParamItem
		//添加商品描述信息
		ShopResult result = addItemDesc(itemId, desc);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		//添加规格参数
		result = insertItemParamItem(itemId, itemParam);
				if (result.getStatus() != 200) {
					throw new Exception();
				}
		
		return ShopResult.ok();
	}

	
	
	/**
	 * 商品描述
	 * 
	 */
	public ShopResult addItemDesc(Long itemId,String desc){
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(new Date());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		 return ShopResult.ok();
	}
	
	
	/**
	 * 添加规格参数
	 * 
	 */
	private  ShopResult insertItemParamItem(Long itemId, String itemParam){
		
		//创建一个pojo
				TbItemParamItem itemParamItem = new TbItemParamItem();
				itemParamItem.setItemId(itemId);
				itemParamItem.setParamData(itemParam);
				itemParamItem.setCreated(new Date());
				itemParamItem.setUpdated(new Date());
				//向表中插入数据
				itemParamItemMapper.insert(itemParamItem);

		
		return ShopResult.ok();
	}
	
	
}
