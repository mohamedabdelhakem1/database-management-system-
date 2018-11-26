package eg.edu.alexu.csd.oop.db.cs43;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import javafx.scene.chart.PieChart.Data;

public class CommandsParser {
	private String tableName;
	private List<String> columns;
	private String[] conditons;

	public String[] ReturnString() {

		return null;
	}

	

	public String[] validateCommand(String command) {
		String pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)";
		Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		String[] strs = pat.split(command.trim()); // removed trailing and leading spaces

		if (strs[0].equalsIgnoreCase("create")) {
			if (strs[1].equalsIgnoreCase("database")) {
				if (strs.length == 3) {
					return new String[] { "create", "database", strs[2] };
				}
			} else if (strs[1].equalsIgnoreCase("table")) {
				return strs;
			}
		} else if (strs[0].equalsIgnoreCase("drop")) {
			if (strs[1].equalsIgnoreCase("database")) {
				if (strs.length == 3) {
					return new String[] { "drop", "database", strs[2] };
				}
			} else if (strs[1].equalsIgnoreCase("table")) {
				if (strs.length == 3) {
					return new String[] { "drop", "database", strs[2] };
				} else {
					System.out.println("failed");
				}

			}

		} else if (strs[0].equalsIgnoreCase("insert")) {

		} else if (strs[0].equalsIgnoreCase("update")) {

		} else if (strs[0].equalsIgnoreCase("delete")) {

		} else if (strs[0].equalsIgnoreCase("select")) {
			pattern = "(\\s+)|([\\s*,\\s*]{1,})";
			pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			strs = pat.split(command.trim());

			if (strs[0].equalsIgnoreCase("select")) {
				if (strs[1].equalsIgnoreCase("*")) {
					if (strs[2].equalsIgnoreCase("from")) {
						tableName = strs[3];
						if (strs.length == 4) {
							// return all columns
						} else if (strs[4].equalsIgnoreCase("where")) {
							String allConditions = command.split("(?i)where")[1];
							allConditions = allConditions.replaceAll("<", " < ");
							allConditions = allConditions.replaceAll("=", " = ");
							allConditions = allConditions.replaceAll(">", " > ");
							conditons = allConditions.split("\\s+");

						}
					}
				} else {
					columns = new LinkedList<String>();
					int i;
					for (i = 1; !strs[i].equalsIgnoreCase("from"); i++) {
						columns.add(strs[i]);
					}
					if (strs[i].equalsIgnoreCase("from")) {
						tableName = strs[i + 1];
						if (strs.length == i + 2) {
							// return columns in array
						} else if (strs[i + 2].equalsIgnoreCase("where")) {
							// return columns with coditions
						}
					}

				}
			}
			return strs;

		}

		return null;
	}

	public String getTableName() {
		return tableName;
	}

	public String[] getColumns() {
		return (String[]) columns.toArray();
	}

	public String[] getconditions() {
		return conditons;
	}

	private void setColumnsAndTypes() {

	}
}
