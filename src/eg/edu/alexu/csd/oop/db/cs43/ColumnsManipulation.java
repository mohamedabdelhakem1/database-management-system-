package eg.edu.alexu.csd.oop.db.cs43;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ColumnsManipulation {
	private String[] allcolumns;
	private Object[][] Filteredvalues;
	private String[] selectedColumns;
	private String[] alltypes;

	public ColumnsManipulation(Object[][] Filteredvalues, String[] allcolumns, String[] selectedColumns,
			String[] alltypes) {
		this.Filteredvalues = Filteredvalues;
		this.allcolumns = allcolumns;
		this.selectedColumns = selectedColumns;
		this.alltypes = alltypes;
	}

	public Object[][] getSelectedColumns() throws Exception {

		Object[][] returnedvalues = new Object[Filteredvalues.length][selectedColumns.length];
		for (int i = 0; i < Filteredvalues.length; i++) {
			int c = 0;
			for (int j = 0; j < Filteredvalues[0].length; j++) {
				for (int k = 0; k < selectedColumns.length; k++) {
					if (selectedColumns[k].equalsIgnoreCase(allcolumns[j])) {
						if(alltypes[j].equalsIgnoreCase("integer")) {
							returnedvalues[i][c] = Integer.valueOf(String.valueOf(Filteredvalues[i][j]));
							c++;
						}else if(alltypes[j].equalsIgnoreCase("string")) {
							returnedvalues[i][c] = Filteredvalues[i][j];
							c++;
						}
						
					}
				}
			}
			if (c != selectedColumns.length) {
				throw new Exception();
			}
		}

		return returnedvalues;
	}

}
