package SW.algorithms.features;

import SW.DAClass;
import SW.FeatureVector;
import SW.TextDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FeatureAlgorithm abstract class
 * Child classes extending this class will be able to make Features
 * from all text documents
 * @author Long
 * @version 1.0
 */
public abstract class FeatureAlgorithm {
    /** Every DAClass type has its own vector of features */
    protected Map<DAClass, FeatureVector> vocabulary;
    /** Reference to all text documents */
    protected List<TextDocument> textDocuments;

    /**
     * Constructor for FeatureAlgorithm
     * instantly makes features/vocabulary on initialization
     * @param textDocuments to make Features from
     */
    public FeatureAlgorithm(List<TextDocument> textDocuments) {
        this.vocabulary = new HashMap<>();
        this.textDocuments = textDocuments;
        makeFeatures();
    }

    /**
     * Method used to make Features for every DAClass
     */
    protected abstract void makeFeatures();

    /**
     * Method returns a FeatureVector if it exists,
     * otherwise it creates a new one and returns it
     * @param type of DAClass FeatureVector to be returned
     * @return already created FeatureVector or return a newly created one
     */
    protected FeatureVector getFeatureVector(DAClass type) {
        if(vocabulary.containsKey(type)) return vocabulary.get(type);
        // create new FeatureVector for new DAClass
        vocabulary.put(type, new FeatureVector());
        return vocabulary.get(type);
    }

    public double[] getFeatureVectorFromDocument(List<String> inputDocument) {
        FeatureVector fv = vocabulary.get(DAClass.OR_QUESTION);
        return fv.getFeatureVector(inputDocument);
    }
}
