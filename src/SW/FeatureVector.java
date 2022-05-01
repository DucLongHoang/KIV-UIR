package SW;

import java.util.TreeSet;

/**
 * FeatureVector class - backed by a TreeSet to keep Features ordered
 */
public class FeatureVector {
    private final TreeSet<Feature> features;

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
    public TreeSet<Feature> getFeatures() {
        return features;
    }

    /**
     * Method adds the Feature to the vector if not present
     * otherwise it does nothing
     * @param word to be included as a feature
     */
    public void addFeatureToVector(String word) {
        Feature searched = new Feature(word);
        features.add(searched);
    }

    /**
     * Method returns a vector containing all names of features
     * @return String array representation of all features
     */
    public String getFeatureVector() {
        StringBuilder sb = new StringBuilder("[");

        for(Feature f: features) {
            sb.append(f.getWord()).append(",");
        }
        sb.append("]");

        return sb.toString();
    }
}
