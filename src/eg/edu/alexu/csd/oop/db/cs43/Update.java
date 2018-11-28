package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.sun.rowset.internal.Row;
import com.sun.xml.internal.ws.util.StringUtils;

import eg.edu.alexu.csd.oop.db.cs43.concreteclass.ExecuteUpdateQuery;

public class Update implements ExecuteUpdateQuery {
	private File tableFolder;
	private String[] columns;
	private String[] conditions;
	private String[] values;
	private ReadXml readXml;
	private Object[][] Storedvalues;
	private XSDReader reader;
	private String[] allcolumns;
	private String[] allTypes;
	private ConditionsManipulation manipulation;
	private WriteXml writeXml;

	public Update(File tableFolder, String[] columns, String[] conditions, String[] values) {
		this.columns = columns;
		this.conditions = conditions;
		this.tableFolder = tableFolder;
		this.values = values;
		readXml = new ReadXml();
		try {
			Storedvalues = readXml.getArray(new File(tableFolder.getAbsolutePath()
					+ System.getProperty("file.separator") + tableFolder.getName() + ".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		reader = new XSDReader();
		reader.ReadXSD(
				tableFolder.getAbsolutePath() + System.getProperty("file.separator") + tableFolder.getName() + ".xsd");
		allcolumns = reader.getColumns();
		allTypes = reader.getTypes();

		new LinkedList<>();
		Arrays.asList(allcolumns);
	}

	public int execute() {

		if (conditions == null) {
			// affect all the rows

			for (int i = 0; i < Storedvalues.length; i++) {
				int c = 0;
				for (int j = 0; j < Storedvalues[0].length; j++) {
					for (int k = 0; k < columns.length; k++) {
						if (columns[k].equalsIgnoreCase(allcolumns[j])) {
							Storedvalues[i][j] = values[k];
							c++;
						}
					}
				}
				if (c != columns.length) {
					return 0;
				}
			}
			writeXml = new WriteXml();
			try {
				writeXml.writeTable(Storedvalues, allcolumns, tableFolder);
			} catch (Exception e) {
				return 0;
			}
			return Storedvalues.length;

		} else if (conditions != null) {

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
				if (count == Storedvalues[0].length) {
					int c = 0;
					for (int j = 0; j < Storedvalues[0].length; j++) {
						for (int k = 0; k < columns.length; k++) {
							if (columns[k].equalsIgnoreCase(allcolumns[j])) {
								Storedvalues[i][j] = values[k];
								c++;
							}
						}
					}
					if (c != columns.length) {
						return 0;
					}
				}
			}
			writeXml = new WriteXml();
			try {
				writeXml.writeTable(Storedvalues, allcolumns, tableFolder);
			} catch (Exception e) {
				return 0;
			}
			return countAffectedRows;
		}
		return 0;
	}

}
