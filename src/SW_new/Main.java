package SW_new;

import SW_new.classifiers.KNNClassifier;
import SW_new.classifiers.NaiveBayesClassifier;
import SW_new.document.Document;
import SW_new.featurizers.AbstractFeaturizer;
import SW_new.featurizers.BagOfWords;
import SW_new.featurizers.DocumentFrequency;
import SW_new.featurizers.TF_IDF;

import java.util.List;

public class Main {
    private final static String TRAIN_DATA_FILE_PATH = "train.txt";
    private final static String TEST_DATA_FILE_PATH = "test.txt";
//    private final static String TRAIN_DATA_FILE_PATH = "myTrain.txt";
//    private final static String TEST_DATA_FILE_PATH = "myTest.txt";

    public static void main(String[] args) {

        Parser p = new Parser(TRAIN_DATA_FILE_PATH);

        // Feature making algorithms
        BagOfWords featAlgo = new BagOfWords(p.documents);
//        DocumentFrequency featAlgo = new DocumentFrequency(p.documents);
//        TF_IDF featAlgo = new TF_IDF(p.documents);

        // Classification algorithms
//        NaiveBayesClassifier classAlgo = new NaiveBayesClassifier(featAlgo);
        KNNClassifier classAlgo = new KNNClassifier(featAlgo).setK(3);

//         Classifying test data
        ClassificationHandler handler = new ClassificationHandler(TEST_DATA_FILE_PATH);
        handler.classifier = classAlgo;
        handler.classifyTestData();

//        allOptionsClassify(p.documents);
    }

    public static void allOptionsClassify(List<Document> docs) {
        ClassificationHandler handler = new ClassificationHandler(TEST_DATA_FILE_PATH);

        AbstractFeaturizer[] featurizers = new AbstractFeaturizer[] {
                new BagOfWords(docs), new DocumentFrequency(docs), new TF_IDF(docs)
        };

        for (AbstractFeaturizer af: featurizers) {
            handler.classifier = new NaiveBayesClassifier(af);
            handler.classifyTestData();
        }
    }
}
