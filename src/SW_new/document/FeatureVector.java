package SW_new.document;

import java.util.Map;
import java.util.TreeMap;

/**
 * FeatureVector class - represents a vector of a Document or DAClass
 * that contains all their respective Features
 * @author Long
 * @version 2.0
 */
public class FeatureVector {
    public Map<String, Feature> vector;

    /**
     * Constructor for FeatureVector
     */
    public FeatureVector() {
        this.vector = new TreeMap<>();
    }

    /**
     * Method add Feature to the vector.
     * Increment value of already present Feature or put it into vector
     * @param toBeAdded Feature
     */
    public void addFeatureToVector(Feature toBeAdded) {
        if (vector.containsKey(toBeAdded.word)) {
            // adding value of input Feature to already inside Feature
            vector.get(toBeAdded.word).value += toBeAdded.value;
        }
        else {
            vector.put(toBeAdded.word, toBeAdded.copy());
        }
    }

    /**
     * Method adds input FeatureVector and it's contents to this object
     * @param toBeAdded FeatureVector
     */
    public void addVector(FeatureVector toBeAdded) {
        for (Feature f: toBeAdded.vector.values()) {
            this.addFeatureToVector(f);
        }
    }

    /**
     * Method returns a Feature instance, they are equal by name
     * @param searched Feature
     * @return Feature reference
     */
    public Feature getFeature(Feature searched) {
        return vector.get(searched.word);
    }

    /**
     * Method returns number of all words in the FeatureVector
     * @return sum of all word counts
     */
    public int getTotalWordCount() {
        return vector.values().stream().mapToInt(f -> f.count).sum();
    }
}
