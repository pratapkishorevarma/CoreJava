package com.prv.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStream {

	static class Address implements Serializable{

		private static final long serialVersionUID = 5576379163470972223L;
		private String street;
		private String city;
		public Address(String street, String city) {
			super();
			this.street = street;
			this.city = city;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return "Address [street=" + street + ", city=" + city + "]";
		}
		
	}
	
	static class Person implements Serializable{

		private static final long serialVersionUID = -6295184968737597628L;
		private String name;
		private int age;
		private Address address;

		public void setName(String name) {
			this.name = name;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
		public Address getAddress() {
			return address;
		}
		public Person(String name, int age, Address address) {
			super();
			this.name = name;
			this.age = age;
			this.address = address;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + ", address="
					+ address + "]";
		}
		
	}
	public static void main(String[] args) {
		Person p = new Person("ABC", 10, new Address("asdf", "bng"));

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("resources/com/prv/io/objectstream")));
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(oos != null){
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
		
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("resources/com/prv/io/objectstream")));
			Person dup = (Person) ois.readObject();
			System.out.println(dup.toString());
			dup.setAge(20);
			dup.getAddress().setCity("hyd");
			System.out.println(dup.toString());
			System.out.println(p.toString());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(ois != null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}

}
