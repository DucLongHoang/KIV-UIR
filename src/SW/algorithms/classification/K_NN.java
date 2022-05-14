package SW.algorithms.classification;

import SW.DAClass;
import SW.TextDocument;
import SW.algorithms.features.FeatureAlgorithm;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * K_NN class - extends Classifier
 * @author Long
 * @version 1.0
 */
public class K_NN extends Classifier {
    public int K;

    /**
     * Constructor for K_NN
     * @param algorithm
     */
    public K_NN(FeatureAlgorithm algorithm) {
        super(algorithm);
    }

    public K_NN setK(int k) {
        this.K = k;
        return this;
    }

    @Override
    protected void trainOnData() {

    }

    @Override
    public DAClass classify(TextDocument document) {
        List<TextDocument> documents = this.algorithm.getTextDocuments();
        List<DAClass> kNearestDocs;
        documents.sort(Comparator.comparingDouble(d -> getEuclidDistance(document, d)));
        kNearestDocs = documents.subList(0, K)
                .stream()
                .map(TextDocument::getType)
                .collect(Collectors.toList());

        return kNearestDocs.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElseThrow();
    }

    /**
     *
     * @param toBeTested
     * @param trainDoc
     * @return
     */
    private double getEuclidDistance(TextDocument toBeTested, TextDocument trainDoc) {
        Set<String> set1 = new HashSet<>(toBeTested.getWords());
        Set<String> set2 = new HashSet<>(trainDoc.getWords());
        Set<String> allWords = new HashSet<>(set1);
        allWords.addAll(set2);

        return ( 2 * allWords.size() - ( set1.size() + set2.size())  );
    }
}
