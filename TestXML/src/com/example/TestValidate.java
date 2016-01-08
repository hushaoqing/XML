package com.example;

import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import java.util.Scanner;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

public class TestValidate{
	public static void main(String args[]){
		String fileName = null;
		try{
			Scanner reader = new Scanner(System.in);
			System.out.print("������ҪҪ����Ч�Ե�XML�ļ������֣�");
			fileName = reader.nextLine();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			MyHandler handler = new MyHandler();
			builder.setErrorHandler(handler);
			Document document = builder.parse(new File(fileName));
			if(handler.errorMessage == null)
				System.out.println(fileName + "�ļ�����Ч��");
			else
				System.out.println(fileName + "�ļ�����Ч��");
		}catch(Exception e){
			System.out.println(e);
		}		
	}	
}

class MyHandler extends DefaultHandler{
	String errorMessage = null ;
	public void error(SAXParseException e) throws SAXException{
		errorMessage = e.getMessage();
		System.out.println("һ�����:" + errorMessage);
	}
	public void fatalError(SAXParseException e) throws SAXException{
		errorMessage = e.getMessage();
		System.out.println("��������:"+errorMessage);
	}
	
}