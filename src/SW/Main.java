package SW;

import SW.algorithms.features.BagOfWords;

import java.util.List;

public class Main {
    private final static String INPUT_FILE_PATH = "train.txt";

    public static void main(String[] args) {
        Parser p = new Parser(INPUT_FILE_PATH);
        BagOfWords bow = new BagOfWords(p.textDocuments);

        List<String> document = List.of("a", "ya", "i", "lieutenant", "or", "over", "ya");

        System.out.print("\n[");
        for(var i: bow.getFeatureVectorFromDocument(document)) {
            System.out.print(i + ",");
        }
        System.out.println("]");
    }
}
