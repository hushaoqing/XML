package com.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
public class TestDom1  
{
	public static void main(String[] args) 
	{
		try{
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder domPaser=factory.newDocumentBuilder();
			Document document = domPaser.parse(new File("test.xml"));
			Element root=document.getDocumentElement();
			String rootName=root.getNodeName();
			System.out.println("XML文件根节点的名字："+rootName);
			NodeList nodeList =root.getElementsByTagName("category");
			int size=nodeList.getLength();
			for(int k=0;k<size;k++){
				Node node=nodeList.item(k);
				String name=node.getNodeName();
				String content=node.getTextContent();
				System.out.print(name);
				System.out.println(":"+content);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
