package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;

import org.junit.Assert;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.MyDatabase;

public  class testingXml {

	public static void main(String[] args) throws Exception {
		Database db = new MyDatabase();
		db.createDatabase("yousef", true);
		db.executeStructureQuery("Create TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)");
		int count1 = db.executeUpdateQuery("INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		Assert.assertNotEquals("Insert returned zero rows", 0, count1);
		
	
	}

}
