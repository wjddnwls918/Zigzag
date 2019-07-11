import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;

public class Inflect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getSnakeCase("Product"));
		System.out.println(getSnakeCase("SpecialGuest"));
		System.out.println(getSnakeCase("Donald E. Knuth"));

	}

	public static String getSnakeCase(String input) {

		String result = "";
		int prePosition = 0;
		ArrayList<String> stringList = new ArrayList<>();

		for (int i = 1; i < input.length(); i++) {
			while (i < input.length() && !(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')) {
				i++;
			}
			stringList.add(input.substring(prePosition, i));
			prePosition = i;
		}

		for (int i = 0; i < stringList.size(); i++) {
			String lower = stringList.get(i).toLowerCase();
			for (int j = 0; j < lower.length(); j++) {
				if (lower.charAt(j) >= 'a' && lower.charAt(j) <= 'z') {
					result += lower.charAt(j);
				}
			}
			if (i != stringList.size() - 1) {
				result += '_';
			}
		}

		return result;

	}
}
