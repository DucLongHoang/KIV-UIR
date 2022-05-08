package SW;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Parser class - handles input file
 * and creates a TextDocument for each line
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
     * Constructor for Parser
     * @param inputFilePath path to the input file
     */
    public Parser(String inputFilePath) {
        this.lines = new ArrayList<>();
        this.textDocuments = new ArrayList<>();
        handleInputFile(inputFilePath);
        makeTextDocuments();
    }

    /**
     * Method tries to read all lines from the input file
     * @param inputFilePath path to the input file
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
     * Method creates a TextDocument for every line
     * assigning the DAClass and the words to it
     */
    private void makeTextDocuments() {
        ArrayList<String> words;
        DAClass type;
        String[] classAndTheRest;

        for(String line: lines) {
            classAndTheRest = line.split(" ");
            type = DAClass.getDAClass(classAndTheRest[0]);   // first word is da-class
            words = new ArrayList<>(Arrays.asList(line.split(DELIM)));
            words.remove(0);    // remove class, the rest is the document
            textDocuments.add(new TextDocument(type, words));
        }
    }
}
