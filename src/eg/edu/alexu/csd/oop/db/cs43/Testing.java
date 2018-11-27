package eg.edu.alexu.csd.oop.db.cs43;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses.ClientCommand;
import eg.edu.alexu.csd.oop.db.cs43.interfaces.CommandFactory;

public class Testing {

	public static void main(String[] args) {
	Database  database = MyDatabase.getInstance();
	String s =  "UPDATE table_name2 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3 ='333333333' WHERE coLUmn_NAME2=4";
			try {
		database.executeUpdateQuery(s);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
	}
}
