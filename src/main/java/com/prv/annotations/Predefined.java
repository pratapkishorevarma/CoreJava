package com.prv.annotations;

public class Predefined {

    /**
     * @deprecated
     * explanation of why it was deprecated
     */
    @Deprecated
    public void deprecatedMethod() { }

	@Override
	public String toString() {
		return super.toString();
	}
    

    public static void main(String[] args){
    	Predefined pre = new Predefined();
    	pre.deprecatedMethod();
    }
    
    
}
