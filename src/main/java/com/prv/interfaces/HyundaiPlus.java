package com.prv.interfaces;

import java.util.Comparator;

public class HyundaiPlus implements CarPlus {

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

	@Override
	public void connect() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args){
		CarPlus cp = new HyundaiPlus();
		cp.speed();
		
		//thenComparing,comparing are default methods in Comparator
		Comparator<CarPlus> comparator = Comparator.comparing(CarPlus::start).
				reversed().thenComparing(CarPlus::brake);
		
	}

}
