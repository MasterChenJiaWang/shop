/**
 * 
 */
package com.chen.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chen.common.utils.JsonUtils;
import com.chen.service.PictureService;

/**
 *<p>标题: PictureController </p>
 *<p>描述： </p>
 *<p>company:</p>
 */
@Controller
public class PictureController {

	@Resource
	private PictureService pictureService;
	
	private static final Logger logger = Logger.getLogger(ItemCatController.class);
	
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile){
		logger.info("开始上传图片。。。");
		Map<Object, Object> result = pictureService.uploadPicture(uploadFile);
		System.out.println("结果；"+result.get("message"));
		//为了保证功能的兼容性，需要把Result转换成json格式的字符串。
		String json = JsonUtils.objectToJson(result);
		logger.info("上传完成。。");
		return json;
	}
}
