package jogo_de_damas;

public class PecaComum extends Peca {
	
	//This method override the mov_valido method on the super class 
	public boolean mov_valido(int i_destino, int j_destino) { //recebe a posicao de destino na matriz
		return true;
	}
}

