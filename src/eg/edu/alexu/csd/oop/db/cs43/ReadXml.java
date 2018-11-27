package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ReadXml {
	public ReadXml() {

	}

	public Object[][] getArray(File table) throws Exception {
		Object[][] values = null;
		int noOfRows = 0;
		int noOfcolumns = 0;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(table);

		NodeList nodes = document.getElementsByTagName(table.getName().substring(0, table.getName().length() - 4));
		Node node = nodes.item(0);
		int cout = 0;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			NodeList rows = document.getElementsByTagName("row");
			if (values == null) {
				noOfRows = rows.getLength();
			}
			for (int i = 0; i < noOfRows; i++) {
				Node row = rows.item(i);
				Node column = row.getFirstChild();
			
				
				List<Object> columnList = new LinkedList<>();
				while (column != null) {
					columnList.add(column.getTextContent());
					column = column.getNextSibling();
				}
				Object[] columnArray = new Object[columnList.size()];
				columnArray = columnList.toArray(columnArray);
				if(values == null) {
					values = new Object[noOfRows][columnList.size()];
				}
				values[i] = columnArray;
			}
		}
		
		return values;
	}
}
