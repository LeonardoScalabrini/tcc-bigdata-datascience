package textmining;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveStopWordTest {

    private final RemoveStopWord removeStopWord = new RemoveStopWord(Arrays.asList("stop1", "stop2", "stop3"));

    @Test
    public void deveRemoverPalavras(){
        List<String> removed = removeStopWord.remove(new ArrayList<>(Arrays.asList("stop1", "stop2", "stop3")));

        Assert.assertTrue(removed.isEmpty());
    }
}