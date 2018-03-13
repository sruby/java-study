package io.github.sruby.collection.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class HashMapTest
{
	@Test
	public void testPut()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		String a = map.get("1");
	}
	
	@Test
	public void testHashCode()
	{
		/**
		 * hash碰撞
		 */
		String str1 = "http://pic.bandaonews.com/PicView.aspx?id=37219";
		String str2 = "http://tech.163.com/05/0829/09/1SAIIRG8000915BD.html";
		int hashCode = str1.hashCode();
		int hashCode2 = str2.hashCode();
		
		if(hashCode == hashCode2)
		{
			System.out.println("equals");
		}
		//equals比较相等的依据：str1==str2 || (str1.length()==)
		boolean flag = str1.equals(str2);
		System.out.println(hashCode + "----" + hashCode2 + "---" + flag );
	}
	
	
	
}
