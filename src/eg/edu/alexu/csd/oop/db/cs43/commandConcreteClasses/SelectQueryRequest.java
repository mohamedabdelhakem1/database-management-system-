package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.sql.SQLException;

import javax.management.QueryExp;

import eg.edu.alexu.csd.oop.db.Database;
import javafx.scene.control.TextArea;
import sun.security.jca.GetInstance;

public class SelectQueryRequest implements CommandRequestStrategy {
	private Database database;

	public SelectQueryRequest(Database database) {
		this.database = database;
	}

	@Override
	public void getSpecifiedRequest(TextArea textArea, String command) {
		try {
			textArea.setText("");
			Object[][] output = database.executeQuery(command);
			for (int i = 0; i < output.length; i++) {
				for (int j = 0; j < output[0].length; j++) {
					
					textArea.appendText((String) output[i][j]); 
					textArea.appendText("\t");
				}
				textArea.appendText("\n");
			}
		} catch (SQLException e) {
			textArea.setText("invalid input");
		}

	}

}
