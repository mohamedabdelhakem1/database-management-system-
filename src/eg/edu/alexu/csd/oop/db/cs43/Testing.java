package eg.edu.alexu.csd.oop.db.cs43;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses.ClientCommand;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.CommandFactory;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.MyDatabase;

public class Testing {

	public static void main(String[] args) {

		Database database = MyDatabase.getInstance();
		database.createDatabase("sample" + System.getProperty("file.separator") + "testDB"
				, false);

		String s = "INSERT INTO  table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'valudsdes', 4)";
		try {
			database.executeUpdateQuery(s);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
