package com.prv.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class ScannerFormatter {

	public static void main(String[] args) {
		scanFile("resources/com/prv/io/input.txt");
		//scanInput();
	}

	private static void scanFile(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(fileName)));
			s.useLocale(Locale.US);
			s.useDelimiter(" ");
			while(s.hasNext()){
				System.out.println(s.next());
				//System.out.println(s.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	private static void scanInput() {
		Scanner s = new Scanner(System.in);
		String input;
		while(s.hasNext() && !(input=s.next()).equalsIgnoreCase("end")){
			System.out.println(input);
		}
		s.close();
	}
}
