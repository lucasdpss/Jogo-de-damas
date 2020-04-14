package jogo_de_damas;

public class Main {

	public static void main(String[] args) {
		//CSVReader csv = new CSVReader();
		//csv.setDataSource("src\\solution.csv");
		//String commands[] = csv.requestCommands();
		
		Peca matriz[][] = new Peca[3][3];
		
		PecaDama dama1 = new PecaDama();
		dama1.cor = 'P';
		
		PecaComum comum1 = new PecaComum();
		comum1.cor = 'B';
		
		matriz[0][0] = dama1;
		matriz[0][1] = comum1;
		
		matriz[0][0].printa();
		matriz[0][1].printa();

	}

}
