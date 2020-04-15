package jogo_de_damas;

public class PecaDama extends Peca { 
	
	public PecaDama(char cor, int i, int j, Tabuleiro t){
		super(cor, i, j, t);
	}
	
	public boolean mov_valido(int i_destino, int j_destino) { //recebe a posicao de destino na matriz
		return true;
	}
	
}
