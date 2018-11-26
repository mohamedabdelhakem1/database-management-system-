package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import java.util.LinkedList;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.cs43.interfaces.Command;
import eg.edu.alexu.csd.oop.db.cs43.interfaces.CommandFactory;

public class ClientCommand implements CommandFactory {
	private String command;
	private Command commandClass;
	private boolean dropIfExists ;
	@Override
	public void setcommand(String command,boolean drop) {
		this.dropIfExists = drop;
		this.command = command;
	}

	@Override
	public Command getFunction() {
		String pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)";
		Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		String[] strs = pat.split(command.trim()); // removed trailing and leading spaces

		if (strs[0].equalsIgnoreCase("create")) {
			if (strs[1].equalsIgnoreCase("database")) {
				commandClass = new CreateDatabase(dropIfExists);
			} else if (strs[1].equalsIgnoreCase("table")) {
				commandClass = new CreateAndDrop();
			}
		}else if (strs[0].equalsIgnoreCase("drop")) {
			commandClass = new CreateAndDrop();	
		}
		else if (strs[0].equalsIgnoreCase("delete") || strs[0].equalsIgnoreCase("insert")
				|| (strs[0].equalsIgnoreCase("update"))) {
			commandClass = new Update();
		} else if (strs[0].equalsIgnoreCase("select")) {
			commandClass = new SelectQuery();
		}
		return commandClass;

	}

}
