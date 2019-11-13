import java.util.*;
import java.io.*;

class QuickScanner {
    private int position = 0;
    private int readerResult = 0;
    private boolean eof = false;
    private char[] cbuf;
    private final int blockSize;
    private final Reader reader;
    private Integer nextInt = null;

    public QuickScanner(InputStream in) {
        this(in, 1024);
    }

    public QuickScanner(InputStream in, int blockSize) {
        this.reader = new InputStreamReader(in);
        this.blockSize = blockSize;
        readInput();
    }

    public QuickScanner(String string) throws UnsupportedEncodingException {
        this(string, 1024);
    }

    public QuickScanner(String string, int blockSize) throws UnsupportedEncodingException {
        this.reader = new InputStreamReader(new ByteArrayInputStream((string + " ").getBytes("utf8")));
        this.blockSize = blockSize;
        readInput();
    }

    public QuickScanner(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, 1024, charset);
    }

    public QuickScanner(File file, int blockSize, String charset)
            throws FileNotFoundException, UnsupportedEncodingException {
        this.reader = new InputStreamReader(new FileInputStream(file), charset);
        this.blockSize = blockSize;
        readInput();
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error in QuickScanner.close() method: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean hasNextLine() {
        return !eof;
    }

    public String nextLine() {
        StringBuilder builder = new StringBuilder();
        while (cbuf[position] != '\n') {
            builder.append(cbuf[position]);
            move();
        }
        move();
        return builder.toString();
    }

    public boolean hasNextInt() {
        StringBuilder builder = new StringBuilder();
        while ((Character.isWhitespace(cbuf[position])) && !eof) {
            move();
        }
        while (((Character.isDigit(cbuf[position])) || cbuf[position] == '-') && !eof) {
            builder.append(cbuf[position]);
            move();
        }
        String suspect = builder.toString();
        if (isInt(suspect)) {
            return true;
        } else {
            return false;
        }
    }

    public Integer nextInt() throws NoSuchElementException {
        if (nextInt != null) {
            return nextInt;
        } else {
            throw new NoSuchElementException();
        }
    }

    private boolean isInt(String suspect) {
        try {
            nextInt = Integer.parseInt(suspect);
            return true;
        } catch (NumberFormatException e) {
            nextInt = null;
            return false;
        }
    }

    private void readInput() {
        if (!eof) {
            try {
                cbuf = new char[blockSize];
                readerResult = reader.read(cbuf, 0, blockSize);
            } catch (IOException e) {
                System.out.println("Error in QuickScanner.readInput local method: " + e.getMessage());
                e.printStackTrace();
                eof = true;
            }
        }
    }

    private void move() {
        if (!eof) {
            position += 1;
            if (position == readerResult) {
                position = 0;
                readInput();
                if (readerResult == -1) {
                    eof = true;
                }
            }
        }
    }
}

class Word {
    public final String word;
    public int frequency;
    public ArrayList<Integer> lineNumbers;
    public ArrayList<Integer> wordNumbers;

    public Word(String word, int lnum, int wnum) {
        this.word = word;
        frequency = 1;
        lineNumbers = new ArrayList<Integer>();
        wordNumbers = new ArrayList<Integer>();
        lineNumbers.add(lnum);
        wordNumbers.add(wnum);
    }

    public ArrayList<Integer> getLineNumbers() {
        return lineNumbers;
    }

    public ArrayList<Integer> getWordNumbers() {
        return wordNumbers;
    }

    public void increment(int lnum, int wnum) {
        frequency += 1;
        lineNumbers.add(lnum);
        wordNumbers.add(wnum);
    }

    public String getString() {
        return word;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(word + " " + frequency);
        for (int i = 0; i < frequency; i++) {
            result.append(" " + lineNumbers.get(i) + ":" + wordNumbers.get(i));
        }
        return result.toString();
    }
}

public class WordStatLineIndex {
    private static ArrayList<Word> words;

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.err.println("Error: No input and output file names specified!");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];
        BufferedWriter writer = null;
        words = new ArrayList<>();
        QuickScanner sc = null;
        try {
            sc = new QuickScanner(new File(inputFileName), "UTF-8");
            String line = new String();
            int lnum = 1;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                lineProcessing(line, lnum);
                lnum += 1;
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
            sc.close();
        }
    }

    private static void lineProcessing(String line, int lnum) {
        int wnum = 1;
        for (int i = 0; i < line.length(); i++) {
            int idxStart = i;
            while (i < line.length() && isWordChar(line.charAt(i))) {
                i++;
            }

            if (idxStart < i) {
                addWord(line.substring(idxStart, i).toLowerCase(), lnum, wnum);
                wnum += 1;
            }
        }
    }

    private static Boolean isWordChar(char c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c) || c == '\'';
    }

    private static void addWord(String wordString, int lnum, int wnum) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getString().equals(wordString)) {
                words.get(i).increment(lnum, wnum);
                return;
            }
        }
        words.add(new Word(wordString, lnum, wnum));
    }

}
