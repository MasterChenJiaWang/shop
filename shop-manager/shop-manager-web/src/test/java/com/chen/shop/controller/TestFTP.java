/**
 * 
 */
package com.chen.shop.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 *<p>标题: TestFTP </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月10日 上午7:30:52
 *@版本 
 */
public class TestFTP {

	@Test
	public  void testClient( ) throws Exception {
		
		
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.235.128", 21);
		
		ftpClient.login("ftpuser", "ftpuser");
		
		FileInputStream inputStream=new FileInputStream(new File("D:\\图片\\ps素材\\q版\\2.jpg"));
	
		ftpClient.changeWorkingDirectory("/home/ftpuser/images");
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		boolean storeFile = ftpClient.storeFile("123567.jpg", inputStream);
		System.out.println(storeFile);
		inputStream.close();
		ftpClient.logout();
	}
	
	
//	public 
}
