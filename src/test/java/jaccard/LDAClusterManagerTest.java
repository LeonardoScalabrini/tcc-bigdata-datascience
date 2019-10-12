package jaccard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class LDAClusterManagerTest {

    private LDAClusterManager ldaClusterManager = new LDAClusterManager();

    @Test
    public void deveCriarCluster(){
        Map<Integer, Set<Integer>> map = ldaClusterManager.cluster("teste");

        Assert.assertEquals(map.get(0).toArray(), Arrays.asList(3, 6).toArray());
        Assert.assertEquals(map.get(1).toArray(), Arrays.asList(0, 1, 2, 4, 7, 8, 9, 10).toArray());
        Assert.assertEquals(map.get(2).toArray(), Arrays.asList(5).toArray());
    }

}