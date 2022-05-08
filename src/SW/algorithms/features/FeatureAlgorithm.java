package SW.algorithms.features;

import SW.DAClass;
import SW.Feature;
import SW.FeatureVector;
import SW.TextDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FeatureAlgorithm abstract class
 * Child classes extending this class will be able to make Features
 * from text documents
 * @author Long
 * @version 1.0
 */
public abstract class FeatureAlgorithm {
    /** Every DAClass type has its own vector of features */
    protected Map<DAClass, FeatureVector> vocabulary;
    /** Reference to all text documents */
    protected List<TextDocument> textDocuments;

    /**
     * Constructor for FeatureAlgorithm
     * instantly makes features/vocabulary on initialization
     * @param textDocuments to make Features from
     */
    public FeatureAlgorithm(List<TextDocument> textDocuments) {
        this.vocabulary = new HashMap<>();
        this.textDocuments = textDocuments;
        makeFeatures();

//        for(Map.Entry<DAClass, FeatureVector> entry: vocabulary.entrySet()) {
//            System.out.println();
//            System.out.println(entry.getKey().toString() + " : " + entry.getValue().getFeatureNamesVector());
//        }
    }

    /**
     * Method used to make Features for every DAClass
     */
    protected void makeFeatures() {
        FeatureVector tmpVector;
        Feature tmpFeature;

        // loop through all documents to make FeatureVectors for every DAClass
        for(TextDocument document: textDocuments) {
            tmpVector = getFeatureVectorByDAClass(document.getType());
            // adding Features to FeatureVector, and increase occurrence count of Feature
            for(String word: document.getWords()) {
                tmpFeature = new Feature(word);
                tmpVector.addFeatureToVector(tmpFeature);
            }
        }
    }

    /**
     * Method returns a FeatureVector if it exists,
     * otherwise it creates a new one and returns it
     * @param type of DAClass FeatureVector to be returned
     * @return already created FeatureVector or return a newly created one
     */
    private FeatureVector getFeatureVectorByDAClass(DAClass type) {
        if(vocabulary.containsKey(type)) return vocabulary.get(type);
        // create new FeatureVector for new DAClass
        vocabulary.put(type, new FeatureVector());
        return vocabulary.get(type);
    }

    /**
     * Getter for vocabulary
     * @return vocabulary
     */
    public Map<DAClass, FeatureVector> getVocabulary() {
        return this.vocabulary;
    }

    /**
     * Method returns amount of all occurrences of parameter
     * @param word to be found
     * @return number word is present in the whole vocabulary
     */
    public int getTotalWordOccurrence(String word) {
        return vocabulary.values()
                .stream()
                .mapToInt(fv -> fv.getWordOccurrence(word))
                .sum();
    }

    /**
     * Method returns number of all words in all FeatureVectors
     * @return count of all words
     */
    public int getAllWordsOccurrence() {
        return vocabulary.values()
                .stream()
                .mapToInt(FeatureVector::getTotalWords)
                .sum();
    }
}
