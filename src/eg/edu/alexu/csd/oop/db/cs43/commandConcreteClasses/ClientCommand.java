package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.util.LinkedList;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.DataBaseBufferPool;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.CommandFactory;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.MyDatabase;
import javafx.scene.control.TextArea;

public class ClientCommand implements CommandFactory {
	private String command;

	private boolean dropIfExists;
	private Database database;
	private String databaseName;
	private static int countUpdates = 0;
	private CommandRequestStrategy strategy;
	DataBaseBufferPool pool;

	@Override
	public void setcommand(String command, boolean drop, String databasename) {
		this.dropIfExists = drop;
		this.command = command;
		database = MyDatabase.getInstance();
		this.databaseName = databasename;
		database = MyDatabase.getInstance();
		pool = DataBaseBufferPool.getInstance();
	}

	@Override
	public void getFunction(TextArea textArea) throws Exception {
		String pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)";
		Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		String[] strs = pat.split(command.trim()); // removed trailing and leading spaces

		
		if (strs[0].equalsIgnoreCase("create")) {
			if (strs[1].equalsIgnoreCase("database")) {
				strategy = new StructureQueryRequest(database);
				strategy.getSpecifiedRequest(textArea, command);
			} else if (strs[1].equalsIgnoreCase("table")) {
				strategy = new StructureQueryRequest(database);
				strategy.getSpecifiedRequest(textArea, command);
				System.out.println();
			}
		} else if (strs[0].equalsIgnoreCase("drop")) {
			strategy = new StructureQueryRequest(database);
			strategy.getSpecifiedRequest(textArea, command);
		} else if (strs[0].equalsIgnoreCase("delete") || strs[0].equalsIgnoreCase("insert")
				|| (strs[0].equalsIgnoreCase("update"))) {

		//	countUpdates++;
			strategy = new UpdateQueryRequest(database);
			strategy.getSpecifiedRequest(textArea, command);
			

		} else if (strs[0].equalsIgnoreCase("select")) {
			strategy = new SelectQueryRequest(database);
			strategy.getSpecifiedRequest(textArea, command);
		}

	}

}
