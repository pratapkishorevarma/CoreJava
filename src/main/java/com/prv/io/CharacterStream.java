package com.prv.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CharacterStream {

	public static void main(String[] args) {
		readWriteUsingFileReaderWriter();
		readWriteUsingBuffReaderWriter();
	}
	
	private static void readWriteUsingBuffReaderWriter() {
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			br = new BufferedReader(new FileReader("resources/com/prv/io/input.txt"));
			pw = new PrintWriter(new FileWriter("resources/com/prv/io/output.txt"));
			String s;
			while((s=br.readLine()) != null){
				pw.println(s);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pw != null){
				pw.close();
			}
		}
	}

	private static void readWriteUsingFileReaderWriter() {
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader("resources/com/prv/io/input.txt");
			fw = new FileWriter("resources/com/prv/io/output.txt");
			int i;
			while((i=fr.read()) != -1){
				fw.write(i);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fw != null){
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
