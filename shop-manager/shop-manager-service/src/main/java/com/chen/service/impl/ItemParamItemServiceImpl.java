/**
 * 
 */
package com.chen.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.common.utils.JsonUtils;
import com.chen.mapper.TbItemParamItemMapper;
import com.chen.pojo.TbItemParamItem;
import com.chen.pojo.TbItemParamItemExample;
import com.chen.pojo.TbItemParamItemExample.Criteria;
import com.chen.service.ItemParamItemService;

/**
 *<p>标题: ItemParamItemServiceImpl </p>
 *<p>描述：  展示商品规格参数 </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class ItemParamItemServiceImpl  implements ItemParamItemService{

	@Resource
	private TbItemParamItemMapper itemParamItemMapper;
	
	
	/* 
	 *通过商品id 获得商品规格参数，并生成html显示在表现层
	 */
	@Override
	public String getItemParamByItemId(Long itemId) {
		
		//根据商品id查询规格参数
				TbItemParamItemExample example = new TbItemParamItemExample();
				Criteria criteria = example.createCriteria();
				criteria.andItemIdEqualTo(itemId);
				//执行查询
				List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
				if (list == null || list.size() == 0) {
					return "";
				}
				//取规格参数信息
				TbItemParamItem itemParamItem = list.get(0);
				String paramData = itemParamItem.getParamData();
				
				// 把规格参数json数据转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				StringBuffer sb = new StringBuffer();
				//生成html
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for(Map m1:jsonList) {
					sb.append("        <tr>\n");
					sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
					sb.append("        </tr>\n");
					List<Map> list2 = (List<Map>) m1.get("params");
					for(Map m2:list2) {
						sb.append("        <tr>\n");
						sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
						sb.append("            <td>"+m2.get("v")+"</td>\n");
						sb.append("        </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				return sb.toString();
		
	}

}
