/**
 * 
 */
package com.chen.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chen.common.utils.JsonUtils;
import com.chen.mapper.TbContentMapper;
import com.chen.pojo.TbContent;
import com.chen.pojo.TbContentExample;
import com.chen.pojo.TbContentExample.Criteria;
import com.chen.rest.dao.JedisClient;
import com.chen.rest.service.ContentService;

/**
 *<p>标题: ContentServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
public class ContentServiceImpl  implements ContentService{

	
	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	/* 
	 *
	 */
	@Override
	public List<TbContent> getContentList(long contentCid) {
		
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			//缓存中有数据  直接返回 list数据
			if(!StringUtils.isBlank(result)){
				//把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		
		List<TbContent> list = contentMapper.selectByExample(example);
		
		try {
			//把list转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid+"", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
