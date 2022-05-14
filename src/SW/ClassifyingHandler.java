package SW;

import SW.algorithms.classification.Classifier;

import java.util.List;

/**
 * ClassifyingHandler class - handles classification of test data with a chosen classifier
 * @author Long
 * @version 1.0
 */
public class ClassifyingHandler {
    private final List<TextDocument> testTextDocs;
    private Classifier classifier;

    /**
     *
     * @param pathToTestData
     */
    public ClassifyingHandler(String pathToTestData) {
        Parser parser = new Parser(pathToTestData);
        this.testTextDocs = parser.textDocuments;
    }

    /**
     *
     * @param classifier
     */
    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    /**
     *
     */
    public void classifyTestData() {
        int correct = 0, total = testTextDocs.size();
        double correctPercentage;
        DAClass guessedClass;

        for(TextDocument td: testTextDocs) {
            guessedClass = classifier.classify(td);
            if (guessedClass.equals(td.getType())) {
                correct++;
//                if (guessedClass != DAClass.INFORM) {
//                    System.out.println(guessedClass + " recognized");
//                }
            }
        }

        correctPercentage = (double) correct / total;
        System.out.format("\nCorrectly classified %d out of %d documents => %f", correct, total, correctPercentage);
    }
}
