/**
 * 
 */
package com.chen.service;

import java.util.List;

import com.chen.pojo.TbItemCat;

/**
 *<p>标题: ItemCatService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月8日 下午9:36:39
 *@版本 
 */
public interface ItemCatService {

	public List<TbItemCat> getItemCatList(Long parentId)throws Exception;
}
