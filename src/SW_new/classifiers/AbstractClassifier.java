package SW_new.classifiers;

import SW_new.featurizers.AbstractFeaturizer;
import SW_new.document.DAClass;
import SW_new.document.Document;

public abstract class AbstractClassifier {
    public static final String TRAIN_STATS_PATH = "train_stats.txt";

    public final AbstractFeaturizer featurizer;
//    final Set<Feature> uniqueFeatures;

    public AbstractClassifier(AbstractFeaturizer featurizer) {
        this.featurizer = featurizer;
        this.train();
    }

    public abstract DAClass classify(Document d);

    public abstract void train();
}
