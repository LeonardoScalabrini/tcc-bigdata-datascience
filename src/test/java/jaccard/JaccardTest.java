package jaccard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class JaccardTest {

    private Jaccard jaccard = new Jaccard();

    @Test
    public void deveCalcularSemelhanca() {
        Double index = jaccard.index(new HashSet<Integer>(Arrays.asList(1, 2, 3)), new HashSet<Integer>(Arrays.asList(1, 2, 3)));
        Assert.assertEquals(Double.valueOf(1), index);
    }

    @Test
    public void deveCalcularSemelhancaMeio() {
        Double index = jaccard.index(new HashSet<Integer>(Arrays.asList(1, 2, 3)), new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assert.assertEquals(Double.valueOf(.5), index);
    }

    @Test
    public void deveCalcularSemelhancaZero() {
        Double index = jaccard.index(new HashSet<Integer>(Arrays.asList(1, 2, 3)), new HashSet<Integer>(Arrays.asList(4, 5, 6)));
        Assert.assertEquals(Double.valueOf(0), index);
    }

    @Test
    public void deveCalcularSemelhancaZeroCaseASejaVazio() {
        Double index = jaccard.index(Collections.<Integer>emptySet(), new HashSet<Integer>(Arrays.asList(4, 5, 6)));
        Assert.assertEquals(Double.valueOf(0), index);
    }

    @Test
    public void deveCalcularSemelhancaZeroCaseBSejaVazio() {
        Double index = jaccard.index(new HashSet<Integer>(Arrays.asList(1, 2, 3)), Collections.<Integer>emptySet());
        Assert.assertEquals(Double.valueOf(0), index);
    }

    @Test
    public void deveCalcularSemelhancaZeroCaseAeBSejaVazio() {
        Double index = jaccard.index(Collections.<Integer>emptySet(), Collections.<Integer>emptySet());
        Assert.assertEquals(Double.valueOf(0), index);
    }

    @Test
    public void deveCalcularSemelhancaZeroCaseASejaNulo() {
        Double index = jaccard.index(null, new HashSet<Integer>(Arrays.asList(4, 5, 6)));
        Assert.assertEquals(Double.valueOf(0), index);
    }

    @Test
    public void deveCalcularSemelhancaZeroCaseBSejaNulo() {
        Double index = jaccard.index(new HashSet<Integer>(Arrays.asList(1, 2, 3)), null);
        Assert.assertEquals(Double.valueOf(0), index);
    }

    @Test
    public void deveCalcularSemelhancaZeroCaseAeBSejaNulo() {
        Double index = jaccard.index(null, null);
        Assert.assertEquals(Double.valueOf(0), index);
    }
}