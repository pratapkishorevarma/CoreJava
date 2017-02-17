package com.prv.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * BlockingQueue Impl classes
 * 
 * ArrayBlockingQueue
 * DelayQueue
 * LinkedBlockingQueue
 * PriorityBlockingQueue
 * SynchronousQueue
 */
public class BlockingQueueExample {

	public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
	}

	static class Producer implements Runnable{

	    protected BlockingQueue<String> queue = null;

	    public Producer(BlockingQueue<String> queue) {
	        this.queue = queue;
	    }

	    public void run() {
	        try {
	            queue.put("1");
	            Thread.sleep(1000);
	            queue.put("2");
	            Thread.sleep(1000);
	            queue.put("3");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	static class Consumer implements Runnable{

	    protected BlockingQueue<String> queue = null;

	    public Consumer(BlockingQueue<String> queue) {
	        this.queue = queue;
	    }

	    public void run() {
	        try {
	            System.out.println(queue.take());
	            System.out.println(queue.take());
	            System.out.println(queue.take());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
