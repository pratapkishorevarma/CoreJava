package com.prv.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newSingleThreadExecutor();
		ExecutorService es1 = Executors.newFixedThreadPool(5);
		ExecutorService es2 = Executors.newCachedThreadPool();
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		
		es.execute(() -> {System.out.println("Execute task1");});
		Future submit = es1.submit(() -> {System.out.println("Submit task1");});
		System.out.println(submit.get()); //returned null when successfully executed the task

		Future<String> callableSubmit = es2.submit(() -> {return "return call";});
		System.out.println(callableSubmit.get());
		
		
		es.shutdown();
		es1.shutdown();
		es2.shutdown();
		
		ScheduledFuture<String> scheduledFuture =
			    scheduledExecutorService.schedule(new Callable<String>() {
			        public String call() throws Exception {
			            System.out.println("Executed!");
			            return "Called!";
			        }
			    }, 5, TimeUnit.SECONDS);
		System.out.println(scheduledFuture.get());
		
		scheduledExecutorService.shutdown(); //if not shutdown the class will be running forever
		
		int  corePoolSize  =    5;
		int  maxPoolSize   =   10;
		long keepAliveTime = 5000;

		ExecutorService threadPoolExecutor =
		        new ThreadPoolExecutor(
		                corePoolSize,
		                maxPoolSize,
		                keepAliveTime,
		                TimeUnit.MILLISECONDS,
		                new LinkedBlockingQueue<Runnable>()
		                );
		
	}

}
