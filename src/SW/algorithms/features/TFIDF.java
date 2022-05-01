package SW.algorithms.features;

import SW.TextDocument;

import java.util.List;

/**
 * Term-Frequency Inverse Document Frequency class - extends FeatureAlgorithm
 * This class can extract and create a List of Features from all text documents
 */
public class TFIDF extends FeatureAlgorithm {

    /**
     * Constructor for TFIDF
     * @param textDocuments to create Features from
     */
    public TFIDF(List<TextDocument> textDocuments) {
        super(textDocuments);
    }

    @Override
    protected void makeFeatures() {

    }
}
