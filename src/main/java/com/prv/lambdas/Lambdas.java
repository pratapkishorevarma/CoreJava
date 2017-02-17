package com.prv.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Lambdas {

	static class Person{
		enum Gender {MALE, FEMALE};
		private String name;
		private int age;
		private Gender gender;
		
		public Person(String name, int age, Gender gender) {
			super();
			this.name = name;
			this.age = age;
			this.gender = gender;
		}
		
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
		public Gender getGender() {
			return gender;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + ", gender="
					+ gender + "]";
		}
		
			
	}
	
	private static void printPersons(List<Person> roster,
			Predicate<Person> tester,
			Function<Person, String> mapper,
			Consumer<String> block) {
		for (Person p : roster) {
			if (tester.test(p)) {
				String data = mapper.apply(p);
				block.accept(data);
			}
		}
	}
	
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Lambdas.Person>();
		persons.add(new Person("B", 20, Person.Gender.FEMALE));
		persons.add(new Person("A",25, Person.Gender.MALE));

		printPersons(persons,  p -> p.getGender() == Person.Gender.MALE
		        && p.getAge() >= 18
		        && p.getAge() <= 25,
		    p -> p.getName(),
		    name -> System.out.println(name));
		
		persons.stream()
	    .filter(
	        p -> p.getGender() == Person.Gender.MALE
	            && p.getAge() >= 18
	            && p.getAge() <= 25)
	    .map(p -> p.getName())
	    .forEach(name -> System.out.println(name));
		
		Person minAgePerson = persons.stream().min((p1,p2) -> p1.getAge()-p2.getAge()).get();
		List<Person> collect = persons.stream().filter(p -> p.getName().startsWith("A")).collect(Collectors.toList());
		
		Integer integer = persons.stream().map(p -> p.getAge()).reduce((totalAge,pAge) -> totalAge+pAge).get();
		
		Arrays.sort(persons.toArray(new Person[0]), (a, b) ->  a.getName().compareTo(b.getName()));
		Arrays.sort(persons.toArray(new Person[0]), (Person a, Person b) ->  a.getName().compareTo(b.getName()));
		Arrays.sort(persons.toArray(new Person[0]), (Person a, Person b) ->  
		                          {return a.getName().compareTo(b.getName());} );
		
		System.out.println(persons.toString());
	}

}
