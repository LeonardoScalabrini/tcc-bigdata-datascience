package textmining;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RemoveStopWordTest {

    private final RemoveStopWord removeStopWord = new RemoveStopWord(Arrays.asList("stop1", "stop2", "stop3"));

    @Test
    public void deveRemoverPalavras(){
        String removed = removeStopWord.remove("stop1 stop2 stop3");

        Assert.assertTrue(StringUtils.isBlank(removed));
    }
}