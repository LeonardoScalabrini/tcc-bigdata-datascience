package textmining;

import edu.stanford.nlp.simple.Sentence;

import java.util.List;

public class Lemmatization {

    public List<String> lemma(String text){
        return new Sentence(text).lemmas();
    }
}
