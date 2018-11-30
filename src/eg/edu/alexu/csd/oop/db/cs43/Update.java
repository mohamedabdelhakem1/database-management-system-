package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;
import com.sun.rowset.internal.Row;
import com.sun.xml.internal.ws.util.StringUtils;

import eg.edu.alexu.csd.oop.db.cs43.concreteclass.ExecuteUpdateQueryCommad;

public class Update implements ExecuteUpdateQueryCommad {
	private File database;
	private String[] columns;
	private String[] conditions;
	private String[] values;
	
	private Object[][] Storedvalues;
	
	private String[] allcolumns;
	private String[] allTypes;
	private ConditionsManipulation manipulation;

	private File tablefolder;
	private Map<String, Object> map;
	public Update(File database, String[] columns, String[] conditions, String[] values, String tablename) throws SQLException {
		this.columns = columns;
		this.conditions = conditions;
		this.database = database;
		tablefolder = new File(database.getAbsolutePath() + System.getProperty("file.separator") + tablename);
		this.values = values;
		//
		DataBaseBufferPool pool = DataBaseBufferPool.getInstance();
		XMLData xml = pool.getTable(database, tablename);
		
		 try {
			map = xml.getXml();
		} catch (NullPointerException e) {
			throw new SQLException();
		}
		//
		Storedvalues = (Object[][]) map.get("array");
		
		allcolumns = (String[]) map.get("columns");
		allTypes = (String[]) map.get("types");

		new LinkedList<>();
		Arrays.asList(allcolumns);
	}

	@Override
	public int execute() throws SQLException {

		if (Storedvalues == null) {
			throw new SQLException();
		}
		if (conditions == null) {
			// affect all the rows

			for (int i = 0; i < Storedvalues.length; i++) {
				int c = 0;
				for (int j = 0; j < Storedvalues[0].length; j++) {
					for (int k = 0; k < columns.length; k++) {
						if (columns[k].equalsIgnoreCase(allcolumns[j])) {
							if (allTypes[j].equalsIgnoreCase("string")) {
								if (values[k].startsWith("'") && values[k].endsWith("'")) {
									Storedvalues[i][j] = values[k];
									c++;
								} else {
									return 0;
								}
							} else if (allTypes[j].equalsIgnoreCase("integer")) {
								if ((!(values[k].startsWith("'"))) && (!values[k].endsWith("'"))) {
									Storedvalues[i][j] = Integer.valueOf(values[k]);
									c++;
								} else {
									return 0;
								}
							}

						}
					}
				}
				if (c != columns.length) {
					return 0;
				}
			}
			
			try {
				map.put("array", Storedvalues);
			} catch (Exception e) {
				return 0;
			}
			return Storedvalues.length;

		} else if (conditions != null) {

			manipulation = new ConditionsManipulation(Storedvalues, conditions, allcolumns, allTypes);
			Object[][] RowsTobeAffected = new Object[0][0];
			try {

				RowsTobeAffected = manipulation.getArrayAfterCondiotions();
				if (RowsTobeAffected == null) {
					return 0;
				}
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
				if (count == Storedvalues[0].length) {
					int c = 0;
					for (int j = 0; j < Storedvalues[0].length; j++) {
						for (int k = 0; k < columns.length; k++) {
							if (columns[k].equalsIgnoreCase(allcolumns[j])) {
								if (allTypes[j].equalsIgnoreCase("string")) {
									if (values[k].startsWith("'") && values[k].endsWith("'")) {
										Storedvalues[i][j] = values[k];
										c++;
									} else {
										return 0;
									}
								} else if (allTypes[j].equalsIgnoreCase("integer")) {
									if ((!(values[k].startsWith("'"))) && (!values[k].endsWith("'"))) {
										Storedvalues[i][j] = values[k];
										c++;
									} else {
										return 0;
									}
								}

							}
						}
					}
					if (c != columns.length) {
						return 0;
					}
				}
			}
			map.put("array", Storedvalues);
			
			return countAffectedRows;
		}
		return 0;
	}

}
