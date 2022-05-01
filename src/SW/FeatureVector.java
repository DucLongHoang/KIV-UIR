package SW;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * FeatureVector class - backed by a TreeSet to keep Features ordered
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
     * Method adds the Feature to the vector if not present
     * otherwise it does nothing
     *
     * @param toAdd to be included as a feature
     */
    public void addFeatureToVector(Feature toAdd, boolean increaseCount) {
        features.add(toAdd);
        if(increaseCount) {
            Feature tmp = getFeature(toAdd);
            assert tmp != null;
            tmp.incrementCount();
        }
    }

    /**
     * Method returns a vector containing all names of features
     * @return String array representation of all features
     */
    public String getFeatureNamesVector() {
        StringBuilder sb = new StringBuilder("[");

        for(Feature f: features) {
            sb.append(f.getWord()).append(",");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Method returns a vector of Feature counts or values.
     * Works by incrementing this FeatureVector, returning it
     * and changing it back to its original state (all zeroes)
     * @param inputDocument to make a FeatureVector from
     * @return vector of Feature counts or values
     */
    public double[] getFeatureVector(List<String> inputDocument) {
        double[] result = new double[features.size()];
        Feature tmp;

        // looping through all words of the input document
        for(String word: inputDocument) {
            tmp = new Feature(word);
            if(features.contains(tmp)) {
                tmp = getFeature(tmp);
                assert tmp != null;
                tmp.incrementCount();
            }
        }

        Iterator<Feature> it = features.iterator();
        for (int i = 0; it.hasNext(); i++) {
            result[i] = it.next().getCount();
        }

        nullifyFeatureVector();
        return result;
    }

    /**
     * Nullifies all counts and values of every Feature from the FeatureVector
     */
    private void nullifyFeatureVector() {
        features.forEach(Feature::zeroCountAndValue);
    }

    /**
     * Method iterates the TreeSet and returns the Feature
     * @param searched Feature to be returned from the TreeSet
     * @return Feature or null if not found
     */
    private Feature getFeature(Feature searched) {
        for(Feature f: features) {
            if(f.equals(searched)) return f;
        }
        return null;
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
}
