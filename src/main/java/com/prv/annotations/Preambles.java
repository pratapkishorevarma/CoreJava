package com.prv.annotations;

import java.lang.annotation.Documented;

@Documented
public @interface Preambles {
	ClassPreamble[] value();
}
