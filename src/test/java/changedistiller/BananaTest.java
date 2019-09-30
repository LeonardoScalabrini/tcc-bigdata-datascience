package changedistiller;

import org.junit.Assert;
import org.junit.Test;
import util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class BananaTest {

    @Test
    public void test(){
        String diff = FileUtil.loadJava("diff.txt");

        String pattern = Pattern.quote("\\" + "n");
        List<String> list = Arrays.asList(diff.split(pattern));
        System.out.println(list);

        String newJava = FileUtil.loadJava("ComputationException_new.java");

        List<String> news = new ArrayList<>(Arrays.asList(newJava.split("\r\n")));
        for (String s : list) {
            if (s.startsWith("+")) {
                String subString = s.substring(1, s.length());
                news.remove(subString);
            }
        }

        String old = FileUtil.loadJava("ComputationException_old.java");

        Assert.assertEquals(Arrays.asList(old.split("\r\n")), news);
    }

    @Test
    public void testOld(){
        String diff = FileUtil.loadJava("diff_map.txt");

        String pattern = Pattern.quote("\\" + "n");
        List<String> list = Arrays.asList(diff.split(pattern));
        System.out.println(list);

        String newJava = FileUtil.loadJava("MapMaker_new.java");

        List<String> news = new ArrayList<>(Arrays.asList(newJava.split("\r\n")));
        for (String s : list) {
            if (s.startsWith("+")) {
                String subString = s.substring(1, s.length());
                news.remove(subString);
            }
        }

        String old = FileUtil.loadJava("MapMaker_old.java");

        Assert.assertEquals(Arrays.asList(old.split("\r\n")), news);
    }
}
