package textmining;

import ch.uzh.ifi.seal.changedistiller.model.entities.SourceCodeChange;
import org.junit.Assert;
import org.junit.Test;
import util.FileUtil;

import java.util.List;

public class ExtractChangeTest {

    private ExtractChange extractChange = new ExtractChange();

    @Test
    public void naoDeveExtrairAlteracoesDeCometarioEAnotacoes(){
        String oldRaw = FileUtil.loadJava("ComputationException_old.java");;
        String raw = FileUtil.loadJava("ComputationException_new.java");

        List<SourceCodeChange> codeChanges = extractChange.extract(oldRaw, raw);

        Assert.assertTrue(codeChanges.isEmpty());
    }

    @Test
    public void deveExtrairAlteracoes(){
        String oldRaw = FileUtil.loadJava("MapMaker_old.java");
        String raw = FileUtil.loadJava("MapMaker_new.java");

        List<SourceCodeChange> codeChanges = extractChange.extract(oldRaw, raw);

        Assert.assertEquals("", codeChanges.get(0).getChangedEntity());
        Assert.assertEquals("", codeChanges.get(1).getChangedEntity().getUniqueName());
        Assert.assertEquals("", codeChanges.get(2).getChangedEntity().getUniqueName());
        Assert.assertEquals("", codeChanges.get(3).getChangedEntity().getUniqueName());
        Assert.assertEquals("", codeChanges.get(4).getChangedEntity().getUniqueName());
        Assert.assertEquals("", codeChanges.get(5).getChangedEntity().getUniqueName());
        Assert.assertEquals("", codeChanges.get(6).getChangedEntity().getUniqueName());
        Assert.assertEquals("", codeChanges.get(7).getChangedEntity().getUniqueName());
    }
}