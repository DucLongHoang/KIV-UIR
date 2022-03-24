package cv05;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Parser class - parses CSV-type file to create Item objects
 * @author Long
 * @version 1.0
 */
public class Parser {
    /** Comma constant */
    final static String COMMA = ";";
    /** Path to file */
    String pathToFile;
    /** Items read from file */
    List<Item> items;

    /**
     * Constructor for Parser
     * @param pathToFile path to the to-be-parsed file
     * @throws IOException exception if file not found
     */
    public Parser(String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        items = new ArrayList<>();
        parseFile();
    }

    /**
     * Method parses file and creates Item objects
     * @throws IOException exception when file not found
     */
    private void parseFile() throws IOException {
        Scanner sc = new Scanner(Paths.get(pathToFile));
        String line;
        int[] tokens;

        sc.nextLine();
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            tokens = convertStringArrayToInt(line.split(COMMA));
            items.add(tokens[0] - 1, new Item(tokens[1], tokens[2]));
        }
    }

    /**
     * Method converts String array to int array
     * @param stringArray String array to convert
     * @return int array
     */
    private int[] convertStringArrayToInt(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for(int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }
}