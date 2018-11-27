package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.MyDatabase;
import eg.edu.alexu.csd.oop.db.cs43.interfaces.Command;

public class Update implements Command {
	Database database;
	
	public Update() {
		database = MyDatabase.getInstance();
	}
	@Override
	public void execute(String s) {
		try {
			database.executeUpdateQuery(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}