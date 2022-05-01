package SW;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Feature class - consists of a word and number of occurrences
 */
public class Feature implements Comparable<Feature>{
    private final String word;
    private int count;

    /**
     * Constructor for Feature
     * @param word the Feature is made for
     */
    public Feature(String word) {
        this.word = word;
        this.count = 0;
    }

    /**
     * Method increments occurrence by one
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * Getter for word
     * @return word of this Feature
     */
    public String getWord() {
        return word;
    }

    /**
     * Getter for count
     * @return number of occurrence for the word
     */
    public int getCount() {
        return count;
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
