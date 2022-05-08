package SW.algorithms.features;

import SW.DAClass;
import SW.Feature;
import SW.FeatureVector;
import SW.TextDocument;

import java.util.List;
import java.util.Map;

/**
 * Term-Frequency Inverse Document Frequency class - extends FeatureAlgorithm
 * This class can extract and create a List of Features from all text documents
 * @author Long
 * @version 1.0
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
        super.makeFeatures();

        // make document frequency for every word of every DAClass type
        for(FeatureVector fv: vocabulary.values()) {
            // setting up document frequency value for every Feature
            for(Feature f: fv.getFeatures()) {
                f.setValue(getTF(fv, f) * getIDF(f));
            }
        }
    }

    /**
     * Method returns the Term Frequency value of the Feature
     * in a given FeatureVector
     * @param vector to be searched in
     * @param feature to be searched
     * @return the fraction of number of occurrences of the Feature
     * and the total size of the FeatureVector
     */
    private double getTF(FeatureVector vector, Feature feature) {
        return ( (double) feature.getCount() / vector.getTotalWords() );
    }

    /**
     * Method returns the Inverse Document Frequency of a Feature
     * across all DAClasses
     * @param searched Feature
     * @return logarithm of fraction of number of DAClasses and number DAClasses
     * that contain the Feature
     */
    private double getIDF(Feature searched) {
        int numberOfDocs = vocabulary.size();
        int docsContaining = 0;

        for(FeatureVector fv: vocabulary.values()) {
            if (fv.containsFeature(searched))
                docsContaining++;
        }
        double fraction = ( (double) numberOfDocs / docsContaining );
        return Math.log(fraction);
    }
}
