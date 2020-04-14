package jogo_de_damas;

public class Main {

	public static void main(String[] args) {
		CSVReader csv = new CSVReader();
		csv.setDataSource("src\\movimentos.csv");
		String commands[] = csv.requestCommands();
		
		Tabuleiro t = new Tabuleiro();
		
		t.mostrar();
		System.out.println();
		
		for(int i=0;i < commands.length;i++) {
			System.out.println("Source: "+commands[i].charAt(0)+commands[i].charAt(1));
			System.out.println("target: "+commands[i].charAt(3)+commands[i].charAt(4));
			t.mover_peca(commands[i]);
			t.mostrar();
			System.out.println();
		}
	}

}
