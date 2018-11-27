package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.XMLConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class tester {

	private final static String NS_PREFIX = "xs:";

	public static void main(String[] args) {
		ReadXml readXml  = new ReadXml();
		WriteXml writeXml = new WriteXml();
		File file = new File("table_name1.xml");
		try {
			Object [][] s= readXml.getArray(file);
			
			writeXml.writeTable(s, new String[]{"col1","col2","col3"}, new File("dd") );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}