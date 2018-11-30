package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseData {
	private Map<String, XMLData> databaseTables;
	private File database;

	public DatabaseData(File name) {
		database = name;
		databaseTables = new HashMap<String, XMLData>();
		loadTables();
	}

	public void loadTables() { // loads the existed tables at creating instance of these database 
								// and can be called when creating a new table
		File[] tables = database.listFiles();
		for (int i = 0; i < tables.length; i++) {
			if (databaseTables.get(tables[i].getName()) == null) {
				XMLData data = new XMLData(database, tables[i].getName());
				try {
					data.loadXml();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				databaseTables.put(tables[i].getName(), data);
			}
		}

	}

	public Map<String, XMLData> getTables() {
		loadTables();
		return databaseTables;
	}

}
