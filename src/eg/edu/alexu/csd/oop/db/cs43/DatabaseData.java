package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseData {
	private Map<String, XMLData> databaseTables;
	private Map<String, Long> TablesLifeTime;
	private File database;

	public DatabaseData(File name) {
		database = name;
		databaseTables = new HashMap<String, XMLData>();
		TablesLifeTime = new HashMap<String, Long>();

	}

	private void loadTable(String tableName) { // loads the existed tables at creating instance of these database
		// and can be called when creating a new table
		File[] tables = database.listFiles();
		for (int i = 0; i < tables.length; i++) {
			if (tables[i].getName().equalsIgnoreCase(tableName)) {
				XMLData data = new XMLData(database, tables[i].getName());
				try {
					data.loadXml();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				databaseTables.put(tables[i].getName(), data);

				break;
			}
		}

	}

	public XMLData getTable(String tableName) {
		if (databaseTables.get(tableName) == null) {
			loadTable(tableName);
		}
		TablesLifeTime.put(tableName, System.currentTimeMillis());

		return databaseTables.get(tableName);
	}

	public Map<String, XMLData> getTables() {
		return databaseTables;
	}

	public Map<String, Long> getTablesLifetime() {
		return TablesLifeTime;
	}
}
