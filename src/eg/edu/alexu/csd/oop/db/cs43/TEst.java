package eg.edu.alexu.csd.oop.db.cs43;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.core.IsInstanceOf;

import eg.edu.alexu.csd.oop.db.Database;

public class TEst {

	public static void main(String[] args) {
	
			
			String s ="CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ";
			String s1 ="CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)";
			String s2 = "column_name1='11111111' COLUMN_NAME2=10  column_name3='333333333' ";
	String s3 = "UPDATE table_name1 SET column_name1='111    11111'   ,    COLUMN_NAME2=22222222, column_name3='3333       33333' WHERE coLUmn_NAME3='VALUE       3'";
		String r2 = "^(\\s*)(SELECT\\s* \\*|Delete)(\\s+)(FROM)(\\s+)(\\b.+\\b)(\\s*)";   //done2
	 String r1 = "^(\\s*)(SELECT\\s* \\*|Delete)(\\s+)(FROM)(\\s+)(\\b.+\\b)(\\s+)(where)(\\s+)(\\b.+\\b)(\\s*)";    //done1
	 String r4 = "^(\\s*)(UPDATE)(\\s+)(\\b.+\\b)(\\s+)(Set)(\\s+)(\\b.+\\b)(\\s*)";              //done4
     String r3 = "^(\\s*)(UPDATE)(\\s+)(\\b.+\\b)(\\s+)(Set)(\\s+)(\\b.+\\b)(\\s+)(where)(\\s+)(\\b.+\\b)(\\s*)";          //done3		
					
		String r5 = "^(\\s*)(DROP\\s* TABLE|CREATE\\s* DATABASE|DROP\\* DATABASE)(\\s+)(\\b.+\\b)(\\s*)$"; // done0
		
		String r6 = "^(\\s*)(SELECT)(\\s+)(\\b.+\\b)(\\s+)(FROM)(\\s+)(\\b.+\\b)(\\s+)(WHERE)(\\s+)(\\b.+\\b)"; // done-2
		String r7  = "^(\\s*)(SELECT)(\\s+)(\\b.+\\b)(\\s+)(FROM)(\\s+)(\\b.+\\b)(\\s+)"; // done-3
		String r8  = "^(\\s*)(INSERT)(\\s+)(INTO)(\\s+)(\\b.+\\b)(\\s+)(VALUES)(\\s*)([(]{1}.[^(]*[)])"; // done-4
		String r9  = "^(\\s*)(INSERT)(\\s+)(INTO)(\\s+)(\\b.+\\b)(\\s+)([(]{1}.[^(]*[)]{1})(\\s*)(VALUES)(\\s*)([(]{1}.[^(]*[)]{1})"; // done-5
		String r10 = "^(\\s*)(CREATE\\s* TABLE)(\\s+)((\\b.+\\b))([(]{1}.[^(]*[)]{1})";//done-1
	//	regex = "^(\\s*)(SELECT \\*)(\\s+)(FROM)(\\s+)(\\b.+\\b)";  
		Pattern pattern = Pattern.compile(r10,Pattern.CASE_INSENSITIVE);
		  Matcher m = pattern.matcher(s);
			  		
			  		
			  		
			  	    
			  		System.out.println(m.find());
			  		System.out.println(m.group(0));
			  		System.out.println(m.group(1));
			  		System.out.println(m.group(2));
			  		System.out.println(m.group(3));
			  		System.out.println(m.group(4));
			  		System.out.println(m.group(5));
			  		System.out.println(m.group(6));
			  		System.out.println(m.group(7));
			  		System.out.println(m.group(8));
			  		System.out.println(m.group(9));
			  		System.out.println(m.group(10));
			  		System.out.println(m.group(11));
			  		System.out.println(m.group(12));
			  		System.out.println(m.group(13));
			  		System.out.println(m.group(14));
			  	}
	// pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)";

}

// karimatef9@bitbucket.org/mohamedmohamed1/dbms.git
