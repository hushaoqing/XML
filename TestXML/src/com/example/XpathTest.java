package com.example;

import javax.xml.xpath.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class  XpathTest
{
	public static void main(String[] args) 
	{
		XPathWindow win = new XPathWindow("test.xml");
	}
}

class XPathWindow extends JFrame implements ActionListener
{
	XPathFactory xPathFactory;
	XPath xPath;
	InputSource source;
	JTextField inputXPath;
	JTextArea showResult;
	JButton button;

	public XPathWindow(String fileName)
	{
		try{
			xPathFactory = XPathFactory.newInstance();
			xPath = xPathFactory.newXPath();
			source = new InputSource(fileName);
			inputXPath = new JTextField(25);
			showResult = new JTextArea();
			button = new JButton("OK");
			button.addActionListener(this);
			inputXPath.addActionListener(this);
			JPanel north = new JPanel();
			north.add(new Label("XPath 表达式:"));
			north.add(inputXPath);
			north.add(button);
			add(north,BorderLayout.NORTH);
			add(new JScrollPane(showResult),BorderLayout.CENTER);
			setBounds(10,10,900,300);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}catch(Exception exp)
		{
			System.out.println(exp);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		showResult.setText(null);
		String path = inputXPath.getText();
		try{
			NodeList nodelist = (NodeList)xPath.evaluate(path,source,XPathConstants.NODESET);
			int size = nodelist.getLength();
			showResult.append("节点集中的节点个数："+size+"\n");
			showResult.append("节点的名字及节点的值依次为：\n");
			for(int k = 0 ;k < size ;k++)
			{
				Node node = nodelist.item(k);
				String name = node.getNodeName();
				showResult.append("第"+(k+1)+"个节点的名字："+name+",");
				String value = node.getNodeValue();
				showResult.append("第"+(k+1)+"个节点的值："+value+"\n");
			}
		}catch(Exception exp)
		{
			showResult.setText(null);
			showResult.append("异常："+exp);
		}
	}
}