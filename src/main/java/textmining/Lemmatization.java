package textmining;

import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;

public class Lemmatization {

    public List<String> lemma(List<String> words){

        List<String> result = new ArrayList<>();
        words.forEach(s -> {
            result.addAll(new Sentence(s).lemmas());
        });

        return result;
    }
}
