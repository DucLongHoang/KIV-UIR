package SW_new;

import SW_new.document.DAClass;
import SW_new.document.Document;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Parser class - handles input file
 * and creates a Document for each line
 * @author Long
 * @version 2.0
 */
public class Parser {
    /** all regex characters */
    private final static char
            SPACE = ' ', PLUS = '+', DASH = '-',
            DOT = '.', TAB = '\t', QUESTION = '?',
            EXCLAMATION = '!', STAR = '*', QUOTE = '"',
            SLASH = '/';
    /** initializing main regex delimiter */
    private final static String DELIM = "[" + SPACE + PLUS + DASH +
            DOT + TAB + STAR + QUOTE + SLASH + "]+";

    public List<String> lines;
    public List<Document> documents;

    /**
     * Constructor for Parser
     * @param input path to the input file
     */
    public Parser(String input, boolean isFile) {
        this.lines = new ArrayList<>();
        this.documents = new ArrayList<>();

        if (isFile) handleInputFile(input);
        else lines.add(DAClass.TO_BE_CLASSIFIED + " " + input);

        makeDocuments();
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
    private void makeDocuments() {
        List<String> words;
        DAClass type;

        for(String line: lines) {
            // first word is da-class
            type = DAClass.getDAClass(line.split(" ")[0]);
            // split into List of Strings
            words = new ArrayList<>(Arrays.asList(line.split(DELIM)));
            // remove da-class, the rest is the document
            words.remove(0);

            words = words.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            checkLastChar(words);
            documents.add(new Document(type, words));
        }
    }

    /**
     * Method checks last char of a Document
     * if it's an exclamation or question mark then make it a separate Feature
     * @param words of a Document
     */
    private void checkLastChar(List<String> words) {
        String lastWord = words.get(words.size() - 1);
        char lastChar = lastWord.toCharArray()[lastWord.length() - 1];
        char toBeAdded = '\0';

        switch (lastChar) {
            case EXCLAMATION -> {
                toBeAdded = EXCLAMATION;
                lastWord = lastWord.substring(0, lastWord.length() - 1);
            }
            case QUESTION -> {
                toBeAdded = QUESTION;
                lastWord = lastWord.substring(0, lastWord.length() - 1);
            }
        }

        if (toBeAdded != '\0') {
            words.remove(words.size() - 1);
            words.add(lastWord);
            words.add(String.valueOf(toBeAdded));
        }
    }
}
