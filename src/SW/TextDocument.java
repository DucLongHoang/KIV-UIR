package SW;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TextDocument class - defined by its type and the words it is made of
 */
public class TextDocument {
    private DAClass type;
    private List<String> words;

    /**
     * Constructor for TextDocument
     * @param type of DAClass
     * @param words it consists of, all made to lower case
     */
    public TextDocument(DAClass type, List<String> words) {
        this.type = type;
        this.words = words
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    /**
     * Getter for type
     * @return type of DAClass
     */
    public DAClass getType() {
        return type;
    }

    /**
     * Getter for words
     * @return all words in document
     */
    public List<String> getWords() {
        return words;
    }
}
