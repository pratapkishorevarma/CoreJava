package com.prv.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStream {

	public static void main(String[] args) {
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			is = new FileInputStream("resources/com/prv/io/input.txt");
			os = new FileOutputStream("resources/com/prv/io/output.txt");
			int i;
			
			while((i=is.read()) != -1){
				os.write(i);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
