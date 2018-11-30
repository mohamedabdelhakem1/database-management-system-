package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eg.edu.alexu.csd.oop.db.Database;
import javafx.scene.AmbientLight;

public class DataBaseBufferPool {
	private static DataBaseBufferPool pool;
	private static Map<File, DatabaseData> DatabaseCache;
	private static int count = 0;

	private DataBaseBufferPool() {

	}

	public static DataBaseBufferPool getInstance() {
		if (pool == null) {
			pool = new DataBaseBufferPool();
			DatabaseCache = new HashMap<>();
		}
		return pool;
	}

	public XMLData getTable(File databasename, String TableName) {
		
		DatabaseData data;
		if (DatabaseCache.get(databasename) == null) {
			data = new DatabaseData(databasename);
			DatabaseCache.put(databasename, data);
			Map<String, XMLData> xmls = data.getTables();
			return xmls.get(TableName);
		} else {
			data = DatabaseCache.get(databasename);
			Map<String, XMLData> xmls = data.getTables();
			return xmls.get(TableName);
		}

	}

	public void unloadCache() throws SQLException {
		System.out.println(DatabaseCache.size());
		for (Entry<File, DatabaseData> e : DatabaseCache.entrySet()) {
			DatabaseData databaseData = e.getValue();
			Map<String, XMLData> xmls = databaseData.getTables();
			for (Entry<String, XMLData> en : xmls.entrySet()) {
				XMLData xmlData = en.getValue();
				xmlData.SaveXml();

			}
		}
	
	}
	public void destroy() {
		pool = null;
		DatabaseCache = null;
	}
}
