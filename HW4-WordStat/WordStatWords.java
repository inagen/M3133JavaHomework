import java.util.*;
import java.io.*;

class Word {
	private String string;
	private int frequency;
	
	public Word(String string) {
		this.string = string;
		this.frequency = 1;
	} 

	public String getString() {
		return string;
	}

	public int getFrequency() {
		return frequency;
	}

	public void incrementFrequency() {
		frequency++;
	}

	public String toString() {
		return string + " " + frequency;
	}
}

public class WordStatWords {
	private static ArrayList<Word> words = new ArrayList<>();

	public static void main(String[] args) {	
		if (args.length < 2 && args == null) {
			System.err.println("Error: No input and output file names specified!");
			return;
		}

		String inputFileName = args[0];
		String outputFileName = args[1];
		BufferedReader reader = null;
		BufferedWriter writer = null;
		words = new ArrayList<>();

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "UTF8"));
			String line = reader.readLine();
			while (line != null) {
				lineProcessing(line);
				line = reader.readLine();
			}
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF8"));
				Collections.sort(words, new Comparator<Word>() {
					@Override
					public int compare(Word lhs, Word rhs) {
						return lhs.getString().compareTo(rhs.getString());
					}
				});

				for (int i = 0; i < words.size(); i++) {
					writer.write(words.get(i) + System.lineSeparator());
				}
			} catch (FileNotFoundException e) {
				System.err.println("Output File not found!");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Output File error!");
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Input File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Input File error!");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void lineProcessing(String line) {
		for (int i = 0; i < line.length(); i++) {
			int idxStart = i;
			while (i < line.length() && isWordChar(line.charAt(i))) {
				i++;
			}
			
			if (idxStart < i) {
				addWord(line.substring(idxStart, i).toLowerCase());
			}
		}
	}

	private static Boolean isWordChar(char c) {
		return Character.getType(c) == Character.DASH_PUNCTUATION 
									|| Character.isLetter(c) 
									|| c == '\'';
	}

	private static void addWord(String wordString) {
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getString().equals(wordString)) {
				words.get(i).incrementFrequency();
				return;
			}
		}
		words.add(new Word(wordString));
	}
}