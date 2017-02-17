package com.prv.nio2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathOperations {

	public static void main(String[] args) {
		Path inputPath = Paths.get("resources/com/prv/io/input.txt");
		Path sample = Paths.get("D:\\project\\workspace\\learn java\\resources\\com\\prv\\xml\\Sample.xml");
		Path samplerelative = Paths.get("resources/com/prv/xml/Sample.xml");
		Path invalid = Paths.get("resources/../com/prv/./io/invalid");
		
		pathOperations(inputPath);
		pathOperations(sample);
		pathOperations(invalid);
		System.out.println(invalid.normalize()); //removes ../ and ./
		realPath(inputPath);
		
		//Combine paths using resolve
		System.out.println(invalid.resolve("asdf"));
		System.out.println(invalid.resolve(sample)); //if absolute path passed, the same is returned
		System.out.println(invalid.resolve(inputPath));
		
		Path p1 = Paths.get("home");
		Path p3 = Paths.get("home/sally/bar");
		// Result is sally/bar
		Path p1_to_p3 = p1.relativize(p3);
		// Result is ../..
		Path p3_to_p1 = p3.relativize(p1); 
		//A relative path cannot be constructed if only one of the paths includes a root element. If both paths include a root element, the capability to construct a relative path is system dependent.
		// The recursive Copy example uses the relativize and resolve methods.
		
		System.out.println(sample.equals(samplerelative));
		System.out.println(inputPath.startsWith("resources"));
		
		for(Path p : inputPath){ // iterate through the path elements
			System.out.println(p.toString());
		}
	}

	private static void realPath(Path inputPath) {
		try {
			System.out.println(inputPath.toRealPath());
			/*
			 * This method performs several operations in one:
    If true is passed to this method and the file system supports symbolic links, this method resolves any symbolic links in the path.
    If the Path is relative, it returns an absolute path.
    If the Path contains any redundant elements, it returns a path with those elements removed.
			 */
		} catch (IOException e) {
			// Logic for case when file doesn't exist.
			e.printStackTrace();
		}
	}

	private static void pathOperations(Path path) {
		System.out.println(path.getFileName());
		System.out.println(path.getRoot());  // null for relative paths
		System.out.println(path.getNameCount());// no of name elements
		System.out.println(path.getParent());
		System.out.println(path.getFileSystem());
		System.out.println(path.getName(0));
		System.out.println(path.subpath(0, 2));
		System.out.println(path.isAbsolute());
		System.out.println(path.toAbsolutePath());
		System.out.println(path.toUri());
		System.out.println("---------------------------------------------------------------");
	}

}
