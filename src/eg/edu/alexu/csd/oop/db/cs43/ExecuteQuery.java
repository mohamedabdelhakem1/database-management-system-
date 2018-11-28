package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExecuteQuery {
	
	private String[] columns;
	private String[] conditions;
	private ReadXml readXml;
	private Object[][] values;
	private XSDReader reader;
	private String[] allcolumns;
	private String[] allTypes;
	private ConditionsManipulation manipulation;
	private ColumnsManipulation columnsManipulation;
	public ExecuteQuery(File tableFolder, String[] columns, String[] conditions) {
		this.columns = columns;
		this.conditions = conditions;
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
		allTypes = reader.getTypes();

	}

	public Object[][] select() {

		if (columns == null && conditions == null) { // select all columns
			return values;

		} else if (columns != null && conditions == null) { // select columns in columns array

			Object[][] returnedvalues = new Object[values.length][columns.length];
			
			for (int i = 0; i < values.length; i++) {
				int c = 0;
				for (int j = 0; j < values[0].length; j++) {
					for(int k = 0 ;k<columns.length;k++) {
						if(columns[k].equalsIgnoreCase(allcolumns[j])) {
							returnedvalues[i][c] = values[i][j];
							c++;
						}
					}
					
				}

				if (c != columns.length) {
					return null;
				}
			}
			return returnedvalues;
		} else if (columns == null && conditions != null) {
			manipulation = new ConditionsManipulation(values, conditions, allcolumns, allTypes);
			try {
				return manipulation.getArrayAfterCondiotions();
			} catch (Exception e) {
				return null;
			}
		} else if (columns != null && conditions != null) {
			manipulation = new ConditionsManipulation(values, conditions, allcolumns, allTypes);
			try {
				Object [][] filteredValues= manipulation.getArrayAfterCondiotions();
				columnsManipulation = new ColumnsManipulation(filteredValues, allcolumns, columns);
				return columnsManipulation.getSelectedColumns();
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}

}
