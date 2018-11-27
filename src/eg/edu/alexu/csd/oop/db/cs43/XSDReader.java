package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.xml.sax.SAXException;

import com.sun.corba.se.impl.orbutil.graph.Node;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.xs.XSImplementationImpl;
import com.sun.org.apache.xerces.internal.xs.XSConstants;
import com.sun.org.apache.xerces.internal.xs.XSLoader;
import com.sun.org.apache.xerces.internal.xs.XSModel;

public class XSDReader {
	private List<String> columns = new LinkedList<>();
	private List<String> types = new LinkedList<>();

	public XSDReader() {

	}

	public void ReadXSD(String path) {
		File xsd = new File(path);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("table_name1.xsd");
			NodeList list = document.getElementsByTagName("xs:element");
			for (int i = 2; i < list.getLength(); i++) {
				NamedNodeMap map = list.item(i).getAttributes();
				map.getNamedItem("name");
				columns.add(String.valueOf(map.item(0)));
				System.out.println(map.getNamedItem("name"));
				System.out.println(map.getNamedItem("type"));
				//types.add(String.valueOf(map.item(0).getNodeType()));
			//	System.out.println((map.item(0).getNodeType()));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
