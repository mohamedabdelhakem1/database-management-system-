package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;

public class ExecuteQuery {
	private File tableFolder;
	private String[] columns;
	private String[] conditions;
	private ReadXml readXml;
	private Object[][] values;
	
	public ExecuteQuery(File tableFolder, String[] columns, String[] conditions) {
		this.columns = columns;
		this.conditions = conditions;
		this.tableFolder = tableFolder;
		readXml = new ReadXml();
		try {
			values = readXml.getArray(new File(
					tableFolder.getAbsolutePath() + System.getProperty("file.separator") + tableFolder.getName() + ".xml"));
		} catch (Exception e) {
		
		}
		
	}

	public Object[][] select() {
		
		if (columns == null && conditions == null) { //select all columns
			return values;
		}else if (columns !=null && conditions == null) { // select columns in columns array
			
		}

		return null;
	}

	
}
