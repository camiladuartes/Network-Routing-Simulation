import java.util.Queue;
import java.io.FileWriter;//abrir ou criar arquivo pra gravaçao
import java.io.PrintWriter; //escrever no arquivo aberto ou criado
import java.io.IOException;

public class Roteador extends DispositivoDeRede implements Roteamento {
    protected Porta portaNorte;
    protected Porta portaSul;
    protected Porta portaLeste;
    protected Porta portaOeste;
    /* Pacotes inicialmente serão injetados nela, e quando chegar no
       roteador destin, é por essa porta que eles vão passar: */
    protected Porta portaLigadaRede;
    // protected Queue<String> buffers; //vai ser que nem uma fila

    public Roteador() {
        portaNorte = new Porta();
        portaSul = new Porta();
        portaLeste = new Porta();
        portaOeste = new Porta();
        portaLigadaRede = new Porta();
    }

    public Porta roteamento(Pacote pacote, int posicao_x, int posicao_y, Roteador matrizDeRoteadores[][], Queue<Pacote> dadosEntrada) {
        // achando indices do destino na matriz de roteadores
        for(int a = 0; a < 3; a++){
            for(int b = 0; b < 3; b++){
                // a e b se referem aos índices do roteador destino:
                if(pacote.getDestino().equals(matrizDeRoteadores[a][b].portaLigadaRede.getSaida().getDestino())){
                    // passando pacote de roteador em roteador até o roteador destino
                    int destino_x = a;
                    int destino_y = b;
                    Roteador roteadorAtualRoteamento = new Roteador();
                    roteadorAtualRoteamento = matrizDeRoteadores[destino_x][posicao_y];
                    while(true){
                        if(destino_y > posicao_y){
                            // manda pacote pela porta leste
                            roteadorAtualRoteamento.portaLeste.setSaida(pacote);
                            roteadorAtualRoteamento = matrizDeRoteadores[destino_x][destino_y+1];
                            roteadorAtualRoteamento.portaOeste.setEntrada(dadosEntrada);
                        }
                        else if(destino_y < posicao_y){
                            // manda pacote pela porta oeste
                            roteadorAtualRoteamento.portaOeste.setSaida(pacote);
                            roteadorAtualRoteamento = matrizDeRoteadores[destino_x][destino_y-1];
                            roteadorAtualRoteamento.portaLeste.setEntrada(dadosEntrada);
                        }
                        else if(posicao_x > destino_x){
                            // manda pacote pela porta norte
                            roteadorAtualRoteamento.portaNorte.setSaida(pacote);
                            roteadorAtualRoteamento = matrizDeRoteadores[destino_x+1][destino_y];
                            roteadorAtualRoteamento.portaSul.setEntrada(dadosEntrada);
                        }   
                        else if(posicao_x < destino_x){
                            // manda pacote pela porta sul
                            roteadorAtualRoteamento.portaSul.setSaida(pacote);
                            roteadorAtualRoteamento = matrizDeRoteadores[destino_x-1][destino_y];
                            roteadorAtualRoteamento.portaNorte.setEntrada(dadosEntrada);
                        }
                        else{
                            break;
                        }
                    }
                    /* Como chegou no roteador destino, agora
                    grava-se o conteúdo do pacote em um arquivo ip.txt */
                    try {
                        String nomeDoArquivo = pacote.getDestino() + ".txt";
                        FileWriter arquivo = new FileWriter(nomeDoArquivo, true);
                        PrintWriter gravarArq = new PrintWriter(arquivo);
                        gravarArq.printf(pacote.getDados() + "\n");
                        arquivo.close();
                    } catch(IOException e) {
                        System.out.println(e);
                    }
                    return roteadorAtualRoteamento.portaLigadaRede;
                }
            }
        }
        // caso não ache roteador destino, retorna porta default
        Porta defaultPorta = new Porta();
        return defaultPorta;
    }
} 
