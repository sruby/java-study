package io.github.sruby.lambda;

import com.google.common.base.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class LambdaTest
{
	
	public static void main(String[] args)
	{
		
	}

	@Test
	public void test() {
		Supplier<Integer> integerCopier = () -> 5;
		log.info("test:{}",integerCopier.get());
	}

	// 定义一个函数式接口
	interface MyFunction {
		int getValue();
	}

//	public class LambdaExample {
//		public static void main(String[] args) {
//			// 将Lambda表达式赋值给函数式接口变量
//			MyFunction myFunction = () -> 5;
//
//			// 调用函数式接口的方法，输出5
//			System.out.println(myFunction.getValue());
//		}
//	}


	public void runThread()
	{
		new Thread(new Runnable()
		{
			
			public void run()
			{
				System.out.println("Run!");
			}
		}).start();
	}
	
	/**
	 * 无形参
	 * @author liuwf on 2017年1月22日 下午5:09:23
	 */
	@Test
	public void runThreadUseLambda()
	{
		new Thread(() -> {
			System.out.println("run!!!!");
		}).start();
	}
	
	/**
	 * 有行参
	 * @author liuwf on 2017年1月22日 下午5:15:18
	 */
	@Test
	public void testComparator()
	{
		List<String> list = new ArrayList<String>();
		list.add("3");
		list.add("1");
		list.add("2");
		
		Collections.sort(list, new Comparator<String>()
		{
			
			@Override
			public int compare(String o1, String o2)
			{
				return 0;
			}
			
		});
		
		System.out.println(list);
		
		Collections.sort(list, Comparator.naturalOrder());
		System.out.println(list);
	}
	
	/**
	 * 列表迭代
	 * @author liuwf on 2017年1月23日 上午10:45:20
	 */
	@Test
	public void testForeach()
	{
		/**
		 * java8之前
		 */
		List<String> list = Arrays.asList("1","2","5");
		for (String string : list)
		{
			System.out.println(string);
		}
		
		/**
		 * java8
		 */
		list.forEach(string->System.out.println(string));
		
		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		list.forEach(System.out::println);
	}
}
