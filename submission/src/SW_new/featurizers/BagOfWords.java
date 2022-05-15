package SW_new.featurizers;

import SW_new.document.Document;
import SW_new.document.Feature;

import java.util.Collection;

/**
 * BagOfWords class - extends AbstractFeaturizer
 * Sets Feature value equal to its count
 * @author Long
 * @version 2.0
 */
public class BagOfWords extends AbstractFeaturizer{
    /**
     * See super
     * @param docs to featurize
     */
    public BagOfWords(Collection<Document> docs) {
        super(docs);
    }

    @Override
    public void featurizeDoc(Document doc) {
        // setting feature value = feature count
        for (Feature f: doc.features.vector.values()) {
            f.value = f.count;
        }
    }
}
