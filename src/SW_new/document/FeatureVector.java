package SW_new.document;

import java.util.Map;
import java.util.TreeMap;

public class FeatureVector {
    public Map<String, Feature> vector;

    public FeatureVector() {
        this.vector = new TreeMap<>();
    }

    public void addFeatureToVector(Feature toBeAdded) {
        if (vector.containsKey(toBeAdded.word)) {
            // adding value of input Feature to already inside Feature
            vector.get(toBeAdded.word).value += toBeAdded.value;
        }
        else {
            vector.put(toBeAdded.word, toBeAdded.copy());
        }
    }

    public void addVector(FeatureVector toBeAdded) {
        for (Feature f: toBeAdded.vector.values()) {
            this.addFeatureToVector(f);
        }
    }

    public Feature getFeature(Feature searched) {
        return vector.get(searched.word);
    }

    public int getTotalWordCount() {
        return vector.values().stream().mapToInt(f -> f.count).sum();
    }
}
