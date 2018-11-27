package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class createTable {
	private String tableName;
	private String[] columnsNames;
	private String[] columnsTypes; 
	private String dataBasePath;
	
	public createTable(String tableName, String[] columnsNames, String[] columnsTypes, String dataBasePath) {
		this.tableName = tableName;
		this.columnsNames = columnsNames;
		this.columnsTypes = columnsTypes;
		this.dataBasePath = dataBasePath +"\\"+tableName + ".xml";
	}
	public boolean creatMytable() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.newDocument();
		Element element = d.createElement(tableName);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(d);
		StreamResult streamResult = new StreamResult(new File(dataBasePath));
		transformer.transform(source, streamResult);
		
		createXSDFile();
		
		return true;

	}
	
	private void createXSDFile() {
		
	}
	
}
