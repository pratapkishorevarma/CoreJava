package com.prv.lambdas;

public class CalculatorLambda {
	  
    interface IntegerMath {
        int operation(int a, int b);   
    }
  
    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }
 
    public static void main(String... args) {
    
        CalculatorLambda myApp = new CalculatorLambda();
        IntegerMath subtraction = (a, b) -> {return a - b;};
        System.out.println("40 + 2 = " +
            myApp.operateBinary(40, 2, (a, b) -> a + b));
        System.out.println("20 - 10 = " +
            myApp.operateBinary(20, 10, subtraction));    
    }
}