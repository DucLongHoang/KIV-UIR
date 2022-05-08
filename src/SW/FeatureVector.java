package SW;

import java.util.Set;
import java.util.TreeSet;

/**
 * FeatureVector class - backed by a TreeSet to keep Features ordered
 * @author Long
 * @version 1.0
 */
public class FeatureVector {
    private final Set<Feature> features;

    /**
     * Constructor for FeatureVector
     */
    public FeatureVector() {
        this.features = new TreeSet<>();
    }

    /**
     * Getter for features
     * @return features of this FeatureVector
     */
    public Set<Feature> getFeatures() {
        return features;
    }

    /**
     * Method returns whether the Feature is contained in the FeatureVector or not
     * @param searched for Feature
     * @return true if FeatureVector contains the Feature otherwise false
     */
    public boolean containsFeature(Feature searched) {
        return features.contains(searched);
    }

    /**
     * Method adds the Feature to the vector if not present,
     * otherwise it does nothing.
     * Depending on parameter, increment the occurrence of the Feature
     *
     * @param toAdd to be included as a feature
     */
    public void addFeatureToVector(Feature toAdd) {
        features.add(toAdd);
        // increase occurrence of Feature
        Feature tmp = getFeature(toAdd);
        assert tmp != null;
        tmp.incrementCount();
    }

    /**
     * Method returns a vector containing all names of features
     * @return String array representation of all features
     */
    public String getFeatureNamesVector() {
        StringBuilder sb = new StringBuilder("[");

        for(Feature f: features) {
            sb.append(f.getWord()).append(":");
            sb.append(f.getCount()).append(",");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Method iterates the TreeSet and returns the Feature
     * @param searched Feature to be returned from the TreeSet
     * @return Feature or null if not found
     */
    public Feature getFeature(Feature searched) {
        for(Feature f: features) {
            if(f.equals(searched)) return f;
        }
        return null;
    }

    /**
     * Method returns total value of all features in the vector
     * @return total value of features
     */
    public double getTotalVectorValue() {
        return features
                .stream()
                .mapToDouble(Feature::getValue)
                .sum();
    }

    /**
     * Method returns total number of words in document
     * @return total number of words
     */
    public int getTotalWords() {
        return features
                .stream()
                .mapToInt(Feature::getCount)
                .sum();
    }

    /**
     * Method return number of occurrences of word
     * @param word to be searched
     * @return times word occurs in this FeatureVector
     */
    public int getWordOccurrence(String word) {
        int wordOccurrence = 0;
        for(Feature f: features) {
            if (f.getWord().equals(word))
                wordOccurrence += f.getCount();
        }
        return wordOccurrence;
    }
}
