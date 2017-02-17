package com.prv.threads;

import java.util.concurrent.Semaphore;
/*
 * As semaphore typically has two uses:

To guard a critical section against entry by more than N threads at a time.
To send signals between two threads.
 */

public class SemaphoneExample {

	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(1);

		//critical section
		semaphore.acquire();

		System.out.println("Critical section");

		semaphore.release();
	}

}
