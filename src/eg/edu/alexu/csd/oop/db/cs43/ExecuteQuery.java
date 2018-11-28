package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExecuteQuery {
	private File tableFolder;
	private String[] columns;
	private String[] conditions;
	private ReadXml readXml;
	private Object[][] values;
	XSDReader reader;
	private String[] allcolumns;

	public ExecuteQuery(File tableFolder, String[] columns, String[] conditions) {
		this.columns = columns;
		this.conditions = conditions;
		this.tableFolder = tableFolder;
		readXml = new ReadXml();
		try {
			values = readXml.getArray(new File(tableFolder.getAbsolutePath() + System.getProperty("file.separator")
					+ tableFolder.getName() + ".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		reader = new XSDReader();
		reader.ReadXSD(
				tableFolder.getAbsolutePath() + System.getProperty("file.separator") + tableFolder.getName() + ".xsd");
		allcolumns = reader.getColumns();

	}

	public Object[][] select() {

		if (columns == null && conditions == null) { // select all columns
			return values;
			
		} else if (columns != null && conditions == null) { // select columns in columns array
			
			Object[][] returnedvalues = new Object[values.length][columns.length];
			List< String>cols = new LinkedList<>();
			cols = Arrays.asList(columns);
			for(int i = 0; i < values.length; i++) {
				int c = 0;
				for(int j = 0 ; j < values[0].length ; j++) {
					if(cols.contains(allcolumns[j])) {
						returnedvalues[i][c] = values[i][j];
								c++;
					}
				}
				
				if(c != cols.size()) {
					return null;
				}
			}
			return returnedvalues;
		} else if (columns != null && conditions == null) {
			
		}

		return null;
	}

}
