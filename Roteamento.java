import java.util.Queue;

public interface Roteamento {

    public Porta roteamento(Pacote pacote, int i, int j, Roteador matrizDeRoteadores[][], Queue<Pacote> dadosEntrada);
}