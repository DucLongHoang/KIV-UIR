package SW.algorithms.classification;

import SW.DAClass;
import SW.Feature;
import SW.FeatureVector;
import SW.TextDocument;
import SW.algorithms.features.FeatureAlgorithm;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */
public class NaiveBayes extends Classifier {
    private static final String TRAIN_STATS_PATH = "train_stats.txt";
    private static final String TEST_STATS_PATH = "test_stats.txt";

    /**
     * NaiveBayes constructor
     * @param algorithm is the dataset to train on
     */
    public NaiveBayes(FeatureAlgorithm algorithm) {
        super(algorithm);

//        System.out.println();
//        for(Map.Entry<DAClass, Double> entry: probabilities.entrySet()) {
//            System.out.println(entry.getKey().toString() + ": " + entry.getValue());
//        }
    }

    @Override
    protected void trainOnData() { }

    /**
     * Method initializes default probabilities according to *-stats.txt files
     */
    private Map<DAClass, Double> initPriorProbabilities() {
        Map<DAClass, Double> probabilities = new HashMap<>();
        Scanner sc = null;

        try {
            sc = new Scanner(Paths.get(TRAIN_STATS_PATH));
            String[] splitLine;
            double priorProb;

            while(sc.hasNextLine()) {
                splitLine = sc.nextLine().split("[ :%]+");
                priorProb = Double.parseDouble(splitLine[1]);
                probabilities.put(DAClass.getDAClass(splitLine[0]), priorProb);
            }
        }
        catch (IOException e) {
            System.out.println("Problem reading file: " + e);
        }
        finally {
            assert sc != null;
            sc.close();
        }

        return probabilities;
    }

    @Override
    public DAClass classify(TextDocument document) {
        Map<DAClass, Double> probabilities = initPriorProbabilities();
        List<String> words = document.getWords();
        double wordOccurrence, denominator;    // NUMERATOR and DENOMINATOR
        double conditionalProb, oldProbability, newProbability;
        Feature tmpFeature;

        // calculating probabilities for all DAClasses
        for(Map.Entry<DAClass, FeatureVector> entry: vocabulary.entrySet()) {
//            System.out.println();
//            System.out.println(entry.getKey().toString() + " " + entry.getValue().getFeatureNamesVector());

            for(String word: words) {
                wordOccurrence = 0;

                // NUMERATOR
                tmpFeature = entry.getValue().getFeature(new Feature(word));
                if (tmpFeature != null) {
                    wordOccurrence = tmpFeature.getValue();
                }
                wordOccurrence++;   // Laplace smoothing, K = 1

                // DENOMINATOR
                denominator = entry.getValue().getTotalVectorValue() + uniqueFeatures.size();
//                denominator = 1;

                // CONDITIONAL PROBABILITY
                conditionalProb = wordOccurrence / denominator;

                // MULTIPLYING PROBABILITY BY CONDITIONAL PROBABILITY
                oldProbability = probabilities.get(entry.getKey());
                newProbability = oldProbability * conditionalProb;
                probabilities.replace(entry.getKey(), newProbability);
            }
        }

        // taking most probable DAClass
        double highestProb = 0;
        DAClass probableClass = null;

        for(Map.Entry<DAClass, Double> entry: probabilities.entrySet()) {
            if (entry.getValue() > highestProb) {
                highestProb = entry.getValue();
                probableClass = entry.getKey();
            }
//            System.out.println(entry.getKey() + ": prob - " + entry.getValue().probability);
        }

        return probableClass;
    }
}
