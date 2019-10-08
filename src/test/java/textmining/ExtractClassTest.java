package textmining;

import org.junit.Assert;
import org.junit.Test;
import util.FileUtil;

public class ExtractClassTest {

    private ExtractClass extractClass = new ExtractClass();

    @Test
    public void deveExtrairClasse(){

        String code = FileUtil.loadJava("MapMaker_new.java");

        String result = extractClass.extract(code);

        String expected = "public final class MapMaker {}";

        Assert.assertTrue(result.endsWith(expected));
    }
}