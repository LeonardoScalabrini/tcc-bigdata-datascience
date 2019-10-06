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
        String oldRaw = "public class MapMaker {}";
        String raw = FileUtil.loadJava("MapMaker_new.java");

        List<SourceCodeChange> codeChanges = extractChange.extract(oldRaw, raw);

        printChanges(raw, codeChanges, 0);
        printChanges(raw, codeChanges, 1);
        printChanges(raw, codeChanges, 2);
        printChanges(raw, codeChanges, 3);
        printChanges(raw, codeChanges, 4);
        printChanges(raw, codeChanges, 5);
    }

    private void printChanges(String raw, List<SourceCodeChange> codeChanges, int index) {
        System.out.println(codeChanges.get(index).getParentEntity().getUniqueName());
        System.out.println(raw.substring(codeChanges.get(index).getChangedEntity().getStartPosition(), codeChanges.get(index).getChangedEntity().getEndPosition()));
    }
}