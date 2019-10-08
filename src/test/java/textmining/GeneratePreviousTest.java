package textmining;

import org.junit.Test;
import util.FileUtil;

import static org.junit.Assert.assertEquals;

public class GeneratePreviousTest {

    private GeneratePrevious generatePrevious = new GeneratePrevious();

    @Test
    public void deveGerarAnteriorParaArquivoMap(){

        String diff = FileUtil.loadJava("diff_map.txt");
        String raw = FileUtil.loadJava("MapMaker_new.java");
        String oldRaw = FileUtil.loadJava("MapMaker_old.java").replaceAll("\\s","");

        String previous = generatePrevious.generate(diff, raw).replaceAll("\\s","");

        assertEquals(oldRaw, previous);
    }

    @Test
    public void deveGerarAnteriorParaArquivoComputation(){

        String diff = FileUtil.loadJava("diff.txt");
        String raw = FileUtil.loadJava("ComputationException_new.java");
        String oldRaw = FileUtil.loadJava("ComputationException_old.java").replaceAll("\\s","");;

        String previous = generatePrevious.generate(diff, raw).replaceAll("\\s","");;

        assertEquals(oldRaw, previous);
    }
}