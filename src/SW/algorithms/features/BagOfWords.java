package SW.algorithms.features;

import SW.Feature;
import SW.FeatureVector;
import SW.TextDocument;

import java.util.List;

/**
 * BagOfWords class - extends FeatureAlgorithm to create features from TextDocuments
 * @author Long
 * @version 1.0
 */
public class BagOfWords extends FeatureAlgorithm {

    /**
     * Constructor for BagOfWords
     * @param textDocuments to extract Features from
     */
    public BagOfWords(List<TextDocument> textDocuments) {
        super(textDocuments);
    }

    /**
     * Method creates FeatureVectors for every type of DAClass
     */
    @Override
    protected void makeFeatures() {
        super.makeFeatures();

        // make document frequency for every word of every DAClass type
        for(FeatureVector fv: vocabulary.values()) {
            // setting up value for every Feature, in BoW it is its count
            for(Feature f: fv.getFeatures()) {
                f.setValue(f.getCount());
            }
        }
    }
}
