package eg.edu.alexu.csd.oop.db.cs43;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.core.IsInstanceOf;

import eg.edu.alexu.csd.oop.db.Database;

public class TEst {



    public static void main(String[] args) {


//        String s ="    Update  table7amo  set 3abas = balas where h5hhh ";
        String s ="    SELECT * FROM tableH5H  where hhhhh ";
        String s1 ="CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)";
        String s2 = "column_name1='11111111' COLUMN_NAME2=10  column_name3='333333333' ";
        String s3 = "UPDATE table_name1 SET column_name1='111    11111'   ,    COLUMN_NAME2=22222222, column_name3='3333       33333' WHERE coLUmn_NAME3='VALUE       3'";
        
        String rr = "^(\\s*)(SELECT\\s* \\*|Delete)(\\s+)(FROM)(\\s+)(\\b.+\\b)(\\s+)";   //done
        String rrr = "^(\\s*)(SELECT\\s* \\*|Delete)(\\s+)(FROM)(\\s+)(\\b.+\\b)(\\s+)(where)(\\s+)(\\b.+\\b)(\\s*)";    //done
        
        
        String rrrr = "^(\\s*)(UPDATE)(\\s+)(\\b.+\\b)(Set)(\\s+)(\\b.+\\b)(\\s+)";              //done
        String rrrrr = "^(\\s*)(UPDATE)(\\s+)(\\b.+\\b)(Set)(\\s+)(\\b.+\\b)(\\s+)(where)(\\s+)(\\b.+\\b)(\\s*)";          //done
        
        
        Pattern pattern = Pattern.compile(rrr,Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(s);
        
        System.out.println(m.matches());				//          [                               &&[      ]         ]
//        System.out.println(m.find());
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
//	    System.out.println(m.group(13));
//	    System.out.println(m.group(14));
	    
// String regex =
//		 "^(\\s*)(INSERT INTO|UPDATE|SELECT * FROM|DELETE FROM|CREATE TABLE|DROP TABLE|CREATE DATABASE|DROP DATABASE)(\\s+)(\\b.+\\b)(\\s+)(VALUES)";
//  String regex2 = "^(SELECT)";
//      Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
//      Matcher m = pattern.matcher(s);
//
//
//
//
//                  System.out.println(m.find());
//                  System.out.println(m.group(0));
//                  System.out.println(m.group(1));
//                  System.out.println(m.group(2));
//                  System.out.println(m.group(3));
//                  System.out.println(m.group(4));
//                  System.out.println(m.group(5));
//                  System.out.println(m.group(6));
//                  System.out.println();
              }
         //     pattern = "(^\s+)|(\s([(]{1})\s)|([\s,\s]{1,})|(([)]{1})\s*)";

    }