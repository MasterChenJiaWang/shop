/**
 * 
 */
package com.chen.service;

import com.chen.common.pojo.ShopResult;
import com.chen.pojo.TbItemParam;

/**
 *<p>标题: ItemParamService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface ItemParamService {

	ShopResult getItemParamByCid(long cid);
	ShopResult insertItemParam(TbItemParam itemParam);
}
