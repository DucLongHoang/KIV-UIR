package SW.algorithms.features;

import SW.DAClass;
import SW.Feature;
import SW.FeatureVector;
import SW.TextDocument;

import java.util.List;
import java.util.Map;

/**
 * BagOfWords class - extends FeatureAlgorithm to create features from TextDocuments
 */
public class BagOfWords extends FeatureAlgorithm {

    /**
     * Constructor for BagOfWords
     * @param textDocuments to extract Features from
     */
    public BagOfWords(List<TextDocument> textDocuments) {
        super(textDocuments);
        for(Map.Entry<DAClass, FeatureVector> entry: vocabulary.entrySet()) {
            System.out.println();
            System.out.println(entry.getKey().toString() + " : " + entry.getValue().getFeatureNamesVector());
        }
    }

    /**
     * Method creates FeatureVectors for every type of DAClass
     */
    @Override
    protected void makeFeatures() {
        FeatureVector featVector;
        Feature tmpFeature;

        // loop through all documents to make FeatureVectors for every DAClass
        for(TextDocument document: textDocuments) {
            featVector = getFeatureVector(document.getType());
            // adding Features to FeatureVector
            for(String word: document.getWords()) {
                tmpFeature = new Feature(word);
                featVector.addFeatureToVector(tmpFeature, false);
            }
        }
    }
}
