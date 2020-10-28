package io.github.sruby.concurrent.collection;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class FutureTest
{
	public static void main(String[] args)
	{
		Future<String> future = new Future<String>()
		{
			
			@Override
			public boolean isDone()
			{
				return false;
			}
			
			@Override
			public boolean isCancelled()
			{
				return false;
			}
			
			@Override
			public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
			{
				return null;
			}
			
			@Override
			public String get() throws InterruptedException, ExecutionException
			{
				return null;
			}
			
			@Override
			public boolean cancel(boolean mayInterruptIfRunning)
			{
				return false;
			}
		};
	}
}
