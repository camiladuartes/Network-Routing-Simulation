import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        // lendo ips do arquivo "ips.txt"
        FileReader ips = new FileReader("ips.txt");
        BufferedReader lerArq = new BufferedReader(ips);
        String linha = lerArq.readLine();     
        Roteador matrizDeRoteadores[][] = new Roteador[3][3];
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Roteador roteador = new Roteador();
                roteador.setIP(linha);
                String mac = "3D:7F:" + j + j + ":DD:" + i + "D";
                roteador.setMAC(mac);
                matrizDeRoteadores[i][j] = roteador;
                linha = lerArq.readLine();
            }
        }

        // lendo as comunicações de "comunicações.txt" e fazendo o roteamento
        FileReader comunicacao = new FileReader("comunicação.txt");
        BufferedReader lerSegArq = new BufferedReader(comunicacao);
        String linha_ = lerSegArq.readLine();
        Queue<Pacote> dadosEntrada = new ArrayDeque<Pacote>();
        while(linha_ != null){
            // ler cada informação da linha
            String[] linhaComSplit = linha_.split(" ");
            String ipOrigem = linhaComSplit[0];
            String ipDestino = linhaComSplit[1];;
            int numPacotes = Integer.parseInt(linhaComSplit[2]);
            String dado = linhaComSplit[3];
            // ler roteador de entrada, criar pacotes
            for(int i = 0; i < numPacotes; i++){
                Pacote pacote = new Pacote();
                if(i == 0)
                    pacote.setCabeçalho(1);
                if(i == numPacotes-1)
                    pacote.setTerminador(1);
                pacote.setDados(dado);
                pacote.setDestino(ipDestino);
                dadosEntrada.add(pacote);
            }
            // colocar buffer(fila de pacotes) na portaLigadaRede do roteador de entrada
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(ipOrigem.equals(matrizDeRoteadores[i][j].getIP())){
                        matrizDeRoteadores[i][j].portaLigadaRede.setEntrada(dadosEntrada);
                        // passar pacotes pro roteamento(chamar roteamento)
                        while(matrizDeRoteadores[i][j].portaLigadaRede.getEntrada().isEmpty() == false){
                            Pacote pacoteDaVez = matrizDeRoteadores[i][j].portaLigadaRede.getEntrada().remove();
                            matrizDeRoteadores[i][j].portaLigadaRede.setSaida(pacoteDaVez);
                            matrizDeRoteadores[i][j].roteamento(pacoteDaVez, i, j, matrizDeRoteadores, dadosEntrada);
                        }
                        break;
                    }
                }
            }
            // ler próxima linha
            linha_ = lerSegArq.readLine();
        }      
        lerArq.close();
        lerSegArq.close();
    }
}