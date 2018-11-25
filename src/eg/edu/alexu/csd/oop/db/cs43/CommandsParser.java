package eg.edu.alexu.csd.oop.db.cs43;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import javafx.scene.chart.PieChart.Data;

public class CommandsParser {
	private String tableName;
	private List<String> columns;
	Database database ;
	public CommandsParser() {
		database = new MyDatabase();
	}
	public String [] ReturnString() {
		
		return null;
	}
	public void executeCommands(String command) {
		String  pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)" ;
		Pattern pat = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
	    String[] strs = pat.split(command.trim()); // removed trailing and leading spaces
	    
		if(strs[0].equalsIgnoreCase("create")) {
			
		}else if(strs[0].equalsIgnoreCase("drop")) {
			
		}else if(strs[0].equalsIgnoreCase("insert")) {
			
		}else if(strs[0].equalsIgnoreCase("update")) {
			
		}else if(strs[0].equalsIgnoreCase("delete")) {
			
		}else if(strs[0].equalsIgnoreCase("select")) {
			  
		}
		
		
	}
	public String [] validateCommand(String command) {
		String  pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)" ;
		Pattern pat = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
	    String[] strs = pat.split(command.trim()); // removed trailing and leading spaces
	    
		if(strs[0].equalsIgnoreCase("create")) {
			if(strs[1].equalsIgnoreCase("database")) {
				if (strs.length == 3) {
					
					return new String[] {"create", "database",strs[2]};
				}
			}else if(strs[1].equalsIgnoreCase("table")) {
					return strs;
			}
		}else if(strs[0].equalsIgnoreCase("drop")) {
			if(strs[1].equalsIgnoreCase("database")) {
				if (strs.length == 3) {
					return new String[] {"drop","database", strs[2]};
				}
			}else if(strs[1].equalsIgnoreCase("table")) {
					if (strs.length == 3) {
						return new String[] {"drop","database", strs[2]};
					}else {
						System.out.println("failed");
					}
				
			}
			
		}else if(strs[0].equalsIgnoreCase("insert")) {
			
		}else if(strs[0].equalsIgnoreCase("update")) {
			
		}else if(strs[0].equalsIgnoreCase("delete")) {
			
		}else if(strs[0].equalsIgnoreCase("select")) {
			  pattern = "(\\s+)|([\\s*,\\s*]{1,})";
			  pat = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
			  strs = pat.split(command.trim()); 
				
				if (strs[0].equalsIgnoreCase("select")) {
					if (strs[1].equalsIgnoreCase("*")) {
						if (strs[2].equalsIgnoreCase("from")) {
							tableName = strs[3];
							if (strs[4].equalsIgnoreCase("where")) {
								String allConditions = command.split("(?i)where")[1];
								allConditions = allConditions.replaceAll("<", " < ");
								allConditions = allConditions.replaceAll("=", " = ");
								allConditions = allConditions.replaceAll(">", " > ");
								String[] conditons = allConditions.split("\\s+");
								
							}else if (strs.length == 4) {
								//return all columns
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
							if (strs[i + 2].equalsIgnoreCase("where")) {
								//return columns with coditions
							}else if(strs.length == i+2) {
								//return columns in array
							}
						}

					}
				}
			  return strs;
			  
		}
		
		return null;
	}

	public String getTableName(){
		return tableName;
	}
	
	public String[] getColumns(){
		return (String[]) columns.toArray();
	}
	
	private void setColumnsAndTypes() {
		
	}
}
