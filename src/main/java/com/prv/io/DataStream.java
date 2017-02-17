package com.prv.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStream {

	public static void main(String[] args) {
		int i = 1;
		double d = 123.567;
		String s = "This is data";
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("resources/com/prv/io/binaryiodata")));
			dos.writeInt(i);
			dos.writeDouble(d);
			dos.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream("resources/com/prv/io/binaryiodata")));
			System.out.println(dis.readInt());
			System.out.println(dis.readDouble());
			System.out.println(dis.readUTF());
			System.out.println(dis.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		

	}

}
