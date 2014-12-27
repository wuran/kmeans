package cn.itcast.util;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlParse {
	private static String filename="src/datas.xml";
	/*获取文档对象树*/
	public static Document getDocument() throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		/*factory.setIgnoringElementContentWhitespace(true);*/
		DocumentBuilder document=factory.newDocumentBuilder();
		Document dom=document.parse(filename);
		return dom;
	}
	/*将内存更新写入数据库xml*/
	public static void write2Xml(Document document) throws Exception{
		TransformerFactory factory=TransformerFactory.newInstance();
		Transformer transformer=factory.newTransformer();
		transformer.transform(new DOMSource(document),new StreamResult(new FileOutputStream(filename)));
	}
}
