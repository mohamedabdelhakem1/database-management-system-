package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.sql.SQLException;
import java.util.regex.Pattern;

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
			boolean b = false;
			String pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)";
			Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			String[] strs = pat.split(command.trim()); // removed trailing and leading spaces
			if (strs[0].equalsIgnoreCase("create")) {
				if (strs[1].equalsIgnoreCase("database")) {
					if(strs.length == 3) {
						database.createDatabase(strs[2], false);	
						b = true;
					}
				}else {
					b = database.executeStructureQuery(command);
				}
			}else {
				b = database.executeStructureQuery(command);
			}

			if (b) {
				textArea.setText("command successed");
			} else {
				textArea.setText("command failed");
			}
		} catch (

		SQLException e) {
			textArea.setText("invalid input");
		}

	}

}
