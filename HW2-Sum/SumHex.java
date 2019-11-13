import java.util.*;

public class SumHex {
	private static int getStringSum(String str) {
		int sum = 0;
		String[] words = str.split("\\p{javaWhitespace}+");
		for (int i = 0; i < words.length; i++) {
			if (!words[i].isEmpty()) {
				if (words[i].toUpperCase().startsWith("0X")) {
					sum += Integer.parseUnsignedInt(words[i].substring(2), 16);
				} else {
					sum += Integer.parseInt(words[i]);
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 0; i < args.length; i++) {
			sum += getStringSum(args[i]);
		}
		System.out.println(sum);
	}
}