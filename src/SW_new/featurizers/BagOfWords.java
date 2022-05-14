package SW_new.featurizers;

import SW_new.document.Document;
import SW_new.document.Feature;
import SW_new.document.FeatureVector;

import java.util.Collection;

public class BagOfWords extends AbstractFeaturizer{

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
