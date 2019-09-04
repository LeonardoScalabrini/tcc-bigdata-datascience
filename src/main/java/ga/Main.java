package ga;

public class Main {

    public static void main(String[] args){
        Populacao populacao = new Populacao(10);
        populacao.sobreviver();
        populacao.reproducao();
        populacao.mutacao();
        populacao.resultado();
    }
}
