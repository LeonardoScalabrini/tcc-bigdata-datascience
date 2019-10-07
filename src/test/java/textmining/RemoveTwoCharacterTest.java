package textmining;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RemoveTwoCharacterTest {

    private RemoveTwoCharacter removeTwoCharacter = new RemoveTwoCharacter();

    @Test
    public void deveRemoverUmCaracter(){
        List<String> result = removeTwoCharacter.remove(new ArrayList<>(Arrays.asList("a")));
        assertTrue(result.isEmpty());
    }

    @Test
    public void deveRemoverDoisCaracteres(){
        List<String> result = removeTwoCharacter.remove(new ArrayList<>(Arrays.asList("aa")));
        assertTrue(result.isEmpty());
    }
}