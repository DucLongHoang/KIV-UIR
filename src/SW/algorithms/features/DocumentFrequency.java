package SW.algorithms.features;

import SW.Feature;
import SW.TextDocument;

import java.util.List;

/**
 *
 */
public class DocumentFrequency extends FeatureAlgorithm {
    List<Feature> features;

    /**
     * @param textDocuments
     */
    public DocumentFrequency(List<TextDocument> textDocuments) {
        super(textDocuments);
    }

    @Override
    protected void makeFeatures() {

    }
}
