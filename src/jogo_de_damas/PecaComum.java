package jogo_de_damas;

public class PecaComum extends Peca {
	
	public PecaComum(char cor, int i, int j, Tabuleiro t){
		super(cor, i, j, t);
	}
	
	public boolean mov_valido(int id,int jd) {
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		capturou_no_movimento = false;
		
		if(diferencaI != diferencaJ) return false; //Precisa ser sempre diagonal
		if(diferencaI > 2) return false;  //Move no maximo 2 posicoes em cada direcao
		if(diferencaI == 0) return false; //Nao pode ficar parado
		if(id >= 8 || jd >= 8 || id < 0 || jd < 0) return false; //Destino deve estar dentro do tabuleiro
		if(t.getPeca(id, jd) != null) return false; //Destino deve estar vazio
		
		if(diferencaI == 1) {
			if(this.getCor() == 'B' && id > iPos) return false; //Nao pode ir pra tras
			if(this.getCor() == 'P' && id < iPos) return false;
		}
		else if(diferencaI == 2) {
			Peca capturada = t.getPeca((iPos+id)/2, (jPos+jd)/2); 
			if(capturada == null) return false; //Precisa ter uma peca a ser capturada
			if(capturada.getCor() == this.getCor()) return false; //Essa peca precisa ser de cor diferente
			capturou_no_movimento = true;
		}
		return true;
	}
	
	public boolean mover(int id,int jd) { //recebe a posicao de destino na matriz
		if(this.mov_valido(id, jd)) {
			int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
			int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
			if(diferencaI == 2 && diferencaJ == 2) { //caso em que ha peca no meio
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				t.registrarCaptura(t.getPeca((iPos+id)/2, (jPos+jd)/2));
				t.setPeca((iPos+id)/2, (jPos+jd)/2, null);
				iPos = id;
				jPos = jd;
			}else if(diferencaI == 1 && diferencaJ == 1) { //caso em que nao ha peca no meio
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				iPos = id;
				jPos = jd;
			}
			if(this.getCor() == 'P' && id == 7) {          //Coroacao das pretas
				PecaDama promovida = new PecaDama('P',id,jd,t);
				t.setPeca(id, jd, promovida);
			}
			if(this.getCor() == 'B' && id == 0) {           //Coroacao das brancas
				PecaDama promovida = new PecaDama('B',id,jd,t);
				t.setPeca(id, jd, promovida);
			}
			return true;
		}
		else return false;
	
	}
	
	//retorna 1 se tem algum mov.valido sem captura obrigatoria e 2 se tem algum movimento valido e tem captura obrigatoria
	public int algumMovimentoValido() {   //retorna 0 se nao tem movimento nenhum valido
		boolean captura_obrigatoria = false;
		boolean tem_movimento_valido = false;
		
		//testar todos os possiveis movimentos nas duas diagonais da peca
		for(int id = this.iPos - 2,jd = this.jPos - 2;id <= this.iPos+2 && jd <= this.jPos+2 ;id++,jd++) {
			if(id == this.iPos && jd == this.jPos) continue;
			if(this.mov_valido(id, jd)) {
				tem_movimento_valido = true;
				if(this.capturou_no_movimento) captura_obrigatoria = true;
			}
		}
		if(tem_movimento_valido) 
			return (captura_obrigatoria)? 2 : 1;
		else
			return 0;
	}
	
}

