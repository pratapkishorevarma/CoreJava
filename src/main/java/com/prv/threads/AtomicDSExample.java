package com.prv.threads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicDSExample {

	public static void main(String[] args) {
		AtomicBoolean atomicBoolean = new AtomicBoolean(true);
		AtomicInteger atomicInt = new AtomicInteger(1234);
		AtomicLong atomicLong = new AtomicLong(453225);
		String initialReference = "the initially referenced string";
		AtomicReference<String> atomicStringReference = new AtomicReference<String>(initialReference);

		System.out.println(atomicBoolean.get());
		atomicBoolean.set(false);
		System.out.println(atomicBoolean.getAndSet(true));
		System.out.println(atomicBoolean.compareAndSet(
		    true, false));
		
		System.out.println(atomicStringReference.get());
	}

}
