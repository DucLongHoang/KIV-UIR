package SW;

import SW.algorithms.classification.K_NN;
import SW.algorithms.classification.NaiveBayes;
import SW.algorithms.features.BagOfWords;
import SW.algorithms.features.DocumentFrequency;
import SW.algorithms.features.TFIDF;

import java.util.Arrays;
import java.util.List;

/**
 * Main class - run app from here
 * @author Long
 * @version 1.0
 */
public class Main {
    private final static String INPUT_FILE_PATH = "train.txt";
    private final static String TEST_DATA_FILE_PATH = "test.txt";

    public static void main(String[] args) {
        Parser p = new Parser(INPUT_FILE_PATH);

        // Feature making algorithms
        BagOfWords featAlgo = new BagOfWords(p.textDocuments);
//        DocumentFrequency featAlgo = new DocumentFrequency(p.textDocuments);
//        TFIDF featAlgo = new TFIDF(p.textDocuments);

        // Classification algorithms
        NaiveBayes classAlgo = new NaiveBayes(featAlgo);
//        K_NN classAlgo = new K_NN(featAlgo);

        // Classifying test data
        ClassifyingHandler handler = new ClassifyingHandler(TEST_DATA_FILE_PATH);
        handler.setClassifier(classAlgo);
        System.out.println();
        handler.classifyTestData();
    }
}
