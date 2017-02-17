package com.prv.generics;

public class BoundedNumber<T extends Number> { //extends used for both classes and interfaces

    private T n;

    public BoundedNumber(T n)  { this.n = n; }

    public boolean isEven() {
        return n.intValue() % 2 == 0;
    }
    
}

class A{
	
}
interface B{
	
}
interface C{
	
}

class D<T extends  A & B & C>{ // classes(A) should come first otherwise compile error
	
}