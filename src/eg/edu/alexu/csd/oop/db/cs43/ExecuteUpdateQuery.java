package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.xml.sax.XMLReader;


public class ExecuteUpdateQuery  {
	private File DataBaseFile; 
	private String tableName;
	
	private String[] columnsNames;
	private String[] columnsTypes;
	private String[] columnsValues;

	public void setColumnsValues(String[] columnsValues) {
		this.columnsValues = columnsValues;
	}

	public void setDataBaseFile(File DataBaseFile) {
		this.DataBaseFile = DataBaseFile;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setColumnsNames(String[] columnsnames) {
		this.columnsNames = columnsnames;
	}

	public void setColumnsTypes(String[] columnsTypes) {
		this.columnsTypes = columnsTypes;
	}

	public int insertData() throws SQLException, Exception {
		
		File tablefolder = new File(DataBaseFile.getAbsolutePath() + System.getProperty("file.separator") + tableName);
		if (!tablefolder.exists()) {
			// table isn't exist in database
			
			throw new SQLException();
		}

		XSDReader xsdReader = new XSDReader();
		xsdReader.ReadXSD(DataBaseFile.getAbsolutePath() + System.getProperty("file.separator") + tableName
				+ System.getProperty("file.separator") + tableName + ".xsd");
		
		String[] tableColumnsNames = xsdReader.getColumns();
		
		ReadXml readXml = new ReadXml();
		File tableXmlFile = new File(DataBaseFile.getAbsolutePath() + System.getProperty("file.separator") + tableName
				+ System.getProperty("file.separator") + tableName + ".xml");

		Object[][] xmlData = readXml.getArray(tableXmlFile);
		
		if(xmlData == null) {
			xmlData = new Object[0][0];
		}
		Object[][] newXmlData = CopyOldXmlInNewXml(xmlData, columnsNames.length);

		if (tableColumnsNames.length != columnsNames.length) {
			throw new SQLException();
		}

		for (int i = 0; i < tableColumnsNames.length; ++i) {
			int index = getIndex(tableColumnsNames[i], columnsNames);// query jas column name doesnot exist
			if( index == -1 ) {// query has column name does not exist
				throw new SQLException();
			}
			newXmlData[xmlData.length][i] = columnsValues[index];
		}
		WriteXml writeXml = new WriteXml();
		writeXml.writeTable(newXmlData, tableColumnsNames, tablefolder);
		XmlValidation validation = new XmlValidation();
		try {
			validation.validateXml(tablefolder);
			
		} catch (Exception e) {
			
			writeXml.writeTable(xmlData, tableColumnsNames, tablefolder);
		}
		
		return 1;
	}
	private Object[][] CopyOldXmlInNewXml(Object[][] arr, int columnsNumber) {
		Object[][] newXml= null;
	if(arr.length != 0) {
		 newXml = new Object[arr.length + 1][arr[0].length];
		for(int i = 0; i < arr.length; ++i) {
			for(int j = 0;j < arr[0].length; ++j) {
				newXml[i][j] = arr[i][j];
				
			}
		}
	}
	else {
		
		 newXml = new Object[1][columnsNumber];
	}
		return newXml;
		
	}

	private int getIndex(String name, String columnsNames[]) {

		for (int i = 0; i < columnsNames.length; ++i) {
			if(name.equalsIgnoreCase(columnsNames[i])) {
				return i;
			}
		}
		return -1;
	}
}
