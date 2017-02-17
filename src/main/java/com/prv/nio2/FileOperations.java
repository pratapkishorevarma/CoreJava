package com.prv.nio2;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileOperations {

	public static void main(String[] args) throws IOException {
		Path sample = Paths.get("resources/com/prv/xml/Sample.xml");
		Path sample2 = Paths.get("resources/com/prv/../prv/xml/Sample.xml");
		Path toCopy = Paths.get("resources/com/prv/io/copy.xml");
		Path invalid = Paths.get("resources/invalid");
		Path dir = Paths.get("resources");

		System.out.println(Files.exists(sample, LinkOption.NOFOLLOW_LINKS));
		System.out.println(Files.notExists(sample));
		System.out.println(Files.isExecutable(sample));
		System.out.println(Files.isReadable(sample));
		System.out.println(Files.isSameFile(sample, sample2));
		
		Files.deleteIfExists(invalid);
		try{
			Files.delete(dir);			
		}catch(DirectoryNotEmptyException e){ //optional exception
			System.out.println("Not empty");
		}
		//Files.delete(invalid);
		
		Files.copy(sample, toCopy, StandardCopyOption.REPLACE_EXISTING);
	}

}
