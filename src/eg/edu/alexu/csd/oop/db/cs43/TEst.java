package eg.edu.alexu.csd.oop.db.cs43;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEst {

	public static void main(String[] args) {
		String s ="SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2<5or ";
		String s3 ="coluMN_NAME2<5 OR coluMN_NAME1=3 AND NOT coluMN_NAME3>5 ";
		String s1 ="CREATE   TABLE   table_name1   (     column_name1 varchar , column_name2    int,  column_name3 varchar)       ";
		String s2 = "create database zbe" ;
		String pattern = "\\s+" ;
		Pattern pat = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
	    String[] strs = pat.split(s,3);
	    for(String sa : strs) {
	  // 	System.out.println(sa);
	    }
	   
	     String name = strs[2];
	  //   pattern = "(\\s+)|([\\s*,\\s*]{1,})";
	   //  pattern = "[<>=]";
	    
	     pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)" ;
	  //   pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)|(\\s*([=]{1,})\\s*)"; 
	     pat = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		  	Matcher matcher = pat.matcher(s3);
	
		  		
		  		
		  		
		  	    
		  		for(String sa : s3.split("\\s+")) {
		  		  	System.out.println(sa);
		  		    }
		  	}
		 //     pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)";
		 
	}

