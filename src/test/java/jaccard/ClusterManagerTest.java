package jaccard;

import org.junit.Assert;
import org.junit.Test;
import textmining.FileUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClusterManagerTest {

    private ClusterManager clusterManager = new ClusterManager();

    @Test
    public void deveGerarCluster(){
        Map<String, Set<Integer>> map = clusterManager.cluster("guava");
        List<String> issues = FileUtil.load( "guava-issue.txt");
        Set<String> setIssues = new HashSet<>(issues);
        Assert.assertEquals(setIssues.size(), map.size());
    }

}