package com.prv.xml;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ValidateXMLTest {

	@Test
	public void testXML() {
		assertTrue(ValidateXML.isXMLValid(new File("src/main/resources/com/prv/xml/SampleSchema.xsd"), new File("src/main/resources/com/prv/xml/Sample.xml")));
	}

}
