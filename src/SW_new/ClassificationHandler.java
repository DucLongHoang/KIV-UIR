package SW_new;

import SW_new.classifiers.AbstractClassifier;
import SW_new.document.DAClass;
import SW_new.document.Document;

import java.util.List;

public class ClassificationHandler {

    public final List<Document> testDocs;
    public AbstractClassifier classifier;

    public ClassificationHandler(String pathToTestData) {
        Parser parser = new Parser(pathToTestData);
        this.testDocs = parser.documents;
    }

    public void classifyTestData() {
        int correct = 0, total = testDocs.size();
        double correctPercentage;
        DAClass guessedClass;

        for(Document d: testDocs) {
            guessedClass = classifier.classify(d);
            if (guessedClass.equals(d.type)) {
                correct++;
//                if (guessedClass != DAClass.INFORM) {
//                    System.out.println(guessedClass + " recognized");
//                }
            }
        }

        correctPercentage = 100 * ( (double) correct / total );
        System.out.format("\n%s + %s ", classifier.getClass().getSimpleName(), classifier.featurizer.getClass().getSimpleName());
        System.out.format("\nCorrectly classified %d out of %d documents => %.2f %% \n", correct, total, correctPercentage);
    }
}
