package com.prv.interfaces;

public class Hyundai implements Car {

	@Override
	public String start() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean accelerate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean brake() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args){
		Car c = new Hyundai();
		c.automatic();
		Car.speed();
	}

}
