package eg.edu.alexu.csd.oop.db.cs43.concreteclass;

import java.io.File;
import java.sql.SQLException;


import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.CommandsParser;
import eg.edu.alexu.csd.oop.db.cs43.ExecuteStructureQuerys;

public class MyDatabase implements Database {
	private CommandsParser commandsParser;
	private File dataBaseFile;
	private static Database database = null;

	public MyDatabase() {
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
		dataBaseFile = new File(databaseName);

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
		return dataBaseFile.getAbsolutePath();

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		ExecuteStructureQuerys executeStructureQuerys = new ExecuteStructureQuerys();
		executeStructureQuerys.setDataBaseFile(dataBaseFile);

		commandsParser.validateCommand(query);
		int queryNo = commandsParser.getQueryNo();
		if (queryNo == 4) {


			return executeStructureQuerys.createDataBase();


		} else if (queryNo == 5) {

	
			executeStructureQuerys.setTableName(commandsParser.getTableName());
			executeStructureQuerys.setColumnsnames(commandsParser.getColumns());
			executeStructureQuerys.setColumnsTypes(commandsParser.getTypes());
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			String[] types = commandsParser.getTypes();
		

			try {
				return executeStructureQuerys.createTable();
			} catch (Exception e) {
			}
		} else if (queryNo == 6) {
			return executeStructureQuerys.dropDataBase();
		} else if (queryNo == 7) {

			executeStructureQuerys.setTableName(commandsParser.getTableName());
			return executeStructureQuerys.dropTable();


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
			


		} else if (commandsParser.getQueryNo() == 2) { // update
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			String[] conditions = commandsParser.getconditions();
			String[] values = commandsParser.getValues();
			update(tablename, columns, conditions, values);
			
		} else if (commandsParser.getQueryNo() == 3) { // delete
			String tablename = commandsParser.getTableName();
			String[] columns = commandsParser.getColumns();
			String[] conditions = commandsParser.getconditions();
			String[] values = commandsParser.getValues();
			


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


	private Object[][] SelectColumns(String tablename, String[] columns, String[] conditions) {

		return null;

	}
/*
	private boolean dropTable(String string) {
		int count = 0;
		File[] files = dataBaseFile.listFiles();
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
*/
}
