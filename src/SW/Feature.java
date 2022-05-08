package SW;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Feature class - consists of a word and number of occurrences
 * @author Long
 * @version 1.0
 */
public class Feature implements Comparable<Feature>{
    /** A word this Feature encapsulates */
    private final String word;
    /** Number of occurrences in a given text document */
    private int count;
    /** Arbitrary value of a Feature, used by some FeatureAlgorithms */
    private double value;

    /**
     * Constructor for Feature
     * @param word the Feature is made for
     */
    public Feature(String word) {
        this.word = word;
        this.count = 0;
        this.value = 0;
    }

    /**
     * Getter for word
     * @return word of this Feature
     */
    public String getWord() {
        return word;
    }

    /**
     * Method increments occurrence by one
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * Getter for count
     * @return number of occurrence for the word
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter for value
     * @param value to set to
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Getter for value
     * @return an arbitrary value of feature
     */
    public double getValue() {
        return value;
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
}
