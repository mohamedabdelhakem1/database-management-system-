package eg.edu.alexu.csd.oop.db.cs43.concreteclass;

import java.io.File;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;



import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.CommandsParser;
import eg.edu.alexu.csd.oop.db.cs43.DataBaseBufferPool;
import eg.edu.alexu.csd.oop.db.cs43.Delete;
import eg.edu.alexu.csd.oop.db.cs43.ExecuteQuery;
import eg.edu.alexu.csd.oop.db.cs43.ExecuteStructureQuerys;

import eg.edu.alexu.csd.oop.db.cs43.Insert;
import eg.edu.alexu.csd.oop.db.cs43.Update;
import eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses.Singleton;

public class MyDatabase implements Database, Singleton {
	private CommandsParser commandsParser;
	private File dataBaseFile;
	private static Database database = null;
	private boolean open = false;
	private ExecuteUpdateQueryCommad executeUpdateQuery;
	private ExecuteStructureQuerys executeStructureQuerys;
	private ExecuteQueryCommand executeQuery;

	public MyDatabase() {
		DataBaseBufferPool pool = DataBaseBufferPool.getInstance();
		try {
			pool.unloadCache();
			pool.destroy();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		dataBaseFile = new File(databaseName.toLowerCase());

		if (dropIfExists) {
			open = false;
			try {
				executeStructureQuery("drop database " + databaseName.toLowerCase());
			} catch (SQLException e) {
			}
			try {
				executeStructureQuery("create database " + databaseName.toLowerCase());
			} catch (SQLException e) {
			}

		} else {
			if (dataBaseFile.exists()) {
				open = true;
			}
			try {
				executeStructureQuery("create database " + databaseName.toLowerCase());
			} catch (SQLException e) {
			}
		}

		return dataBaseFile.getAbsolutePath();

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		executeStructureQuerys = new ExecuteStructureQuerys();
		commandsParser.validateCommand(query);

		executeStructureQuerys.setDataBaseFile(dataBaseFile);
		int queryNo = commandsParser.getQueryNo();
		if (queryNo == 4) {
			if (open) {
				return true;
			}
			if (dataBaseFile == null) {
				dataBaseFile = new File(commandsParser.getTableNameOrDatabase().toLowerCase());
				executeStructureQuerys.setDataBaseFile(dataBaseFile);
			}
			return executeStructureQuerys.createDataBase();
		} else if (queryNo == 5) {
			executeStructureQuerys.setTableName(commandsParser.getTableNameOrDatabase().toLowerCase());
			executeStructureQuerys.setColumnsnames(commandsParser.getColumns());
			executeStructureQuerys.setColumnsTypes(commandsParser.getTypes());

			return executeStructureQuerys.createTable();
		} else if (queryNo == 6) {

			dataBaseFile = new File(commandsParser.getTableNameOrDatabase().toLowerCase());
			executeStructureQuerys.setDataBaseFile(dataBaseFile);
			return executeStructureQuerys.dropDataBase();
		} else if (queryNo == 7) {
			executeStructureQuerys.setTableName(commandsParser.getTableNameOrDatabase().toLowerCase());
			return executeStructureQuerys.dropTable();
		}

		throw new SQLException();

	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		commandsParser.validateCommand(query);
		if (dataBaseFile == null) {
			throw new SQLException();
		}
		if (commandsParser.getQueryNo() == 15) {
			String tablename = commandsParser.getTableNameOrDatabase().toLowerCase();
			String[] columns = commandsParser.getColumns();
			String[] conditions = commandsParser.getconditions();

			return SelectColumns(tablename, columns, conditions);
		}
		throw new SQLException();
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {

		commandsParser.validateCommand(query);

		if (dataBaseFile == null) {
			throw new SQLException();
		}
		if (commandsParser.getQueryNo() == 1) { // insert

			executeUpdateQuery = new Insert(commandsParser.getValues(), dataBaseFile,
					commandsParser.getTableNameOrDatabase().toLowerCase(), commandsParser.getColumns());

			try {
				return executeUpdateQuery.execute();

			} catch (Exception e) {

				e.printStackTrace();
				throw new SQLException();
			}

		} else if (commandsParser.getQueryNo() == 2) { // update

			String tablename = commandsParser.getTableNameOrDatabase().toLowerCase();
			String[] columns = commandsParser.getColumns();
			String[] conditions = commandsParser.getconditions();
			String[] values = commandsParser.getValues();
			executeUpdateQuery = new Update(dataBaseFile, columns, conditions, values, tablename);

			return executeUpdateQuery.execute();

		} else if (commandsParser.getQueryNo() == 3) { // delete
			String tablename = commandsParser.getTableNameOrDatabase().toLowerCase();
			String[] columns = commandsParser.getColumns();
			String[] conditions = commandsParser.getconditions();

			executeUpdateQuery = new Delete(dataBaseFile, columns, conditions, tablename);
			try {
				return executeUpdateQuery.execute();
			} catch (Exception e) {
				return 0;
			}
		}

		throw new SQLException();
	}

	private Object[][] SelectColumns(String tablename, String[] columns, String[] conditions) throws SQLException {
		executeQuery = new ExecuteQuery(dataBaseFile, columns, conditions, tablename);
		return executeQuery.execute();

	}

	/*
	 * private boolean dropTable(String string) { int count = 0; File[] files =
	 * dataBaseFile.listFiles(); for (int i = 0; i < files.length; i++) { if
	 * (files[i].getName().contains(string)) { files[i].delete(); count++; } } if
	 * (count == 2) { return true; } return false; }
	 */
}
