/** 
 * <pre>项目名称:c_teacher 
 * 文件名称:Md5Util.java 
 * 包名:com.jk.wz.util 
 * 创建日期:2018年9月22日下午6:07:15 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */
package com.jk.util;

import java.security.MessageDigest;

/**
 * <pre>
 * 项目名称：c_teacher    
 * 类名称：Md5Util    
 * 类描述：    
 * 创建人：wzstart    
 * 创建时间：2018年9月22日 下午6:07:15    
 * 修改人：wzstart  
 * 修改时间：2018年9月22日 下午6:07:15    
 * 修改备注：       
 * &#64;version
 * </pre>
 */
public class Md5Util {

	public final static String calc(String ss) {// MD5加密算法
		String s = ss == null ? "" : ss;// 如果为空，则返回""
		char hexDigists[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };// 字典

		try {
			byte[] strTemp = s.getBytes();// 获取二进制
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);// 执行加密
			byte[] md = mdTemp.digest();// 加密结果
			int j = md.length;// 结果长度
			char str[] = new char[j * 2];// 字符数组
			int k = 0;
			for (int i = 0; i < j; i++) { // 将二进制加密结果转化为字符
				byte byte0 = md[i];
				str[k++] = hexDigists[byte0 >>> 4 & 0xf];
				str[k++] = hexDigists[byte0 & 0xf];

			}
			return new String(str);// 输出加密后的字符
		} catch (Exception e) {
			return null;
		}

	}

}
