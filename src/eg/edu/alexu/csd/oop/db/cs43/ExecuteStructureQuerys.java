package eg.edu.alexu.csd.oop.db.cs43;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExecuteStructureQuerys {
	private File dataBaseFile;
	private String tableName;
	private String[] columnsnames;
	private String[] columnsTypes;
	
	
	public void setDataBaseFile(File dataBaseFile) {
		this.dataBaseFile = dataBaseFile;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public void setColumnsnames(String[] columnsnames) {
		this.columnsnames = columnsnames;
	}
	public void setColumnsTypes(String[] columnsTypes) {
		this.columnsTypes = columnsTypes;
	}
	
	
	public boolean createDataBase() {
		return dataBaseFile.mkdir();
	}
	public boolean dropDataBase() {
		return dataBaseFile.delete();
	}
	public boolean createTable() throws Exception{
		File tablefolder = new File(dataBaseFile.getAbsolutePath() + System.getProperty("file.separator") + tableName);
		tablefolder.mkdir();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.newDocument();
		Element table = d.createElement(tableName);
		d.appendChild(table);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(d);
		File xmlFile = new File(tablefolder.getAbsolutePath() + System.getProperty("file.separator") + tableName + ".xml");
		StreamResult streamResult = new StreamResult(xmlFile);
		transformer.transform(source, streamResult);
		createTableSchema(tablefolder);
		
		return true;
	}
	
	public boolean dropTable() {
		File tableFolder = new File(dataBaseFile.getAbsolutePath() + System.getProperty("file.separator") + tableName);
		return tableFolder.delete();
	}
	
	private void createTableSchema(File tabFolder2) throws IOException {
		File xmlSchema = new File(tabFolder2.getAbsolutePath() + System.getProperty("file.separator") + tableName + ".xsd");
		FileWriter fileWriter = new FileWriter(xmlSchema);
		BufferedWriter br = new BufferedWriter(fileWriter);
		br.write("<?xml version=\"1.0\"?>");
		br.newLine(); 	
		br.write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">");
		br.newLine();
		br.newLine();		
		br.write("  " + "<xs:element name= " + " \"" + tableName + " \" >");
		br.newLine();
		br.write("    " + "<xs:complexType>" );
		br.newLine();
		br.write("     " + "<xsd:sequence>");
		br.newLine();
		for(int i = 0; i < columnsnames.length; ++i) {
			br.write("      " + "<xsd:element " + "name= \"" + columnsnames[i] + "\"");
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	

}
