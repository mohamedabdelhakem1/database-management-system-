package eg.edu.alexu.csd.oop.db.cs43;

import java.io.File;

public  class testingXml {

	public static void main(String[] args) throws Exception {
		XmlValidation validation = new XmlValidation();
		validation.validateXml(new File("sample"+System.getProperty("file.separator")+"testDB"	+System.getProperty("file.separator")+"table_name1"));
	
	}

}
