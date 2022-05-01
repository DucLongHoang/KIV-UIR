package SW.features;

import SW.TextDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class BagOfWords extends FeatureAlgorithm {
    Map<String, Integer> vocabulary;

    /**
     *
     * @param textDocuments
     */
    public BagOfWords(List<TextDocument> textDocuments) {
        super(textDocuments);
        for(Map.Entry<String, Integer> entry: vocabulary.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     *
     */
    @Override
    protected void makeFeatures() {
        this.vocabulary = new HashMap<>();

        // go through all documents
        for(TextDocument document: textDocuments) {
            // go through every word in each document
            for(String word: document.getWords()) {
                if(vocabulary.containsKey(word)) {
                    incrementCount(word);
                }
                else {
                    vocabulary.put(word, 1);
                }
            }
        }
    }

    /**
     *
     * @param word
     */
    private void incrementCount(String word) {
        int count = vocabulary.get(word);
        count++;
        vocabulary.put(word, count);
    }
}
