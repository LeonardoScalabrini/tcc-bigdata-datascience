package textmining;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LemmatizationTest {

    private Lemmatization lemmatization = new Lemmatization();

    @Test
    public void deveLemmatizar(){
        List<String> result = lemmatization.lemma(Arrays.asList("converted name"));
        assertEquals("convert", result.get(0));
        assertEquals("name", result.get(1));
    }
}