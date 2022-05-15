package SW_new.featurizers;

import SW_new.document.Document;
import SW_new.document.Feature;
import SW_new.document.FeatureVector;

import java.util.Collection;

/**
 * TF_IDF class - extends AbstractFeaturizer
 * Sets Feature value equal to tf * idf
 * tf = Feature count divided by the document word count
 * idf = logarithm of (total number of docs divided by docs containing Feature)
 * @author Long
 * @version 2.0
 */
public class TF_IDF extends AbstractFeaturizer{
    /**
     * See super
     * @param docs to featurize
     */
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

    /**
     * Method return term frequency
     * @param f Feature
     * @param fv FeatureVector
     * @return tf
     */
    public double tf(Feature f, FeatureVector fv) {
        return (double) f.count / fv.getTotalWordCount();
    }

    /**
     * Method returns inverse document frequency
     * @param f Feature
     * @return idf
     */
    public double idf(Feature f) {
        int docsContaining = (int) docs
                .stream()
                .map(d -> d.features.vector)
                .filter(docVector -> docVector.containsKey(f.word)).count();

        return Math.log( (double) docs.size() / docsContaining);
    }
}
