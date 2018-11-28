package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class ExecuteUpdateQuery {
	private File DataBaseFile;
	private String tableName;
	private String[] columnsNames;
	private String[] columnsTypes;
	
	
	public void setDataBaseFile(File DataBaseFile)  {
		this.DataBaseFile = DataBaseFile;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setColumnsNames(String[] columnsnames) {
		this.columnsNames = columnsnames;
	}

	public void setColumnsTypes(String[] columnsTypes) {
		this.columnsTypes = columnsTypes;
	}
	
	public int insertData() throws SQLException {
		
		File tablefolder = new File(DataBaseFile.getAbsolutePath() +System.getProperty("file.separator") + tableName);
		if(!tablefolder.exists()) {
			//table isn't exist in database
			throw new SQLException();
		}
		
		
		
		
		
		return 0;
	}

}
