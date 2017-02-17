package com.prv.basics;

import java.util.Arrays;

public class ArrayExamples {
	public static void main(String[] args){
		int[] arr = new int[10];
		String[] arr2 = {"Mr. ", "Mrs. ", "Ms. "};
		String[][] names = {
				{"Mr. ", "Mrs. ", "Ms. "},
				{"Smith", "Jones"}
		};
		
		String[][] mdArr = new String[2][3];
		
		System.out.println(names[1].length);
		System.out.println(Arrays.deepToString(arr2));
		
        char[] copyFrom = { 'd', 'e', 'c', 'a', 'f', 'f', 'e',
			    'i', 'n', 'a', 't', 'e', 'd' };
        char[] copyTo = new char[7];

        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        System.out.println(new String(copyTo));
        
        char[] copyOfRange = Arrays.copyOfRange(copyFrom, 0, 3);
        //Implementation note: The sorting algorithm is a Dual-Pivot Quicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm offers O(n log(n)) performance on many data sets that cause other quicksorts to degrade to quadratic performance, and is typically faster than traditional (one-pivot) Quicksort implementations.
        Arrays.sort(copyFrom);
        Arrays.parallelSort(copyOfRange);
        System.out.println(Arrays.binarySearch(copyFrom, 'd'));
        //Arrays.equals and deepequals(for arrays other than primitives)
        //Arrays.fill -> fills arrays with the specified value
        
        int a = 5;
        System.out.println(a>>1);
        
	}
}
