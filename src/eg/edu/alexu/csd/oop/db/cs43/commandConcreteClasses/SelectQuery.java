package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.MyDatabase;
import eg.edu.alexu.csd.oop.db.cs43.interfaces.Command;

public class SelectQuery implements Command {
	private Database database;
	public SelectQuery() {
		database = MyDatabase.getInstance();
	}
	@Override
	public void execute(String s) {
		try {
			database.executeQuery(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
