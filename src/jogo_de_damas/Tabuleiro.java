package jogo_de_damas;

public class Tabuleiro {
	private Peca matriz[][];
	
	public Tabuleiro(){
		matriz = new Peca[8][8];
		
		for(int i=0;i < 8;i++) {
			for(int j=0;j < 8;j++) {
				if(i == 0 || i == 1 || i == 2) {
					if((i + j) % 2 == 1) {
						matriz[i][j] = new PecaComum('P', i, j, this);
					}
				}else if(i == 5 || i == 6 || i == 7) {
					if((i + j) % 2 == 1) {
						matriz[i][j] = new PecaComum('B', i, j, this);
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
					System.out.print(" " + matriz[i][j].getCor());
				}
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public Peca getPeca(int i, int j) {
		return matriz[i][j];
	}
	
	public void setPeca(int i, int j, Peca p) {
		matriz[i][j] = p;
	}
}
