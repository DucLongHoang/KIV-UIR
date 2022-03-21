package cv05;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    final static String COMMA = ";";
    String pathToFile;
    List<Item> items;

    public Parser(String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        items = new ArrayList<>();
        parseFile();
    }

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

    private int[] convertStringArrayToInt(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for(int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }
}