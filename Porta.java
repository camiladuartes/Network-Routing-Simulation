import java.util.Queue; 

public class Porta {
    Queue<Pacote> entrada; //buffer
    Pacote saida;

    public Porta() {
        saida = new Pacote();
    }

    public Queue<Pacote> getEntrada() {
        return entrada;
    }

    public void setEntrada(Queue<Pacote> entrada) {
        this.entrada = entrada;
    }

    public Pacote getSaida() {
        return saida;
    }

    public void setSaida(Pacote saida) {
        this.saida = saida;
    }  
}