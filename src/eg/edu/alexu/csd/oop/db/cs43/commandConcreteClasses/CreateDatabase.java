package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.Command;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.MyDatabase;

public class CreateDatabase implements Command {
	private Database database;
	private boolean dropIfExists;

	public CreateDatabase(boolean dropIfExists) {
		database = MyDatabase.getInstance();
		this.dropIfExists = dropIfExists;
	}

	@Override
	public void execute(String s) {

	}

}