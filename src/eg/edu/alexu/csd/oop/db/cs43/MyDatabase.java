package eg.edu.alexu.csd.oop.db.cs43;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.db.Database;

public class MyDatabase implements Database {
	private CommandsParser commandsParser;

	public MyDatabase() {
		commandsParser = new CommandsParser();
	}

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		if (dropIfExists) {
			try {
				executeQuery("create database " + databaseName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				executeQuery("drop database " + databaseName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		String[] commandWords = commandsParser.validateCommand(query);
		if (commandWords[0].equalsIgnoreCase("create")) {
			if (commandWords[1].equalsIgnoreCase("database")) {
				createDatabase(commandWords[2]);
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
				createTable(commandWords[2], columns, types);
			}
		} else if (commandWords[0].equalsIgnoreCase("drop")) {
			if (commandWords[1].equalsIgnoreCase("database")) {
				dropDatabase(commandWords[2]);
			} else if (commandWords[1].equalsIgnoreCase("table")) {
				dropTable(commandWords[2]);
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
					if (commandWords[4].equalsIgnoreCase("where")) {
						//return columns with coditions
					}else if (commandWords.length == 4) {
						//return all columns
					}
				}
			} else {
				List<String> columns = new LinkedList<>();
				int i;
				for (i = 1; !commandWords[i].equalsIgnoreCase("from"); i++) {
					columns.add(commandWords[i]);
				}

				if (commandWords[i].equalsIgnoreCase("from")) {
					tablename = commandWords[i + 1];
					if (commandWords[i + 2].equalsIgnoreCase("where")) {
						//return columns with coditions
					}else if(commandWords.length == i+2) {
						//return columns in array
					}
				}

			}
		}
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean createDatabase(String string) {
		return false;
	}

	private boolean createTable(String string, String[] columns, String[] types) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean dropTable(String string) {

		return false;
	}

	private boolean dropDatabase(String string) {
		// TODO Auto-generated method stub
		return false;
	}

}
