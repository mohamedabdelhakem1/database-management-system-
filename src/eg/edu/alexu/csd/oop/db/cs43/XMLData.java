package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.AmbientLight;

public class XMLData {
	private Map<String, Object> xmlData = null;

	private String[] columns;
	private String[] types;
	private File databasename;
	private String tableName;

	public XMLData(File databasename, String tableName) {
		this.tableName = tableName;
		this.databasename = databasename;
	}

	public void loadXml() throws SQLException {
		if (xmlData == null) {
			xmlData = new HashMap<>();
			xmlData.put("database", databasename);
			xmlData.put("tablename", tableName);
			ReadXml readXml = new ReadXml();
			Object[][] xml = readXml.getArray(new File(((File) xmlData.get("database")).getAbsolutePath()
					+ System.getProperty("file.separator") + xmlData.get("tablename")
					+ System.getProperty("file.separator") + xmlData.get("tablename") + ".xml"));
			XSDReader xsdReader = new XSDReader();
			xsdReader.ReadXSD(((File) xmlData.get("database")).getAbsolutePath() + System.getProperty("file.separator")
					+ xmlData.get("tablename") + System.getProperty("file.separator") + xmlData.get("tablename")
					+ ".xsd");

			types = xsdReader.getTypes();
			columns = xsdReader.getColumns();
			xmlData.put("array", xml);
			xmlData.put("types", types);
			xmlData.put("columns", columns);
		}

	}

	public Map<String, Object> getXml() {
		if (xmlData == null) {
			try {
				loadXml();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return xmlData;
	}

	public void SaveXml() throws SQLException {
		WriteXml writeXml = new WriteXml();
		writeXml.writeTable((Object[][])xmlData.get("array"), columns, new File(((File) xmlData.get("database")).getAbsolutePath()
				+ System.getProperty("file.separator") + xmlData.get("tablename")));
	}

}
