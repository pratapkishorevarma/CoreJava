package com.prv.interfaces;

public interface Car {
	String start();
	boolean accelerate();
	boolean brake();
	default void automatic(){
		System.out.println("Auto gear");
	}
	default void navigate(){
		System.out.println("Auto gear");
	}
	default void connect(){
		System.out.println("Auto gear");
	}
	static void speed(){
		System.out.println("Speed");
	}
}
