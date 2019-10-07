package textmining;

import java.util.List;

public class PipelineTextMining {

    private static final String SPACE = "\\s";
    private RemoveAnotation removeAnotation = new RemoveAnotation();
    private Tokenizador tokenizador = new Tokenizador();
    private RemoveStopWord removeStopWordJava = new RemoveStopWord(FileUtil.load("stop_word_java.txt"));
    private DivideWord divideWord = new DivideWord();
    private RemoveStopWord removeStopWordIngles = new RemoveStopWord(FileUtil.load("stop_word_ingles.txt"));
    private RemoveNumber removeNumber = new RemoveNumber();
    private RemoveTwoCharacter removeTwoCharacter = new RemoveTwoCharacter();
    private Lemmatization lemmatization = new Lemmatization();

    public List<String> mine(String text){

        return lemmatization.lemma(
                removeTwoCharacter.remove(
                        removeNumber.remove(
                                removeStopWordIngles.remove(
                                        divideWord.divide(
                                                tokenizador.token(
                                                        removeStopWordJava.remove(
                                                                removeAnotation.remove(text))))))));
}
}
