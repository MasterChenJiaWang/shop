/**
 * 
 */
package com.chen.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.ExceptionUtil;
import com.chen.search.mapper.ItemMapper;
import com.chen.search.pojo.Item;
import com.chen.search.service.ItemService;

/**
 *<p>标题: ItemServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private   ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	/* 
	 *
	 */
	@Override
	public ShopResult importAllItems() {
		
		try {
			List<Item> itemList = itemMapper.getItemList();
			
			for(Item item:itemList){
			
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				//写入索引库
				solrServer.add(document);
				
			}
			//提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return ShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return ShopResult.ok();
	}
	
	
	
	

}
