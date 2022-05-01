package SW;

import java.util.List;

/**
 *
 */
public class TextDocument {
    private DAClass type;
    private List<String> words;

    /**
     *
     * @param type
     * @param words
     */
    public TextDocument(DAClass type, List<String> words) {
        this.type = type;
        this.words = words;
    }

    public DAClass getType() {
        return type;
    }

    public List<String> getWords() {
        return words;
    }
}
