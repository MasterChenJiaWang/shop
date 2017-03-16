/**
 * 
 */
package com.chen.common.pojo;

/**
 *<p>标题: EUTreeNode </p>
 *<p>描述： easyUI树形控件节点格式</p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public class EUTreeNode {

	private long id;
	private String text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
