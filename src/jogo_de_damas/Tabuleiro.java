package jogo_de_damas;

public class Tabuleiro {
	private Peca matriz[][];
	private char lance;
	
	private int pecasBrancas;
	private int pecasPretas;
	private boolean brancasVenceram;
	private boolean pretasVenceram;
	
	private boolean ultimaPecaCapturouNoMovimento;
	
	public Tabuleiro(){
		pecasBrancas = 12;
		pecasPretas = 12;
		brancasVenceram = false;
		pretasVenceram = false;
		matriz = new Peca[8][8];
		lance = 'B';   //o jogo deve comecar pelas brancas
		
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
					System.out.print(" " + matriz[i][j].getCaractere());
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
	
	public void registrarCaptura(Peca capturada) {
		if(capturada.getCor() == 'B') {
			pecasBrancas--;
			if(pecasBrancas <= 0) pretasVenceram = true;
		}
		else {
			pecasPretas--;
			if(pecasPretas <= 0) brancasVenceram = true;
		}
	}
	
	public char getVencedor() {
		if(brancasVenceram) return 'B';
		else if(pretasVenceram) return 'P';
		else return 'N';
	}
	
	public boolean testarFimDeJogo() {
		switch(this.getVencedor()) {
		case 'B':
			System.out.println("Brancas venceram!");
			return true;
		case 'P':
			System.out.println("Pretas venceram!");
			return true;
		default:
			return false;
		}
	}
	
	//funcoes para controlar o lance:
	public char getlance() {
		return lance;
	}
	
	public void setLance(char lance) {
		this.lance = lance;
	}
	
	public void mudaJogador() {
		if(lance == 'P') setLance('B');
		else setLance('P');
	}
	//fim das funcoes para controlar o lance
	
	public void setUltimaPecaCapturouNoMovimento(boolean capturou) {
		ultimaPecaCapturouNoMovimento = capturou;
	}
	
	public boolean getUltimaPecaCapturouNoMovimento() {
		return ultimaPecaCapturouNoMovimento;
	}
}
