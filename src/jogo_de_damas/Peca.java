package jogo_de_damas;

public class Peca {
	private char cor;
	protected int iPos, jPos;
	protected Tabuleiro t;
	
	public Peca(char cor, int i, int j, Tabuleiro t){
		this.cor = cor;
		iPos = i;
		jPos = j;
		this.t = t;
	}
	
	public void mover(int id,int jd) { //recebe a posicao de destino da matriz
		return;
	}
	
	public char getCor(){
		return cor;
	}
}
