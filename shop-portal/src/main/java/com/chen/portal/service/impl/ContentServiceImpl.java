/**
 * 
 */
package com.chen.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.HttpClientUtil;
import com.chen.common.utils.JsonUtils;
import com.chen.pojo.TbContent;
import com.chen.portal.service.ContentService;

/**
 *<p>标题: ContentServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
public class ContentServiceImpl  implements ContentService{

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	/* 
	 *根据内容分类id查询分类的内容列表.
	 *需要使用httpclient调用shop-rest的服务。得到一个json字符串。
	 *需要把字符串转换成java对象shopResult对象。
	 *从ShopResult对象中取data属性，得到内容列表。
	 *把内容列表转换成jsp页面要求的json格式。
	 *返回一个json字符串。
	 */
	@Override
	public String getContentList() {
		
		//调用服务层的服务
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		//把字符串转换成ShopResult
		
		try {
			ShopResult shopResult = ShopResult.formatToList(result, TbContent.class);
			
			//取内容列表
			List<TbContent> list = (List<TbContent>) shopResult.getData();
			List<Map> resultList = new ArrayList<>();
			//创建一个jsp页码要求的pojo列表
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
	
}
