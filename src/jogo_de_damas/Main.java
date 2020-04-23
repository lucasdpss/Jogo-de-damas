package jogo_de_damas;

public class Main {

	public static void main(String[] args) {
		CSVReader csv = new CSVReader();
		csv.setDataSource("src\\jogo.csv");
		String commands[] = csv.requestCommands();

		Tabuleiro t = new Tabuleiro();

		t.mostrar();
		System.out.println();

		Peca ultimaPeca = null;

		for(int i=0;i < commands.length;i++) {
			//Leitura do comando:
			System.out.println("Source: "+commands[i].charAt(0)+commands[i].charAt(1));
			System.out.println("target: "+commands[i].charAt(3)+commands[i].charAt(4));

			if(commands[i].length() != 5) {
				System.out.println("Comando invalido\n");
				continue;
			}

			int ii = '8' - commands[i].charAt(1);    //i inicial
			int ji =  commands[i].charAt(0) - 'a';   //j inicial
			int id = '8' - commands[i].charAt(4);    //i destino
			int jd = commands[i].charAt(3) - 'a';    //j destino

			boolean tem_captura_brancas = true;
			boolean tem_movimento_valido_brancas = true;
			boolean tem_captura_pretas  = true;
			boolean tem_movimento_valido_pretas  = true;

			switch(t.procuraMovimento('B')) {
			case 2:
				tem_captura_brancas = true;
				tem_movimento_valido_brancas = true;
				break;
			case 1:
				tem_captura_brancas = false;
				tem_movimento_valido_brancas = true;
				break;
			case 0:
				tem_captura_brancas = false;
				tem_movimento_valido_brancas = false;
				break;
			}

			switch(t.procuraMovimento('P')) {
			case 2:
				tem_captura_pretas = true;
				tem_movimento_valido_pretas = true;			
				break;
			case 1:
				tem_movimento_valido_pretas = true;
				tem_captura_pretas = false;
				break;
			case 0:
				tem_captura_pretas = false;
				tem_movimento_valido_pretas = false;
				break;
			}
			
			System.out.println(t.procuraMovimento('P'));
			System.out.println(t.procuraMovimento('B'));
			
			if(!tem_movimento_valido_brancas && !tem_movimento_valido_pretas) {
				System.out.println("Empate");
				break;
			}

			//Interpretacao do comando:
			Peca pecaAtual = t.getPeca(ii, ji);

			if(pecaAtual != null) {

				boolean tem_movimento_valido = (pecaAtual.getCor() == 'B')? tem_movimento_valido_brancas : tem_movimento_valido_pretas;
				boolean tem_captura_obrigatoria = (pecaAtual.getCor() == 'B')? tem_captura_brancas : tem_captura_pretas;

				if(!tem_movimento_valido) {
					System.out.println("Nao tem movimento valido para essa cor");
					t.mostrar();
					System.out.println();
					continue; //passa para o proximo comando
				}

				if(pecaAtual.getCor() != t.getlance()) { //Peca esta sendo jogada "no turno errado"

					//Para ser um lance longo:
					//- Peca atual eh a ultima peca jogada
					//- A ultima peca jogada fez uma captura
					//- O movimento eh valido
					//- O movimento faz uma captura

					if(pecaAtual == ultimaPeca && t.getUltimaPecaCapturouNoMovimento() && pecaAtual.mov_valido(id, jd) && pecaAtual.getCapturou_no_movimento()) { //Lance longo
						pecaAtual.mover(id,jd);
						t.setUltimaPecaCapturouNoMovimento(pecaAtual.getCapturou_no_movimento());
						t.mostrar();
						if(t.testarFimDeJogo()) break;
						System.out.println();
						continue;
					}else { //Turno errado
						System.out.println("Nao eh sua vez ainda\n");
						continue;
					}
				}else { //Peca esta sendo jogada no turno certo
					if(pecaAtual.mov_valido(id, jd)) {
						if(tem_captura_obrigatoria) {
							if(pecaAtual.getCapturou_no_movimento() == false) { //se nao captura no movimento atual
								System.out.println("Captura obrigatoria em andamento, movimento atual invalido");
								//nao muda o turno agora
							}else { //se captura no movimento atual
								pecaAtual.mover(id,jd);
								t.setUltimaPecaCapturouNoMovimento(pecaAtual.getCapturou_no_movimento());
								t.mudaJogador(); //muda o turno
							}
						}else {
							pecaAtual.mover(id,jd);
							t.setUltimaPecaCapturouNoMovimento(pecaAtual.getCapturou_no_movimento());
							t.mudaJogador(); //muda o turno
						}
					}else { //se mov_valido retorna false
						System.out.println("Movimento invalido");
					}
				}

			}
			else System.out.println("posicao vazia, nao ha peca nessa posicao");

			if(pecaAtual != null) ultimaPeca = pecaAtual;
			t.mostrar();
			if(t.testarFimDeJogo()) break;
			System.out.println();
		}
	}



}
