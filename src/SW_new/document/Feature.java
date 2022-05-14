package SW_new.document;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Feature class - consists of a word and number of occurrences
 * @author Long
 * @version 1.0
 */
public class Feature implements Comparable<Feature>{
    /** A word this Feature encapsulates */
    public final String word;
    /** Number of occurrences in a given text document */
    public int count;
    /** Arbitrary value of a Feature, used by some FeatureAlgorithms */
    public double value;

    /**
     * Constructor for Feature
     * @param word the Feature is made for
     */
    public Feature(String word) {
        this.word = word;
        this.count = 1;
        this.value = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature other = (Feature) o;
        return this.word.equals(other.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public int compareTo(@NotNull Feature other) {
        return this.word.compareTo(other.word);
    }

    public Feature copy() {
        Feature newFeature = new Feature(this.word);
        newFeature.count = this.count;
        newFeature.value = this.value;

        return newFeature;
    }
}
