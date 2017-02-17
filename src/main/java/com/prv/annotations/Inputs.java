package com.prv.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Inherited	//this annotation is applicable to subclass if this is present for a super class							
@Target(value = { ElementType.TYPE }) // Type means any thing class, method,enum etc
public @interface Inputs {

}
