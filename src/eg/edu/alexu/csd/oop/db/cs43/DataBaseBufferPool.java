package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SingleSelectionModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses.Singleton;
import javafx.scene.AmbientLight;

public class DataBaseBufferPool implements Singleton {
	private static DataBaseBufferPool pool;
	private static Map<File, DatabaseData> DatabaseCache;
	private static int count = 0;
	private static Thread thread ;
	private static long startTime ;
	private DataBaseBufferPool() {
		startTime = System.currentTimeMillis();
	}

	public static DataBaseBufferPool getInstance() {
		if (pool == null) {
			pool = new DataBaseBufferPool();
			DatabaseCache = new HashMap<>();
			Runnable runnable = new Runnable() {
				public void run() {
					while (true) {
						if (System.currentTimeMillis() - startTime >= 50000) {
							startTime = System.currentTimeMillis();
							DataBaseBufferPool pool = DataBaseBufferPool.getInstance();
							try {
								pool.unloadCache();

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				}
			};
			thread = new Thread(runnable);
			thread.start();
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
		thread.stop();
		pool = null;
		DatabaseCache = null;
	}
}
