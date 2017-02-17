package com.prv.xml.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXParserClient {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setNamespaceAware(true);
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema newSchema = schemaFactory.newSchema(new File("resources/com/prv/xml/SampleSchema.xsd"));
		
		saxParserFactory.setSchema(newSchema);
		SAXParser saxParser = saxParserFactory.newSAXParser();
		saxParser.parse(new File("resources/com/prv/xml/Sample.xml"), new SAXCustomer());
		/* can do as below also
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(new SAXCustomer());
		xmlReader.setErrorHandler(new SAXCustomer());
		xmlReader.parse("file:/D:/project/workspace/learn java/resources/com/prv/xml/Sample.xml");*/
	}

}
