package SW.algorithms.classification;

import SW.DAClass;
import SW.Feature;
import SW.FeatureVector;
import SW.TextDocument;
import SW.algorithms.features.FeatureAlgorithm;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Classifier abstract class - to be extended by specific child classes
 * with correct implementation of determining the DAClass by TextDocument
 * @author Long
 * @version 1.0
 */
public abstract class Classifier {
    protected final Map<DAClass, FeatureVector> vocabulary;
    protected final FeatureAlgorithm algorithm;
    protected final Set<Feature> uniqueFeatures;

    /**
     * Classifier abstract class - to be extended
     * by classes that can classify TextDocuments into DAClasses
     * @param algorithm that was used to extract Features across dataset
     */
    public Classifier(FeatureAlgorithm algorithm) {
        this.algorithm = algorithm;
        this.vocabulary = algorithm.getVocabulary();
        this.uniqueFeatures = algorithm.getUniqueFeatures();
        trainOnData();
    }

    /**
     * Method to be implemented by child classes. Trains the classifier on training data
     */
    protected abstract void trainOnData();

    /**
     * Method to be implemented by child classes. Return the DAClass of the TextDocument
     * @param document to be classified
     * @return the guessed DAClass
     */
    public abstract DAClass classify(TextDocument document);
}
