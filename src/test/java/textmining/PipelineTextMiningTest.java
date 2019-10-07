package textmining;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PipelineTextMiningTest {

    private PipelineTextMining pipelineTextMining = new PipelineTextMining();

    @Test
    public void deveMinerarTexto(){

        String text = "public MapMaker concurrencyLevel(@Anotation int concurrencyLevel) {\n" +
                "        checkArgument(\n" +
                "                concurrencyLevel >= 1, \"concurrency level (%s) must be at least 1\", concurrencyLevel);\n" +
                "        // GWT technically only supports concurrencyLevel == 1, but we silently\n" +
                "        // ignore other positive values.\n" +
                "        return this;\n" +
                "    }";

        List<String> result = pipelineTextMining.mine(text);

        List<String> expected = Arrays.asList("map", "maker", "concurrency", "level", "check", "argument", "must", "least", "gwt", "technically", "support", "silently", "ignore", "positive", "value");
        result.removeAll(expected);

        Assert.assertTrue(result.isEmpty());
    }
}