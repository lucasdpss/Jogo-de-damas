package jogo_de_damas;

public class PecaComum extends Peca {
	
	public PecaComum(char cor, int i, int j, Tabuleiro t){
		super(cor, i, j, t);
	}
	
	private boolean mov_valido(int id,int jd) {
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		
		if(diferencaI != diferencaJ) return false; //Precisa ser sempre diagonal
		if(diferencaI > 2) return false;  //Move no maximo 2 posicoes em cada direcao
		if(diferencaI == 0) return false; //Nao pode ficar parado
		
		if(t.getPeca(id, jd) != null) return false; //Destino deve estar vazio
		
		if(diferencaI == 1) {
			if(this.getCor() == 'B' && id > iPos) return false; //Nao pode ir pra tras
			if(this.getCor() == 'P' && id < iPos) return false;
		}
		else if(diferencaI == 2) {
			Peca capturada = t.getPeca((iPos+id)/2, (jPos+jd)/2);
			if(capturada == null) return false; //Precisa ter uma peca a ser capturada
			if(capturada.getCor() == this.getCor()) return false; //Essa peca precisa ser de cor diferente
		}
		return true;
	}
	
	public void mover(int id,int jd) { //recebe a posicao de destino na matriz
		if(this.mov_valido(id, jd)) {
			int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
			int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
			if(diferencaI == 2 && diferencaJ == 2) { //caso em que ha peca no meio
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				t.setPeca((iPos+id)/2, (jPos+jd)/2, null);
				iPos = id;
				jPos = jd;
			}else if(diferencaI == 1 && diferencaJ == 1) { //caso em que nao ha peca no meio
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				iPos = id;
				jPos = jd;
			}
		}
	}
}

