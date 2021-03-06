package io.github.sruby.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StringUtil {

	public static String trim(String value){
		return value == null ? "" : value.trim();
	}
	
	public static boolean isEmpty(String value){
		return "".equals(trim(value));
	}
	
	public static boolean isNotEmpty(String value){
		return !isEmpty(value);
	}
	
	/**
	 * 将字符串用分隔符断裂成字符串列表
	 * @param value 原字符串
	 * @param separator 分隔字符
	 * @return 结果列表
	 */
	public static List<String> split2List(String value, String separator) {
		List<String> ls = new ArrayList<String>();
		int i=0,j=0;
		while ((i = value.indexOf(separator, i)) != -1) {
			ls.add(value.substring(j, i));
			++i;
			j=i;
		}
		ls.add(value.substring(j));
		return ls;
	}
	
	/**
	 * 将字符串用分隔符断裂成字符串数组
	 * 在不需要使用正则表达式时，用来代替String.split方法
	 * 效率在String.split的3-4倍左右
	 * 备注：String.split方法效率也还可以，如果不是在循环中使用，也可以忽略此方法
	 * @param value 原字符串
	 * @param separator 分隔字符
	 * @return 结果数组
	 */
	public static String[] split(String value, String separator) {
		List<String> ls = split2List(value, separator);
		return ls.toArray(new String[ls.size()]);
	}
	
	/**
	 * 将数组用分隔符连接成新字符串(split的逆方法)
	 * @param strs 字符串数组
	 * @param sep 分隔符
	 * @return 结果字符串
	 */
	public static String join(String[] strs, String sep) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			res.append(strs[i]+sep);
		}
		return res.substring(0, res.length()-sep.length());
	}
	
	/** 
	 * 获得一个UUID 
	 * @return String UUID 
	 */ 
	public static String getUUID() {
		String str = UUID.randomUUID().toString();
		return join(split(str, "-"),"");
	}
	
}
