package textmining;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DivideWordTest {

    private DivideWord divideWord = new DivideWord();

    @Test
    public void deveDividirPalavraCamelCase(){
        List<String> words = divideWord.divide("convertYourName");
        Assert.assertEquals("convert", words.get(0));
        Assert.assertEquals("your", words.get(1));
        Assert.assertEquals("name", words.get(2));
    }

    @Test
    public void deveDividirPalavraUpperCase(){
        List<String> words = divideWord.divide("ConvertYourName");
        Assert.assertEquals("convert", words.get(0));
        Assert.assertEquals("your", words.get(1));
        Assert.assertEquals("name", words.get(2));
    }

    @Test
    public void deveDividirPalavraUpperCaseSequentes(){
        List<String> words = divideWord.divide("ERPTypeStatus");
        Assert.assertEquals("erp", words.get(0));
        Assert.assertEquals("type", words.get(1));
        Assert.assertEquals("status", words.get(2));
    }

    @Test
    public void naoDeveDividirUnicaPalavraUpperCase(){
        List<String> words = divideWord.divide("ERP");
        Assert.assertEquals("erp", words.get(0));
    }

    @Test
    public void naoDeveDividirUnicaPalavraLowerCase(){
        List<String> words = divideWord.divide("convert");
        Assert.assertEquals("convert", words.get(0));
    }

}