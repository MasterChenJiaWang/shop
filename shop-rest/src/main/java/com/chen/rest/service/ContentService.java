/**
 * 
 */
package com.chen.rest.service;

import java.util.List;

import com.chen.pojo.TbContent;

/**
 *<p>标题: ContentService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public interface ContentService {

	List<TbContent> getContentList(long contentCid);
}
