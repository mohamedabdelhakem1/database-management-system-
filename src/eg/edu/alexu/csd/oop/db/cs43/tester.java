package eg.edu.alexu.csd.oop.db.cs43;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tester {
	public static void main(String[] args) {
		String subjectString = "This is a string that \"will be\" highlighted when your 'regular expression' matches something.";
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(subjectString);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		       System.out.println(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		    	 System.out.println(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		    	 System.out.println(regexMatcher.group());
		    }
		} 
	}
}
