package com.prv.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XsltTransform {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder newDocumentBuilder = domFactory.newDocumentBuilder();
		Document doc = newDocumentBuilder.parse("resources/com/prv/xml/jps-config.xml");
		
		StreamSource stylesource = new StreamSource(new File("resources/com/prv/xml/jps.xslt"));
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer newTransformer = transformerFactory.newTransformer(stylesource);
		
		DOMSource domSource = new DOMSource(doc);
		
		StreamResult result = new StreamResult(System.out);
		newTransformer.transform(domSource, result);

	}

}
