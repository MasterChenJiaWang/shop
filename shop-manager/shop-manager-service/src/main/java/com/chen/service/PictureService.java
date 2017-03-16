/**
 * 
 */
package com.chen.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 *<p>标题: PictureService </p>
 *<p>描述： </p>
 *<p>company:</p>
 *@版本 
 */
public interface PictureService {

	Map<Object ,Object>  uploadPicture(MultipartFile uploadFile);
}
