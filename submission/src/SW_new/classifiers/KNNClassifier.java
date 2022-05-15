package SW_new.classifiers;

import SW_new.document.Feature;
import SW_new.featurizers.AbstractFeaturizer;
import SW_new.document.DAClass;
import SW_new.document.Document;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * KNNClassifier class - extends AbstractClassifier
 * Works by calculating the Euclid distance to every train Document
 * and sort in ascending order. Choose K nearest and democratically
 * choose a DAClass
 * @author Long
 * @version 2.0
 */
public class KNNClassifier extends AbstractClassifier {
    public int K;

    /**
     * Constructor for KNNClassifier
     * @param featurizer see super
     */
    public KNNClassifier(AbstractFeaturizer featurizer) {
        super(featurizer);
    }

    /**
     * Hack for chained constructor call
     * @param k number of nearest neighbours
     * @return this instance
     */
    public KNNClassifier setK(int k) {
        K = k;
        return this;
    }

    @Override
    public void train() { }

    @Override
    public DAClass classify(Document d) {
        featurizer.featurizeDoc(d);

        // sort docs by euclid distance from input doc
        List<Document> docs = featurizer.docs
                .stream()
                .sorted(Comparator.comparingDouble(doc -> euclidDistance(doc, d)))
                .collect(Collectors.toList());

        // take K nearest -> change stream to DAClasses -> make Map<DAClass, Count>
        // get Entry with the highest Count -> return Entry.key
        return docs.subList(0, K)
                .stream()
                .map(doc -> doc.type)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElseThrow();
    }

    /**
     * Method calculates Euclid distance of 2 Documents
     * @param trainedDoc document already featurized
     * @param toCompare document to calculate distance to
     * @return Euclid distance
     */
    private double euclidDistance(Document trainedDoc, Document toCompare) {
        Map<String, Double> allWords = new TreeMap<>();
        double oldVal, sum;

        // adding first vector
        for (Feature f: trainedDoc.features.vector.values()) {
            allWords.put(f.word, f.value);
        }

        // adding negative second vector
        for (Feature f: toCompare.features.vector.values()) {
            // subtracting present value
            if (allWords.containsKey(f.word)) {
                oldVal = allWords.get(f.word);
                allWords.put(f.word, oldVal - f.value);
                continue;
            }
            // else put in normally
            allWords.put(f.word, f.value);
        }

        // sum of values squared
        sum = allWords.values()
                .stream()
                .mapToDouble(d -> d * d)
                .sum();

        return Math.sqrt(sum);
    }
}
