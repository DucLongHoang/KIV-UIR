package SW.algorithms.features;

import SW.TextDocument;

import java.util.List;

/**
 *
 */
public abstract class FeatureAlgorithm {

    /**
     *
     */
    protected List<TextDocument> textDocuments;

    /**
     *
     * @param textDocuments
     */
    public FeatureAlgorithm(List<TextDocument> textDocuments) {
        this.textDocuments = textDocuments;
        makeFeatures();
    }

    /**
     *
     */
    protected abstract void makeFeatures();
}
