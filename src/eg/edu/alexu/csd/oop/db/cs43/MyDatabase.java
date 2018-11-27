package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import eg.edu.alexu.csd.oop.db.Database;

public class MyDatabase implements Database {
	private CommandsParser commandsParser;
	private File file;
	private static Database database = null;
	private String currentDataBase = null;

	private MyDatabase() {
		commandsParser = new CommandsParser();
	}

	public static Database getInstance() {
		if (database == null) {
			database = new MyDatabase();
		}
		return database;

	}

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		file = new File(databaseName);

		if (dropIfExists) {
			try {
				executeStructureQuery("drop database " + databaseName);
			} catch (SQLException e) {

			}
		}
		try {
			executeStructureQuery("create database " + databaseName);
		} catch (SQLException e) {

		}

		return file.getAbsolutePath();

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		commandsParser.validateCommand(query);
		int queryNo = commandsParser.getQueryNo();
		if (queryNo == 4) {
			
			return file.mkdirs();
		} else if (queryNo == 5) {
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			String[] types = commandsParser.getTypes();
			return createTable(tablename, columns, types);

		} else if (queryNo == 6) {
			return file.delete();
		} else if (queryNo == 7) {
			String tablename = commandsParser.getTableName();
			
			return dropTable(tablename);

		}

		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		commandsParser.validateCommand(query);
		if (commandsParser.getQueryNo() == 15) {
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			
			String[] conditions = commandsParser.getconditions();
			System.out.println(tablename);
			
			try {
				for(String s : columns) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			
			try {
				for(String s : conditions) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			return SelectColumns(tablename, columns, conditions);
		}
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {

		commandsParser.validateCommand(query);
		if (commandsParser.getQueryNo() == 1) { // insert
			
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			String[] values = commandsParser.getValues();
			insert(tablename, columns, values);
			System.out.println(tablename);
			try {
				for(String s : columns) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			try {
				for(String s : values) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			
		
			
		} else if (commandsParser.getQueryNo() == 2) { // update
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			String[] conditions = commandsParser.getconditions();
			String[] values = commandsParser.getValues();
			update(tablename, columns, conditions, values);
			System.out.println(tablename);
			try {
				for(String s : columns) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			try {
				for(String s : values) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			
			try {
				for(String s : conditions) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			
		} else if (commandsParser.getQueryNo() == 3) { // delete
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			String[] conditions = commandsParser.getconditions();
			String[] values = commandsParser.getValues();
			System.out.println(tablename);
			try {
				for(String s : columns) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			try {
				for(String s : values) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			
			try {
				for(String s : conditions) {
					System.out.println(s);
				}
			} catch (Exception e) {
				
			}
			
			delete(tablename, columns, conditions, values);
		}

		
		return 0;
	}

	private void delete(String tablename, String[] columns, String[] conditions, String[] values) {
		// TODO Auto-generated method stub

	}

	private void update(String tablename, String[] columns, String[] conditions, String[] values) {
		// TODO Auto-generated method stub

	}

	private void insert(String tablename, String[] columns, String[] values) {
		// TODO Auto-generated method stub

	}

	private boolean createTable(String string, String[] columns, String[] types) {
		// TODO Auto-generated method stub
		return false;
	}

	private Object[][] SelectColumns(String tablename, String[] columns, String[] conditions) {

		return null;

	}

	private boolean dropTable(String string) {
		int count = 0;
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(string)) {
				files[i].delete();
				count++;
			}
		}
		if (count == 2) {
			return true;
		}
		return false;
	}

}
