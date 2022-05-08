package SW.algorithms.classification;

import SW.DAClass;
import SW.TextDocument;
import SW.algorithms.features.FeatureAlgorithm;

/**
 * K_NN class - extends Classifier
 * @author Long
 * @version 1.0
 */
public class K_NN extends Classifier {

    /**
     * Constructor for K_NN
     * @param algorithm
     */
    public K_NN(FeatureAlgorithm algorithm) {
        super(algorithm);
    }

    @Override
    protected void trainOnData() {

    }

    @Override
    public DAClass classify(TextDocument document) {
        return null;
    }
}
