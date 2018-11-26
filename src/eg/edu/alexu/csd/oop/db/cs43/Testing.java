package eg.edu.alexu.csd.oop.db.cs43;

import java.util.Scanner;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses.ClientCommand;
import eg.edu.alexu.csd.oop.db.cs43.interfaces.CommandFactory;

public class Testing {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		String str = s.next();	
		CommandFactory client = new ClientCommand();
	
	}
}
