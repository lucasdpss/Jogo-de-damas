package jogo_de_damas;

public class Peca {
	private char cor, caractere;
	protected int iPos, jPos;
	protected Tabuleiro t;
	protected boolean capturou_no_movimento;
	boolean captura_obrigatoria;
	
	public Peca(char cor, int i, int j, Tabuleiro t){
		this.cor = cor;
		this.caractere = (char) (cor + 32); //Minuscula
		iPos = i;
		jPos = j;
		this.t = t;
	}
	
	public boolean mover(int id,int jd) { //recebe a posicao de destino da matriz
		return false;
	}
	
	public boolean mov_valido(int i,int j) {
		System.out.println("Isso nao era pra ser impresso");
		return false;
	}
	
	public char getCor(){
		return cor;
	}
	
	public char getCaractere() {
		return caractere;
	}
	
	public void setCaractere(char caractere) {
		this.caractere = caractere;
	}
	
	public boolean getCapturou_no_movimento() {
		return capturou_no_movimento;
	}
	
	public int algumMovimentoValido() { //veja o overload em PecaComum e PecaDama
		return 0;
	}
	
	public void setCapturouNoMovimento(boolean capturou_no_movimento) {
		this.capturou_no_movimento = capturou_no_movimento;
	}
}
