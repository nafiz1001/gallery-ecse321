
package ca.mcgill.ecse321.gallery.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

	public static <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	public static <T> Set<T> toSet(Iterable<T> iterable) {
		Set<T> resultList = new HashSet<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	public static Boolean isEmailValid(String email, Boolean isValidAlready) {
		Boolean isValid = isValidAlready;
		int numAts = 0;
		int atIndex = 0;
		int numDots = 0;
		int dotIndex = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				numAts++;
				atIndex = i;
			}
			if (email.charAt(i) == '.') {
				numDots++;
				dotIndex = i;
			}
		}
		if (numAts != 1) {
			isValid = false;
		}
		if (numDots != 1 || atIndex >= dotIndex) {
			isValid = false;
		}
		return isValid;
	}
}
