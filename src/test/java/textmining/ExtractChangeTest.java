package textmining;

import org.junit.Assert;
import org.junit.Test;
import util.FileUtil;

public class ExtractChangeTest {

    private ExtractChange extractChange = new ExtractChange();

    @Test
    public void naoDeveExtrairAlteracoesDeCometarioEAnotacoes(){
        String oldRaw = FileUtil.loadJava("ComputationException_old.java");;
        String raw = FileUtil.loadJava("ComputationException_new.java");

        String codeChanges = extractChange.extract(oldRaw, raw, "");

        Assert.assertTrue(codeChanges.isEmpty());
    }

    @Test
    public void deveExtrairAlteracoes(){
        String oldRaw = FileUtil.loadJava("MapMaker_old.java");
        String raw = FileUtil.loadJava("MapMaker_new.java");

        String codeChanges = extractChange.extract(oldRaw, raw, "");

        Assert.assertFalse(codeChanges.isEmpty());
    }
}