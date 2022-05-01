package SW;

import SW.features.BagOfWords;

public class Main {
    private final static String INPUT_FILE_PATH = "train.txt";

    public static void main(String[] args) {
        Parser p = new Parser(INPUT_FILE_PATH);
        BagOfWords bow = new BagOfWords(p.textDocuments);
    }
}
