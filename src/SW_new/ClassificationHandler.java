package SW_new;

import SW_new.classifiers.AbstractClassifier;
import SW_new.document.DAClass;
import SW_new.document.Document;

import java.util.List;

/**
 * ClassificationHandler class - handles classifying input Documents
 * by using an AbstractClassifier
 * @author Long
 * @version 2.0
 */
public class ClassificationHandler {
    public final List<Document> testDocs;
    public AbstractClassifier classifier;
    public boolean isFile;

    /**
     * Constructor for ClassificationHandler
     * @param input file path or document
     * @param isFile flag
     */
    public ClassificationHandler(String input, boolean isFile) {
        Parser p;

        if (isFile)
            p = new Parser(input, true);
        else p = new Parser(input, false);

        this.isFile = isFile;
        this.testDocs = p.documents;
    }

    /**
     * Method classifies data and return statistic String
     * if isFile is false return guessed DAClass
     * @return statistic or guessed DAClass
     */
    public String classifyTestData() {
        int correct = 0, total = testDocs.size();
        double correctPercentage;
        DAClass guessedClass = null;
        String result;

        for(Document d: testDocs) {
            guessedClass = classifier.classify(d);
            if (guessedClass.equals(d.type)) {
                correct++;
            }
        }

        if (isFile) {
            correctPercentage = 100 * ((double) correct / total);
            result = String.format("%s + %s ", classifier.getClass().getSimpleName(), classifier.featurizer.getClass().getSimpleName());
            result += String.format("\n\tCorrectly classified %d out of %d documents => %.2f %%", correct, total, correctPercentage);
        }
        else {
            assert guessedClass != null;
            result = "Sentence classified as: " + guessedClass.name();
        }

        return result;
    }
}
