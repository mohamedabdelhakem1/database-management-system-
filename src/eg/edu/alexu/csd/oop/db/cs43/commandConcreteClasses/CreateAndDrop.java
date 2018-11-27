package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.Command;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.MyDatabase;

public class CreateAndDrop implements Command {
	private Database database;
	public CreateAndDrop() {
		database = MyDatabase.getInstance();
	}
	@Override
	public void execute(String s) {
	try {
		database.executeStructureQuery(s);
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	}
}
