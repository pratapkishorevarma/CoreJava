package com.prv.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateXML {

	public static boolean isXMLValid(File schema, File xml){
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema newSchema = schemaFactory.newSchema(schema);
			Validator newValidator = newSchema.newValidator();
			newValidator.validate(new StreamSource(xml));
		} catch (IOException | SAXException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
