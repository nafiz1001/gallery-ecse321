
package ca.mcgill.ecse321.gallery.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * This class contains useful methods
 *
 */
public class Utils {

	/**
	 * Converts Iterable to a List
	 * 
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	public static <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	/**
	 * Converts Iterable to a Set
	 * 
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	public static <T> Set<T> toSet(Iterable<T> iterable) {
		Set<T> resultList = new HashSet<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	/**
	 * Verifies if the email inputed is valid
	 * 
	 * @param email
	 * @param isValidAlready
	 * @return
	 */
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

	/**
	 * Verifies if the tags inputed are valid
	 * 
	 * @param tags
	 * @return
	 */
	public static Boolean areTagsValid(String tags) {
		Boolean tagsValid = true;
		String[] allTags = tags.split(", ");

		for (String tag : allTags) {
			if (tag.matches("[a-zA-Z]+") == false)
				tagsValid = false;

		}
		return tagsValid;

	}
}
