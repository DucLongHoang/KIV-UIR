package SW_new.document;

import java.util.List;

public class Document {
    public final DAClass type;
    public final FeatureVector features;

    public Document(DAClass type, List<String> words) {
        this.type = type;
        this.features = new FeatureVector();

        for(String word: words) {
            this.features.addFeatureToVector(new Feature(word));
        }
    }
}
