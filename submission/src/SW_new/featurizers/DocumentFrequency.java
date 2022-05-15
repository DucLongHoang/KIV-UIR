package SW_new.featurizers;

import SW_new.document.Document;
import SW_new.document.Feature;

import java.util.Collection;

/**
 * DocumentFrequency class - extends AbstractFeaturizer
 * Sets Feature value equal to its count divided by the document word count
 * @author Long
 * @version 2.0
 */
public class DocumentFrequency extends AbstractFeaturizer{
    /**
     * See super
     * @param docs to featurize
     */
    public DocumentFrequency(Collection<Document> docs) {
        super(docs);
    }

    @Override
    public void featurizeDoc(Document doc) {
        // setting feature value = count / doc size
        for (Feature f: doc.features.vector.values()) {
            f.value = (double) f.count / doc.features.getTotalWordCount();
        }
    }
}
