package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.db.cs43.concreteclass.ExecuteUpdateQueryCommad;

public class Delete implements ExecuteUpdateQueryCommad {
	private File database;
	private String[] columns;
	private String[] conditions;

	
	private Object[][] Storedvalues;
	
	private String[] allcolumns;
	private String[] allTypes;
	private ConditionsManipulation manipulation;
	
	private File tablefolder;
	private Map<String, Object> map;
	public Delete(File database, String[] columns, String[] conditions, String tablename) {
		this.columns = columns;
		this.conditions = conditions;
		this.database = database;
		tablefolder = new File(database.getAbsolutePath() + System.getProperty("file.separator")
					+ tablename );
		DataBaseBufferPool pool = DataBaseBufferPool.getInstance();
		XMLData xml = pool.getTable(database, tablename);
		map = xml.getXml();
		
		
			Storedvalues = (Object[][]) map.get("array");

		
		allcolumns = (String[]) map.get("columns");
		allTypes = (String[]) map.get("types");

	}
	@Override
	public int execute() {
		if (conditions == null) {
		//	System.out.println("null");
			map.put("array", new Object[0][0]);
			Object[][] o = (Object[][]) map.get("array");
			for (int i = 0; i < o.length; i++) {
				for (int j = 0; j < o[0].length; j++) {
					System.out.println(o[i][j]);
				}
			}
			System.out.println("");
			return Storedvalues.length;

		} else if (conditions != null) {

			List<Object[]> newRows = new LinkedList<>();
			manipulation = new ConditionsManipulation(Storedvalues, conditions, allcolumns, allTypes);

			Object[][] RowsTobeAffected = new Object[0][0];
			try {
				RowsTobeAffected = manipulation.getArrayAfterCondiotions();

			} catch (Exception e) {

			}
			int countAffectedRows = 0;

			for (int i = 0; i < Storedvalues.length; i++) {
				int count = 0;
				for (int k = 0; k < RowsTobeAffected.length; k++) {
					count = 0;

					for (int j = 0; j < Storedvalues[0].length; j++) {
						if (RowsTobeAffected[k][j].equals(Storedvalues[i][j])) {
							count++;

						}
					}
					if (count == Storedvalues[0].length) {
						countAffectedRows++;

						break;
					}
				}
				if (count != Storedvalues[0].length) {
					newRows.add(Storedvalues[i]);
					
				}
			}

			Object[][] returnedRows;
			try {
				returnedRows = new Object[newRows.size()][newRows.get(0).length];
				returnedRows = newRows.toArray(returnedRows);

			} catch (Exception e1) {
				returnedRows = null;
			}
			map.put("array", returnedRows);
			
			
			
		
			return countAffectedRows;
		}
		return 0;

	}
}
