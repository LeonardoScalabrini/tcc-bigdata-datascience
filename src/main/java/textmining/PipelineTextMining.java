package textmining;

import java.util.List;

public class PipelineTextMining {

    private static final String SPACE = "\\s";
    private RemoveStopWord removeStopWordJava = new RemoveStopWord(FileUtil.load("stop_word_java.txt"));
    private Tokenizador tokenizador = new Tokenizador();
    private DivideWord divideWord = new DivideWord();
    private RemoveStopWord removeStopWordIngles = new RemoveStopWord(FileUtil.load("stop_word_ingles.txt"));
    private RemoveAnotation removeAnotation = new RemoveAnotation();
    private RemoveNumber removeNumber = new RemoveNumber();
    private RemoveTwoCharacter removeTwoCharacter = new RemoveTwoCharacter();
    private Lemmatization lemmatization = new Lemmatization();

    public List<String> mine(String text){

        StringBuilder stringBuilder = new StringBuilder();
        tokenizador.token(removeAnotation.remove(removeStopWordJava.remove(text))).forEach(word -> {
            divideWord.divide(word).forEach(s -> {
                stringBuilder.append(s).append(SPACE);
            });
        });

        return lemmatization.lemma(
                removeTwoCharacter.remove(
                        removeNumber.remove(
                                removeStopWordIngles.remove(
                                        stringBuilder.toString().toLowerCase()))));
    }
}
