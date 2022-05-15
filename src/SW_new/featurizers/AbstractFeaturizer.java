package SW_new.featurizers;

import SW_new.document.DAClass;
import SW_new.document.Document;
import SW_new.document.Feature;
import SW_new.document.FeatureVector;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractFeaturizer abstract class - to be extended by classes that can featurize a Documents
 * @author Long
 * @version 2.0
 */
public abstract class AbstractFeaturizer {
    public Collection<Document> docs;
    public Map<DAClass, FeatureVector> featuresByClassMap;

    /**
     * Constructor for AbstractFeaturizer
     * @param docs to be featurized
     */
    public AbstractFeaturizer(Collection<Document> docs) {
        this.docs = docs;
        this.featuresByClassMap = new HashMap<>();
        this.makeFeatures();

//        this.printDocsFeatureValues();
//        this.printClassFeatureValues();
    }

    /**
     * Method makes Features for all docs and DAClasses
     */
    public void makeFeatures() {
        makeFeaturesForDocs();
        makeFeaturesForClasses();
    }

    /**
     * Featurize one specific Document only
     */
    public abstract void featurizeDoc(Document doc);

    /**
     * Make features for every Document
     */
    public void makeFeaturesForDocs() {
        for (Document doc: docs) featurizeDoc(doc);
    }

    /**
     * Make features for every DAClass as sum of Document features
     */
    private void makeFeaturesForClasses() {
        FeatureVector tmpFeaturesOfClass;

        for (Document doc: docs) {
            tmpFeaturesOfClass = getFeatureVectorByDAClass(doc.type);
            tmpFeaturesOfClass.addVector(doc.features);
        }
    }

    /**
     * Method returns FeatureVector by DAClass
     * @param type of DAClass whose FeatureVector we want
     * @return FeatureVector of DAClass
     */
    public FeatureVector getFeatureVectorByDAClass(DAClass type) {
        if(featuresByClassMap.containsKey(type))
            return featuresByClassMap.get(type);

        // create new FeatureVector for new DAClass
        featuresByClassMap.put(type, new FeatureVector());
        return featuresByClassMap.get(type);
    }

    /**
     * Method prints Feature values of all DAClasses
     */
    public void printClassFeatureValues() {
        System.out.println();

        for(Map.Entry<DAClass, FeatureVector> entry: featuresByClassMap.entrySet()) {
            System.out.println(entry.getKey());

            System.out.print("[ ");
            for (Feature f: entry.getValue().vector.values()) {
                System.out.print(f.word + " " + f.value + " | ");
            }
            System.out.println("]");
        }

        System.out.println();
    }

    /**
     * Method prints Feature values of all Documents
     */
    public void printDocsFeatureValues() {
        System.out.println();

        for (Document doc: docs) {
            System.out.print(doc.type.toString() + ": [ ");
            for (Feature f: doc.features.vector.values()) {
                System.out.print(f.word + " " + f.value + " | ");
            }
            System.out.print("] \n");
        }

        System.out.println();
    }
}
