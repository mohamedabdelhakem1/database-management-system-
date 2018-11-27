package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXml {
	public WriteXml() {

	}

	/**
	 * 
	 * @param values
	 *            each row in these array is saved in a tag <row> </row> like in the
	 *            file
	 * @param columnNames
	 *            the tag names in each row in the XML file
	 * @return boolean if the file created or not
	 * @throws Exception
	 */
	
	public boolean writeTable(Object[][] values, String[] columnNames, File tablefolder) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.newDocument();
		Element Table = d.createElement(tablefolder.getName());
		d.appendChild(Table);
		for (int i = 0; i < values.length; i++) {
			Element row = d.createElement("row");
			Table.appendChild(row);
			for (int j = 0; j < values[i].length; j++) {
				Element column = d.createElement(columnNames[j]);
				row.appendChild(column);
				column.setTextContent((String)values[i][j]);
			}
		}
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domsource = new DOMSource(d);
		File xml = new File(
				tablefolder.getAbsolutePath() + System.getProperty("file.separator") + tablefolder.getName() + ".xml");
		if(xml.exists()) {
			xml.delete();
		}
		StreamResult streamResult = new StreamResult(xml);
		transformer.transform(domsource, streamResult);
		return xml.exists();
	}
}
