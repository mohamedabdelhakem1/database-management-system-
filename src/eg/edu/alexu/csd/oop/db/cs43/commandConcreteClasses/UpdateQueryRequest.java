package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;
import javafx.scene.control.TextArea;

public class UpdateQueryRequest implements CommandRequestStrategy {
	private Database database;

	public UpdateQueryRequest(Database database) {
		this.database = database;
	}

	@Override
	public void getSpecifiedRequest(TextArea textArea, String command) {
		try {
			int i = database.executeUpdateQuery(command);
				textArea.setText("number of rows affected "+ String.valueOf(i));
			
		} catch (SQLException e) {
			textArea.setText("invalid input");
		}


	}

}
