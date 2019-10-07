package textmining;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RemoveNumberTest {

    private final RemoveNumber removeNumber = new RemoveNumber();

    @Test
    public void deveRemoverNumeros(){
        List<String> removed = removeNumber.remove(new ArrayList<>(Arrays.asList("1", "3.52", "5.0", "5")));
        assertTrue(removed.isEmpty());
    }
}