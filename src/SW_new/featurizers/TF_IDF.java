package SW_new.featurizers;

import SW_new.document.Document;
import SW_new.document.Feature;
import SW_new.document.FeatureVector;

import java.util.Collection;

public class TF_IDF extends AbstractFeaturizer{

    public TF_IDF(Collection<Document> docs) {
        super(docs);
    }

    @Override
    public void featurizeDoc(Document doc) {
        FeatureVector fv;
        // setting feature value = tf (like in document frequency) * idf
        for (Feature f: doc.features.vector.values()) {
            fv = doc.features;
            f.value = tf(f, fv) * idf(f);
        }
    }

    public double tf(Feature f, FeatureVector fv) {
        return (double) f.count / fv.getTotalWordCount();
    }

    public double idf(Feature f) {
        int docsContaining = (int) docs
                .stream()
                .map(d -> d.features.vector)
                .filter(docVector -> docVector.containsKey(f.word)).count();

        return Math.log( (double) docs.size() / docsContaining);
    }
}
