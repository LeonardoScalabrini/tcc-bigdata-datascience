package textmining;

import org.junit.Assert;
import org.junit.Test;

public class RemoveAnotationTest {

    private final RemoveAnotation removeAnotation = new RemoveAnotation();

    @Test
    public void deveRemoverAnotacoes(){

        String text = "public MapMaker concurrencyLevel(@Anotation int concurrencyLevel) {\n" +
                "        checkArgument(\n" +
                "                concurrencyLevel >= 1, \"concurrency level (%s) must be at least 1\", concurrencyLevel);\n" +
                "        // GWT technically only supports concurrencyLevel == 1, but we silently\n" +
                "        // ignore other positive values.\n" +
                "        return this;";

        String removed = removeAnotation.remove(text);

        Assert.assertEquals(text.replace("@Anotation", ""), removed);
    }

    @Test
    public void deveRemoverAnotacoesNoMetodo(){

        String text = "" +
                "@Anotation\n" +
                "public MapMaker concurrencyLevel(@Anotation int concurrencyLevel) {\n" +
                "        checkArgument(\n" +
                "                concurrencyLevel >= 1, \"concurrency level (%s) must be at least 1\", concurrencyLevel);\n" +
                "        // GWT technically only supports concurrencyLevel == 1, but we silently\n" +
                "        // ignore other positive values.\n" +
                "        return this;";

        String removed = removeAnotation.remove(text);

        Assert.assertEquals(text.replaceAll("@Anotation", ""), removed);
    }

    @Test
    public void deveRemoverAnotacoesNoMeioDoCodigo(){

        String text = "" +
                "@Anotation\n" +
                "public MapMaker concurrencyLevel(@Anotation int concurrencyLevel) {\n" +
                "        @Anotation\n" +
                "        checkArgument(\n" +
                "                concurrencyLevel >= 1, \"concurrency level (%s) must be at least 1\", concurrencyLevel);\n" +
                "        // GWT technically only supports concurrencyLevel == 1, but we silently\n" +
                "        // ignore other positive values.\n" +
                "        return this;";

        String removed = removeAnotation.remove(text);

        Assert.assertEquals(text.replaceAll("@Anotation", ""), removed);
    }
}