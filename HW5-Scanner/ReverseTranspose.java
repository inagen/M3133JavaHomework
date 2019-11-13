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
        this(in, 4096);
    }

    public QuickScanner(InputStream in, int blockSize) {
        this.reader = new InputStreamReader(in);
        this.blockSize = blockSize;
        readInput();
    }

    public QuickScanner(String string) throws UnsupportedEncodingException {
        this(string, 4096);
    }

    public QuickScanner(String string, int blockSize) throws UnsupportedEncodingException {
        this.reader = new InputStreamReader(new ByteArrayInputStream((string + " ").getBytes("utf8")));
        this.blockSize = blockSize;
        readInput();
    }

    public QuickScanner(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, 4096, charset);
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
                if (cbuf == null) {
                    cbuf = new char[blockSize];
                }
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

public class ReverseTranspose {
    public static void main(String[] args) {
        QuickScanner sc = new QuickScanner(System.in);
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        int k = 0, h = 0;
        try {
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }

            for (int i = 0; i < lines.size(); i++) {
                sc = new QuickScanner(lines.get(i));
                k = 0;
                while (sc.hasNextInt()) {
                    k++;
                    if (k > h) {
                        matrix.add(new ArrayList<Integer>());
                        h = k;
                    }
                    matrix.get(k - 1).add(sc.nextInt());
                }
            }
            for (int i = 0; i < matrix.size(); i++) {
                for (int j = 0; j < matrix.get(i).size(); j++) {
                    System.out.print(matrix.get(i).get(j) + " ");
                }
                System.out.println();
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Scanner encodng error: " + e.getMessage());
            e.printStackTrace();
        }

        sc.close();
    }
}