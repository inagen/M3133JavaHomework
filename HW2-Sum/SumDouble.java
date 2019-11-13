import java.util.*;

public class SumDouble {
	private static double getStringSum(String str) {
		double sum = 0;
		String[] words = str.split("\\p{javaWhitespace}+");
		for (int i = 0; i < words.length; i++) {
			if (!words[i].isEmpty()) {
				sum += Double.parseDouble(words[i]);
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		double sum = 0;
		for (int i = 0; i < args.length; i++) {
			sum += getStringSum(args[i]);
		}
		System.out.println(sum);
	}
}