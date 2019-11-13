import java.util.*;
import java.io.*;

public class ReverseEven {

	private static ArrayList<Integer> getEvenNumbersFromString(String str) {
		Scanner sc;
		try {
			sc = new Scanner(str);
		} catch(Exception e) {
			return new ArrayList<>();
		}
		ArrayList<Integer> result = new ArrayList<>();
		while (sc.hasNextInt()) {
			int number = sc.nextInt();
			result.add(number);
		}
		return result;
	}


	private static void printArrayList(ArrayList<Integer> arr) {
		for(int i = arr.size() - 1; i >= 0; i--) {
			System.out.print(arr.get(i) + " ");
		}
		System.out.print("\n");
	} 

	public static void main(String[] args) {
		canner sc = new Scanner(System.in);
		ArrayList<String> input = new ArrayList<>();
		while (sc.hasNextLine()) {
			input.add(sc.nextLine());
		}

		for (int i = input.size() - 1; i >= 0; i--) {
			printArrayList(getEvenNumbersFromString(input.get(i)));
		}
		sc.close();
	}

} 
