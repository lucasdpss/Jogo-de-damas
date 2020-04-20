package jogo_de_damas;

public class Main {

	public static void main(String[] args) {
		CSVReader csv = new CSVReader();
		csv.setDataSource("src\\jogo_completo.csv");
		String commands[] = csv.requestCommands();
		
		Tabuleiro t = new Tabuleiro();
		
		t.mostrar();
		System.out.println();
		
		for(int i=0;i < commands.length;i++) {
			System.out.println("Source: "+commands[i].charAt(0)+commands[i].charAt(1));
			System.out.println("target: "+commands[i].charAt(3)+commands[i].charAt(4));
			
			int ii = '8' - commands[i].charAt(1);    //i inicial
			int ji =  commands[i].charAt(0) - 'a';   //j inicial
			int id = '8' - commands[i].charAt(4);    //i destino
			int jd = commands[i].charAt(3) - 'a';    //j destino
			
			Peca pecaAtual = t.getPeca(ii, ji);
			if(pecaAtual != null) {
				if(pecaAtual.getCor() != t.getlance()) {
					if(pecaAtual.mov_valido(id, jd) && pecaAtual.getCapturou_no_movimento()) {
						//System.out.println("flag de um lance longo");
						pecaAtual.mover(id,jd);
						t.mostrar();
						System.out.println();
						continue;
					}else {
						System.out.println("Nao eh sua vez ainda\n");
						continue;
					}
				}
				if(pecaAtual.mov_valido(id, jd)) {
					pecaAtual.mover(id,jd);
					t.mudaJogador();
				}else {
					System.out.println("Movimento invalido");
				}
				
			}else {
				System.out.println("posicao vazia, nao ha peca nessa posicao");
			}
			
			t.mostrar();
			System.out.println();
		}
	}

}
