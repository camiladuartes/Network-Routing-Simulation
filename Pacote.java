public class Pacote {
    protected int cabeçalho = 0;
    protected int terminador = 0;
    protected String dados;
    protected String destino;

	public void setCabeçalho(int cabeçalho) {
		this.cabeçalho = cabeçalho;
	}
	public void setTerminador(int terminador) {
		this.terminador = terminador;
	}
	public String getDados() {
		return dados;
	}
	public void setDados(String dados) {
		this.dados = dados;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
}