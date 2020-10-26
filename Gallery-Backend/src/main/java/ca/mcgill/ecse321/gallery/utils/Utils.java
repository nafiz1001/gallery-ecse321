
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

}
