package com.prv.interfaces;

public interface CarPlus extends Car {

	public abstract void connect();
	
	default void speed(){
		System.out.println("Speed2");
	}
}
