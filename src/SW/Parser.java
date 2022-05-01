package SW;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */
public class Parser {
    /** all regex characters */
    private final static char
            SPACE = ' ', PLUS = '+', DASH = '-', DOT = '.', TAB = '\t',
            QUESTION = '?', EXCLAMATION = '!', STAR = '*', QUOTE = '"',
            SLASH = '/';
    /** initializing main regex delimiter */
    private final static String DELIM = "[" + SPACE + PLUS + DASH +
            DOT + TAB + QUESTION + EXCLAMATION +
            STAR + QUOTE + SLASH + "]+";

    List<String> lines;
    List<TextDocument> textDocuments;

    /**
     *
     * @param inputFilePath
     */
    public Parser(String inputFilePath) {
        this.lines = new ArrayList<>();
        this.textDocuments = new ArrayList<>();
        handleInputFile(inputFilePath);
        makeTextDocuments();
    }

    /**
     *
     * @param inputFilePath
     */
    private void handleInputFile(String inputFilePath) {
        try {
            Scanner sc = new Scanner(Paths.get(inputFilePath));
            while(sc.hasNextLine()) {
                this.lines.add(sc.nextLine());
            }
        }
        catch (IOException e) {
            System.out.println("Error reading input file");
        }
    }

    /**
     *
     */
    private void makeTextDocuments() {
        ArrayList<String> words;
        DAClass type;

        for(String line: lines) {
            words = new ArrayList<>(Arrays.asList(line.split(DELIM)));
            type = DAClass.valueOf(words.get(0));   // first word is da-class
            words.remove(0);    // remove it, the rest is the document
            textDocuments.add(new TextDocument(type, words));
        }
    }
}
