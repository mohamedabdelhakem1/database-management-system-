package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.Command;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.MyDatabase;

public class UpdateQuery implements Command {
	Database database;
	
	public UpdateQuery() {
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
