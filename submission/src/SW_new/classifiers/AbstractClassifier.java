package SW_new.classifiers;

import SW_new.featurizers.AbstractFeaturizer;
import SW_new.document.DAClass;
import SW_new.document.Document;

/**
 * AbstractClassifier abstract class - to be extended by classes that can classify sentences
 * and determine their DAClass
 * @author Long
 * @version 2.0
 */
public abstract class AbstractClassifier {
    public static final String TRAIN_STATS_PATH = "res/train_stats.txt";
    public final AbstractFeaturizer featurizer;

    /**
     * Constructor for AbstractClassifier
     * @param featurizer used to evaluate Features
     */
    public AbstractClassifier(AbstractFeaturizer featurizer) {
        this.featurizer = featurizer;
        this.train();
    }

    /**
     * Method classifies Document and returns its suspected DAClass
     * @param d Document to be classified
     * @return DAClass of input Document
     */
    public abstract DAClass classify(Document d);

    /**
     * Method trains classifier on training data
     */
    public abstract void train();
}
