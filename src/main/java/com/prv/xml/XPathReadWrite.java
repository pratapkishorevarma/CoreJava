package com.prv.xml;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathReadWrite {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
//		getAttributeValue(new File("resources/com/prv/xml/biee-domain.xml"), "/*[local-name()='BIDomain']/*[local-name()='BIInstance']/*[local-name()='EssbaseOptions']");
//		getAttributeValue(new File("resources/com/prv/xml/biee-domain.xml"), "//*[name()='EssbaseOptions']");
		getAttributeValue(new File("resources/com/prv/xml/xmlp-server-config.xml"), "//*[name()='property']");
//		setAttributeValue(new File("resources/com/prv/xml/biee-domain.xml"), "//EssbaseOptions/@sharedFolderPath");
	}

	private static void getAttributeValue(File xmlFile, String xpathExpr)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(xmlFile);

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(xpathExpr);
		NodeList nodelist = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		for(int i=0; i<nodelist.getLength(); i++){
			Node node = nodelist.item(i);

			NamedNodeMap attributes = node.getAttributes();
			Node name = attributes.getNamedItem("name");
			Node value = attributes.getNamedItem("value");
			System.out.println(name.getNodeValue() + ":" + value.getNodeValue());
		}
	}

	private static void setAttributeValue(File xmlFile, String xpathExpr)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(xmlFile);

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(xpathExpr);
		Node node = (Node) expr.evaluate(document, XPathConstants.NODE);
		if(node.getNodeName().equals("sharedFolderPath")){
			Attr a = (Attr) node;
			a.setValue("pratap");
		}
		documentToFile(document, xmlFile);
		
		System.out.println(node.getNodeName() + node.getNodeType() + node.getNodeValue());
	}
	
    public static void documentToFile(Document doc, File file) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // handle DOCTYPE setting
            if (doc.getDoctype() != null) {
                String systemValue = doc.getDoctype().getSystemId();
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemValue);
            }
            transformer.transform(new DOMSource(doc), new StreamResult(file));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
