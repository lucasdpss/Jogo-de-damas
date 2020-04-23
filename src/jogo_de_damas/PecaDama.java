package jogo_de_damas;

public class PecaDama extends Peca { 
	private int iCap;        //i capturado
	private int jCap;        //j capturado
	
	public PecaDama(char cor, int i, int j, Tabuleiro t){
		super(cor, i, j, t);
		this.setCaractere(cor);
	}
	
	public boolean mov_valido(int id, int jd) { //recebe a posicao de destino na matriz
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		capturou_no_movimento = false;
		
		if(diferencaI != diferencaJ) return false; //Precisa ser sempre diagonal
		if(diferencaI == 0) return false; //Nao pode ficar parado
		if(id >= 8 || jd >= 8 || id < 0 || jd < 0) return false; //Destino deve estar dentro do tabuleiro
		if(t.getPeca(id, jd) != null) return false; //Destino deve estar vazio
		
		//Duas possibilidades agora: 1)ela anda na diagonal (pelo menos uma casa),sem ter peca no meio
		// ou 2)ela anda na diagonal capturando uma unica peca 
		
		
		for(int v=1;v < diferencaI;v++) { //ja foi verificado que o destino esta vazio
			if(t.getPeca(iPos + ((id-iPos)>0?v:-v), jPos + ((jd-jPos)>0?v:-v)) != null) { //se encontrar peca no caminho
				if(capturou_no_movimento == true) {
					return false; //nao pode ter mais que uma peca no caminho
				}else {
					iCap = iPos + ((id-iPos)>0?v:-v);   //i do Capturado
					jCap = jPos + ((jd-jPos)>0?v:-v);   //j do Capturado
					capturou_no_movimento = true;
				}
			}
				
		}
		
		return true;
	}
	
	
	public boolean mover(int id,int jd) { //recebe a posicao de destino na matriz
		if(this.mov_valido(id, jd)) {
			if(capturou_no_movimento) {
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				t.registrarCaptura(t.getPeca(iCap, jCap));
				t.setPeca(iCap, jCap, null);  //tira do tabuleiro a capturada
				iPos = id;
				jPos = jd;
			}else {
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				iPos = id;
				jPos = jd;
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
		for(int id = this.iPos - 7,jd = this.jPos - 7;id <= this.iPos+7 && jd <= this.jPos+7 ;id++,jd++) {
			if(id == this.iPos && jd == this.jPos) continue;
			if(this.mov_valido(id, jd)) {
				tem_movimento_valido = true;
				if(this.capturou_no_movimento) captura_obrigatoria = true;
			}
		}
		for(int id = this.iPos - 7,jd = this.jPos + 7;id <= this.iPos+7 && jd >= this.jPos-7 ;id++,jd--) {
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
