package jogo_de_damas;

public class Main {
	private static boolean testarFimDeJogo(Tabuleiro t) {
		switch(t.getVencedor()) {
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
	
	public static void main(String[] args) {
		CSVReader csv = new CSVReader();
		csv.setDataSource("src\\jogo_completo.csv");
		String commands[] = csv.requestCommands();
		
		Tabuleiro t = new Tabuleiro();
		
		t.mostrar();
		System.out.println();
		
		Peca ultimaPeca = null;
		
		for(int i=0;i < commands.length;i++) {
			//Leitura do comando:
			System.out.println("Source: "+commands[i].charAt(0)+commands[i].charAt(1));
			System.out.println("target: "+commands[i].charAt(3)+commands[i].charAt(4));
			
			int ii = '8' - commands[i].charAt(1);    //i inicial
			int ji =  commands[i].charAt(0) - 'a';   //j inicial
			int id = '8' - commands[i].charAt(4);    //i destino
			int jd = commands[i].charAt(3) - 'a';    //j destino
			
			
			//Interpretacao do comando:
			Peca pecaAtual = t.getPeca(ii, ji);
			
			if(pecaAtual != null) {
				if(pecaAtual.getCor() != t.getlance()) { //Peca esta sendo jogada "no turno errado"
					
					//Para ser um lance longo:
					//- Peca atual eh a ultima peca jogada
					//- A ultima peca jogada fez uma captura
					//- O movimento eh valido
					//- O movimento faz uma captura
					
					if(pecaAtual == ultimaPeca && ultimaPeca.getCapturou_no_movimento() && pecaAtual.mov_valido(id, jd) && pecaAtual.getCapturou_no_movimento()) { //Lance longo
						pecaAtual.mover(id,jd);
						t.mostrar();
						if(testarFimDeJogo(t)) break;
						System.out.println();
						continue;
					}else { //Turno errado
						System.out.println("Nao eh sua vez ainda\n");
						continue;
					}
				}
				else if(pecaAtual.mover(id, jd)) t.mudaJogador(); //Peca esta sendo jogada no turno certo
			}
			else System.out.println("posicao vazia, nao ha peca nessa posicao");
			
			ultimaPeca = pecaAtual;
			t.mostrar();
			if(testarFimDeJogo(t)) break;
			System.out.println();
		}
	}
}
