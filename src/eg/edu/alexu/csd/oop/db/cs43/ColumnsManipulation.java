package eg.edu.alexu.csd.oop.db.cs43;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ColumnsManipulation {
	private String[] allcolumns;
	private Object[][] Filteredvalues;
	private String[] selectedColumns;

	public ColumnsManipulation(Object[][] Filteredvalues, String[] allcolumns, String[] selectedColumns) {
		this.Filteredvalues = Filteredvalues;
		this.allcolumns = allcolumns;
		this.selectedColumns = selectedColumns;
	}
	public Object[][] getSelectedColumns() throws Exception{
		List< String > temp = new LinkedList<>();
		temp = Arrays.asList(selectedColumns);
		Object[][] returnedvalues = new Object[Filteredvalues.length][selectedColumns.length];
		for (int i = 0; i < Filteredvalues.length; i++) {
			int c = 0;
			for (int j = 0; j < Filteredvalues[0].length; j++) {
				if (temp.contains(allcolumns[j])) {
					returnedvalues[i][c] = Filteredvalues[i][j];
					c++;
				}
			}
			if (c != temp.size()) {
				throw new Exception();
			}
		}
		
		return returnedvalues;
	}
	
	
	
}
