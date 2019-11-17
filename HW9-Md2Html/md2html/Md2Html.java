package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Too few arguments of command line!. Exiting...");
            return;
        } else {
            String inName = args[0];
            String outName = args[1];
            BufferedReader reader = null;
            BufferedWriter writer = null;
            StringBuilder result = new StringBuilder();
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(inName), "UTF8"));

                StringBuilder block = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    while (line != null && !line.equals("")) {
                        block.append(line);
                        block.append('\n');
                        line = reader.readLine();
                    }
                    if (block.length() > 0) {
                        block.deleteCharAt(block.length() - 1);
                        MdParser parser = new MdParser(block.toString());
                        parser.toHtml(result);
                        result.append('\n');
                        block = new StringBuilder();
                    }
                }

                try {
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outName), "UTF8"));
                    writer.write(result.toString());
                } catch (UnsupportedEncodingException e) {
                    System.err.println("Output file is incorrectly encoded!\n" + e.getMessage());
                    e.printStackTrace(System.err);
                } catch (FileNotFoundException e) {
                    System.err.println("Output file not found!\n" + e.getMessage());
                    e.printStackTrace(System.err);
                } catch (IOException e) {
                    System.err.println("Output file write error!\n" + e.getMessage());
                    e.printStackTrace(System.err);
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        System.err.println("Writer close error!\n" + e.getMessage());
                        e.printStackTrace(System.err);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                System.err.println("Input file is incorrectly encoded!\n" + e.getMessage());
                e.printStackTrace(System.err);
            } catch (FileNotFoundException e) {
                System.err.println("Input file not found!\n" + e.getMessage());
                e.printStackTrace(System.err);
            } catch (IOException e) {
                System.err.println("Input error!\n" + e.getMessage());
                e.printStackTrace(System.err);
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Reader close error!\n" + e.getMessage());
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}