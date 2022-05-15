package SW_new.classifiers;

import SW_new.document.DAClass;
import SW_new.document.Document;
import SW_new.document.Feature;
import SW_new.document.FeatureVector;
import SW_new.featurizers.AbstractFeaturizer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * NaiveBayesClassifier class - extends AbstractClassifier
 * Uses Bayes theorem as its core principle
 * @author Long
 * @version 2.0
 */
public class NaiveBayesClassifier extends AbstractClassifier {
    public final static double LAPLACE_SMOOTHING = 0.00000000001;
    public Map<DAClass, Integer> classCount;

    /**
     * Constructor for NaiveBayesClassifier
     * @param featurizer see super
     */
    public NaiveBayesClassifier(AbstractFeaturizer featurizer) {
        super(featurizer);
    }

    @Override
    public DAClass classify(Document d) {
        Map<DAClass, Double> probabilities = initPriorProbabilities();
        List<Feature> features = new ArrayList<>(d.features.vector.values());

        double featureOccurrence, denominator;    // NUMERATOR and DENOMINATOR
        double conditionalProb, oldProbability, newProbability;
        Feature tmpFeature;


        // calculating probabilities for all DAClasses
        for(Map.Entry<DAClass, FeatureVector> entry: featurizer.featuresByClassMap.entrySet()) {

            for(Feature feature: features) {
                // Laplace smoothing
                featureOccurrence = LAPLACE_SMOOTHING;

                // NUMERATOR
                tmpFeature = entry.getValue().getFeature(feature);
                if (tmpFeature != null) {
                    featureOccurrence += tmpFeature.value;
                }

                // DENOMINATOR
                denominator = classCount.get(entry.getKey());

                // CONDITIONAL PROBABILITY
                conditionalProb = featureOccurrence / denominator;

                // MULTIPLYING PROBABILITY BY CONDITIONAL PROBABILITY
                oldProbability = probabilities.get(entry.getKey());
                newProbability = oldProbability * conditionalProb;
                probabilities.replace(entry.getKey(), newProbability);
            }
        }

        return probabilities.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    @Override
    public void train() {
        classCount = new HashMap<>();
        int oldCount;

        // init to zero
        for (DAClass daClass: DAClass.values()) { classCount.put(daClass, 0); }

        // increment DA-class count
        for (Document doc: featurizer.docs) {
            oldCount = classCount.get(doc.type);
            classCount.put(doc.type, ++oldCount);
        }
    }

    /**
     * Method initializes prior probabilities according to stats file
     * @return Map of DAClasses and their prior probabilities
     */
    private Map<DAClass, Double> initPriorProbabilities() {
        Map<DAClass, Double> probabilities = new HashMap<>();
        Scanner sc;

        try {
            sc = new Scanner(Paths.get(TRAIN_STATS_PATH));
            String[] splitLine;
            double priorProb;

            while(sc.hasNextLine()) {
                splitLine = sc.nextLine().split("[ :%]+");

                // initializing only values that occur
                if (classCount.get(DAClass.getDAClass(splitLine[0])) > 0) {
                    priorProb = Double.parseDouble(splitLine[1]);
                    probabilities.put(DAClass.getDAClass(splitLine[0]), priorProb);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Problem reading file: " + e);
        }

        return probabilities;
    }
}
