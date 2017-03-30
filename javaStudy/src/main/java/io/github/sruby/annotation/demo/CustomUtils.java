package io.github.sruby.annotation.demo;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注解解析
 * @author liuwf on 2017年3月30日 下午4:57:21
 */
public class CustomUtils
{
	private static Logger logger = LoggerFactory.getLogger(CustomUtils.class);
	
	public static void getInfo(Class<?> clazz)
	{
		String name = "";
		String gender = "";
		String profile = "";
		Field fields[] = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			if (field.isAnnotationPresent(Name.class))
			{
				Name arg0 = field.getAnnotation(Name.class);
				name = name + arg0.value();
				logger.info("name:{}",name);
			}
			if (field.isAnnotationPresent(Gender.class))
			{
				Gender arg0 = field.getAnnotation(Gender.class);
				gender = gender + arg0.gender().toString();
				logger.info("gender:{}",gender);
			}
			if (field.isAnnotationPresent(Profile.class))
			{
				Profile arg0 = field.getAnnotation(Profile.class);
				profile = "[id=" + arg0.id() + ",height=" + arg0.height() + ",nativePlace=" + arg0.nativePlace() + "]";
				logger.info("profile:{}",profile);
			}
		}
	}
	
}