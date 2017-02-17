package com.prv.io;

import java.io.Console;
import java.util.Arrays;

public class ReadConsole {

	public static void main(String[] args) {
		Console c = System.console();
		
		if(c != null){ //console might be null when the os doesnt support or the program run in non interactive mode. 
			String user = c.readLine("Enter user:");
			char[] password = c.readPassword("Enter password");
			System.out.println("User: "+ user + " Password: "+ Arrays.toString(password));
		}
	}

}
