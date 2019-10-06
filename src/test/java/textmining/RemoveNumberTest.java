package textmining;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveNumberTest {

    private final RemoveNumber removeNumber = new RemoveNumber();

    @Test
    public void deveRemoverNumeros(){

        String text = "public MapMaker concurrencyLevel(3.52 int concurrencyLevel) {\n" +
                "        checkArgument(\n" +
                "                concurrencyLevel >= 1, \"concurrency level (%s) must be at least 1\", concurrencyLevel);\n" +
                "        // GWT technically only supports concurrencyLevel == 1, but we silently\n" +
                "        // 5 5.0 ignore other positive values.\n" +
                "        return this;\n" +
                "    }";
        String removed = removeNumber.remove(text);

        assertEquals(text.replace("1", "")
                .replace("3.52", "")
                .replace("5.0", "")
                .replace("5", ""), removed);
    }
}