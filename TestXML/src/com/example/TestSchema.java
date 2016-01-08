package com.example;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import java.io.*;
import javax.xml.validation.*;
import javax.xml.transform.stream.StreamSource;

public class TestSchema{
	public static void main(String args[]){
		File xsdfile = new File("test.xsd");
		File xmlfile = new File("test.xml");
		Handler errorHandler = null;
		try{
			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = schemaFactory.newSchema(xsdfile);
			Validator validator = schema.newValidator();
			errorHandler = new Handler();
			if(errorHandler == null)
			{
				System.out.println("create errorHandler fail");
			}
			validator.setErrorHandler(errorHandler);
			validator.validate(new StreamSource(xmlfile));
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		if(errorHandler.errorMessage == null)
		{
			System.out.println("XML 文件" +xmlfile.getName() +"符合模式");
		}
		else
		{
			System.out.println("XML 文件" +xmlfile.getName() +"不符合模式");
		}
	}
}

class Handler extends DefaultHandler
{
	String errorMessage = null;
	public void error(SAXParseException e) throws SAXException
	{
		errorMessage = e.getMessage();
		int row = e.getLineNumber();
		int colums = e.getColumnNumber();
		System.out.println("一般错误：" +errorMessage+ "位置" +row+ "," + colums);
	}
	public void fatalError(SAXParseException e) throws SAXException
	{
		errorMessage = e.getMessage();
		int row = e.getLineNumber();
		int colums = e.getColumnNumber();
		System.out.println("致命错误：" +errorMessage+ "位置" +row+ "," + colums);
	}
	
}

















