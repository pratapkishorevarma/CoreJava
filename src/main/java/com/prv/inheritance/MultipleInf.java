package com.prv.inheritance;

public class MultipleInf {

	interface Animal{
		void name();
		default void type(){
			System.out.println("Herbivorous");
		}
		default void test(){
			System.out.println("test Animal");
		}
	}
	
	interface Bird{
		void name();
		default void test(){
			System.out.println("test Bird");
		}
		void myName();
	}
	
	static class Living{
		public void type(){
			System.out.println("Living");
		}
		public void myName(){
			System.out.println("In Living world");
		}
	}
	
	static class BirdAnimal extends Living implements Animal,Bird{

		@Override
		public void name() {
			System.out.println("Animal Bird");
		}
		public void test(){
			Animal.super.test();
			Bird.super.test();
		}
	}
	
	public static void main(String[] args){
		BirdAnimal ba = new BirdAnimal();
		ba.name();
		ba.type(); //output: living
		ba.test();
		ba.myName();
	}
}
