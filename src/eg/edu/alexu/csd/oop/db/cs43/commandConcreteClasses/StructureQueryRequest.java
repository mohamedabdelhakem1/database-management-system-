package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;
import javafx.scene.control.TextArea;

public class StructureQueryRequest implements CommandRequestStrategy {
	private Database database;
	public StructureQueryRequest(Database database) {
		this.database = database;
	}

	@Override
	public void getSpecifiedRequest(TextArea textArea, String command) {
		try {
			boolean b = database.executeStructureQuery(command);
			if(b) {
				textArea.setText("command successed");
			}else {
				textArea.setText("command failed");
			}
		} catch (SQLException e) {
			textArea.setText("invalid input");
		}


	}

}
