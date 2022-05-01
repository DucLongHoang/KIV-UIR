package SW.features;

import SW.DAClass;
import SW.FeatureVector;
import SW.TextDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BagOfWords class - extends FeatureAlgorithm to create features from TextDocuments
 */
public class BagOfWords extends FeatureAlgorithm {
    Map<DAClass, FeatureVector> vocabulary;

    /**
     * Constructor for BagOfWords
     * @param textDocuments to extract Features from
     */
    public BagOfWords(List<TextDocument> textDocuments) {
        super(textDocuments);
        for(Map.Entry<DAClass, FeatureVector> entry: vocabulary.entrySet()) {
            System.out.println();
            System.out.println(entry.getKey().toString() + " : " + entry.getValue().getFeatureVector());
        }
    }

    /**
     * Method creates FeatureVectors for every type of DAClass
     */
    @Override
    protected void makeFeatures() {
        this.vocabulary = new HashMap<>();
        FeatureVector featVector;

        for(TextDocument document: textDocuments) {
            featVector = getFeatureVector(document.getType());
            for(String word: document.getWords()) {
                featVector.addFeatureToVector(word);
            }
        }
    }

    /**
     * Method returns a FeatureVector if it exists,
     * otherwise it creates a new one and returns it
     * @param type of DAClass FeatureVector to be returned
     * @return already created FeatureVector or return a newly created one
     */
    private FeatureVector getFeatureVector(DAClass type) {
        if(vocabulary.containsKey(type)) return vocabulary.get(type);

        vocabulary.put(type, new FeatureVector());
        return vocabulary.get(type);
    }
}
