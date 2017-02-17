package com.prv.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A synchronized block makes no guarantees about the sequence in which threads waiting to entering it are granted access.
You cannot pass any parameters to the entry of a synchronized block. Thus, having a timeout trying to get access to a synchronized block is not possible.
The synchronized block must be fully contained within a single method. A Lock can have it's calls to lock() and unlock() in separate methods.
 * @author pvemulam
 *
 */
public class LocksExample {

	public static void main(String[] args){
		Lock lock = new ReentrantLock();

		lock.lock();

		//critical section

		lock.unlock();
	}
}
