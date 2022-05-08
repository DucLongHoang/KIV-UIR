package SW.algorithms.features;

import SW.DAClass;
import SW.Feature;
import SW.FeatureVector;
import SW.TextDocument;

import java.util.List;
import java.util.Map;

/**
 * Document Frequency class - extends FeatureAlgorithm
 * This class can make Features from text documents
 * @author Long
 * @version 1.0
 */
public class DocumentFrequency extends FeatureAlgorithm {

    /**
     * Constructor for DocumentFrequency
     * @param textDocuments to make Features from
     */
    public DocumentFrequency(List<TextDocument> textDocuments) {
        super(textDocuments);
    }

    @Override
    protected void makeFeatures() {
        super.makeFeatures();

        // make document frequency for every word of every DAClass type
        for(FeatureVector fv: vocabulary.values()) {
            // setting up document frequency value for every Feature
            for(Feature f: fv.getFeatures()) {
                f.setValue( (double) f.getCount() / fv.getTotalWords() );
            }
        }
    }
}
