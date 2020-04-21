package jogo_de_damas;

public class Peca {
	private char cor;
	protected int iPos, jPos;
	protected Tabuleiro t;
	protected boolean capturou_no_movimento;
	
	public Peca(char cor, int i, int j, Tabuleiro t){
		this.cor = cor;
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
	
	public boolean getCapturou_no_movimento() {
		return capturou_no_movimento;
	}
}
