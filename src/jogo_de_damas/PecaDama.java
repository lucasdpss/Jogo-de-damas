package jogo_de_damas;

public class PecaDama extends Peca { 
	
	private boolean capturou_no_movimento;    //perguntar se esse nome de variavel segue as regras de boas praticas
	private int iCap;        //i capturado
	private int jCap;        //j capturado
	
	public PecaDama(char cor, int i, int j, Tabuleiro t){
		super(cor, i, j, t);
	}
	
	private boolean mov_valido(int id, int jd) { //recebe a posicao de destino na matriz
		int diferencaI = (id-iPos>=0)?(id-iPos):(iPos-id);
		int diferencaJ = (jd-jPos>=0)?(jd-jPos):(jPos-jd);
		capturou_no_movimento = false;
		
		if(diferencaI != diferencaJ) return false; //Precisa ser sempre diagonal
		if(diferencaI == 0) return false; //Nao pode ficar parado
		if(id >= 8 || jd >= 8) return false; //Destino deve estar dentro do tabuleiro
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
		
		if(capturou_no_movimento) {  //verificar se eh somente uma posicao depois de ter capturado
			int diferencaI_cap = (id-iCap>=0)?(id - iCap):(iCap - id);               
			int diferencaJ_cap = (jd-jCap>=0)?(jd - jCap):(jCap - jd);
			if(diferencaI_cap > 1 || diferencaJ_cap > 1) return false; //destino so pode ser uma casa depois de ter capturado
		}
		
		return true;
	}
	
	
	public void mover(int id,int jd) { //recebe a posicao de destino na matriz
		if(this.mov_valido(id, jd)) {
			if(capturou_no_movimento) {
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				t.setPeca(iCap, jCap, null);  //tira do tabuleiro a capturada
				iPos = id;
				jPos = jd;
			}else {
				t.setPeca(id, jd, this);
				t.setPeca(iPos, jPos, null);
				iPos = id;
				jPos = jd;
			}
		}
	}
	
}
