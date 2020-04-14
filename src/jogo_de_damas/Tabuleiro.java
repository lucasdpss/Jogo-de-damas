package jogo_de_damas;

public class Tabuleiro {
	public Peca matriz[][];
	
	Tabuleiro(){
		matriz = new Peca[8][8];
		
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				if(i == 0 || i == 1 || i == 2) {
					if((i + j) % 2 == 1) {
						PecaComum tmp = new PecaComum();
						tmp.cor = 'P';
						matriz[i][j] = tmp;
					}
				}else if(i == 5 || i == 6 || i == 7) {
					if((i + j) % 2 == 1) {
						PecaComum tmp = new PecaComum();
						tmp.cor = 'B';
						matriz[i][j] = tmp;
					}
				}
			}
		}
	}
	
	
	public void mostrar() {
		for(int i=0;i < 8;i++) {
			System.out.print(8 - i);
			for(int j=0;j < 8;j++) {
				if(matriz[i][j] == null) {
					System.out.print(" -");
				}else {
					System.out.print(" " + matriz[i][j].cor);
				}
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	public void mover_peca(String comando) { //recebe uma string "f4:d4"
		int ii = '8' - comando.charAt(1);    //i inicial
		int ji =  comando.charAt(0) - 'a';   //j inicial
		int id = '8' - comando.charAt(4);    //i destino
		int jd = comando.charAt(3) - 'a';    //j destino
		
		if(matriz[ii][ji] == null) {
			System.out.println("Nenhuma peca nessa posicao de inicio");
			return;
		}
		
		if(matriz[ii][ji].mov_valido(id, jd)) {    //pergunta para a peca se eh um mov valido ate o destino
			//Valores absolutos das diferencas das posicoes em X e em Y:
			int diferencaX = (id-ii>=0)?(id-ii):(ii-id);
			int diferencaY = (jd-ji>=0)?(jd-ji):(ji-jd);
			if(diferencaX == 2 && diferencaY == 2) {     //caso em que ha peca no meio
				matriz[id][jd] = matriz[ii][ji];
				matriz[ii][ji] = null;
				matriz[(ii+id)/2][(ji+jd)/2] = null;
			}else if(diferencaX == 1 && diferencaY == 1) { //caso em que nao ha peca no meio
				matriz[id][jd] = matriz[ii][ji];
				matriz[ii][ji] = null;
			}
			  //possibilidade de colocar outros else if's para movimentos "especiais" caso precise
		}

	}
	
}
