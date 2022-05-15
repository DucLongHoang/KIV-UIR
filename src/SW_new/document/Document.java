package SW_new.document;

import java.util.List;

/**
 * Document class - represents a line from input file
 * consists of a List of words and a DAClass type
 * @author Long
 * @version 2.0
 */
public class Document {
    public final DAClass type;
    public final FeatureVector features;

    /**
     * Constructor for Document
     * @param type of DAClass
     * @param words of a sentence
     */
    public Document(DAClass type, List<String> words) {
        this.type = type;
        this.features = new FeatureVector();

        for(String word: words) {
            this.features.addFeatureToVector(new Feature(word));
        }
    }
}
