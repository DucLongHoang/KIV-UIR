package SW.algorithms.features;

import SW.Feature;
import SW.TextDocument;

import java.util.List;

/**
 *
 */
public class TFIDF extends FeatureAlgorithm {
    List<Feature> features;

    /**
     * @param textDocuments
     */
    public TFIDF(List<TextDocument> textDocuments) {
        super(textDocuments);
    }

    @Override
    protected void makeFeatures() {

    }
}
