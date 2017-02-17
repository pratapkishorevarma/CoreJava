package com.prv.xml.sax;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.prv.xml.dom.Employee;
 
public class SAXReader {
 
    public static void main(String[] args) {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    try {
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MyHandler handler = new MyHandler();
        saxParser.parse(new File("resources/com/prv/xml/Employees.xml"), handler);
        //Get Employees list
        List<Employee> empList = handler.getEmpList();
        //print employee information
        for(Employee emp : empList)
            System.out.println(emp);
    } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
    }
    }
 
}