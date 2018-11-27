package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;

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
		String[] commandWords = commandsParser.validateCommand(query);

		if (commandWords[0].equalsIgnoreCase("create")) {
			if (commandWords[1].equalsIgnoreCase("database")) {
				return file.mkdirs();
			} else if (commandWords[1].equalsIgnoreCase("table")) {
				int numOfColumns = (commandWords.length - 3) / 2;
				String[] columns = new String[numOfColumns];
				String[] types = new String[numOfColumns];
				int counter = 0;
				for (int i = 3; i < commandWords.length; i = i + 2) {
					columns[counter] = commandWords[i];
					counter++;
				}
				counter = 0;
				for (int i = 4; i < commandWords.length; i = i + 2) {
					types[counter] = commandWords[i];
				}
				
				try {
					createTable(commandWords[2], columns, types);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return true;
			}
		} else if (commandWords[0].equalsIgnoreCase("drop")) {
			if (commandWords[1].equalsIgnoreCase("database")) {
				return file.delete();
			} else if (commandWords[1].equalsIgnoreCase("table")) {
				dropTable(commandWords[2]);
				return true;
			}

		}

		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		String[] commandWords = commandsParser.validateCommand(query);
		String tablename;
		if (commandWords[0].equalsIgnoreCase("select")) {
			if (commandWords[1].equalsIgnoreCase("*")) {
				if (commandWords[2].equalsIgnoreCase("from")) {
					tablename = commandWords[3];
					if (commandWords.length == 4) {
						return SelectColumns(null, null);
						// return all columns
					} else if (commandWords[4].equalsIgnoreCase("where")) {
						String[] conditions = commandsParser.getconditions();
						return SelectColumns(null, conditions);
						// return columns with conditions
					}
				}
			} else {
				String[] columns = commandsParser.getColumns();
				int i = 1 + columns.length;

				if (commandWords[i].equalsIgnoreCase("from")) {
					tablename = commandWords[i + 1];
					if (commandWords.length == i + 2) {
						return SelectColumns(columns, null);
						// return Coloumns in columns array
					} else if (commandWords[i + 2].equalsIgnoreCase("where")) {
						String[] conditions = commandsParser.getconditions();
						return SelectColumns(columns, conditions);
					}
				}

			}
		}
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		
		return 0;
	}

	private boolean createTable(String tableName, String[] columns, String[] types) throws Exception {
		 createTable obj = new createTable(tableName, columns, types, file.getAbsolutePath());
		 return obj.creatMytable();
	
	}

	private Object[][] SelectColumns(String[] columns, String[] conditions) {

		return null;

	}

	private boolean dropTable(String string) {
		return false;
	}

}
