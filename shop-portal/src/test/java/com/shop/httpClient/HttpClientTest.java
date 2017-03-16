/**
 * 
 */
package com.shop.httpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.chen.common.pojo.ShopResult;
import com.chen.common.utils.HttpClientUtil;
import com.chen.portal.pojo.SearchResult;

/**
 *<p>标题: HttpClientTest </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public class HttpClientTest {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	
	@Test
	public void doGet() throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://www.sogou.com");
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statusCode="+statusCode);
		
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		//关闭httpClient
		response.close();
		httpClient.close();
	}
	
	
	
	@Test
	public void doGetWithParam() throws Exception{
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://localhost:8083/search/query");
		uriBuilder.addParameter("q", "奶粉");
		HttpGet get = new HttpGet(uriBuilder.build());
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statusCode="+statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}

	
	/**
	 * 执行post请求 不带参数
	 * 
	 */
	@Test
	public void doPost() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
	
		
		//创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}

	/**
	 *  执行post请求 带参数
	 * 
	 */
	@Test
	public void doPostWithParam() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		//创建一个Entity。模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "zhangsan"));
		kvList.add(new BasicNameValuePair("password", "123"));
		
		//包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
		//设置请求的内容
		post.setEntity(entity);
		
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}

	@Test
	public void testdoGet(){
//		String url="http://localhost:8083/search/query";
//		SEARCH_BASE_URL="http://localhost:8083/search/query";
		HashMap<String, String> map = new HashMap<>();
		map.put("q", "奶粉");
		map.put("page", 1+"");
		
		System.out.println(SEARCH_BASE_URL);
		
////		String json = HttpClientUtil.doGet(url, map);
//		String json = HttpClientUtil.doGet(SEARCH_BASE_URL, map);
//		ShopResult shopResult = ShopResult.formatToPojo(json, SearchResult.class);
//		System.out.println("状态码="+shopResult.getStatus());
//		System.out.println(json);
	}
}
