package controlador;

import static java.lang.System.out;
import java.util.Scanner;
import modelo.NavioColocado;

public class MatrizCenario {


	public void mostraTabela(String[][] table) {
		
		// Mostra as Letras
		out.print(" ");
		for (char i = 'A'; i <= 'J'; i++) {
			out.print(" " + i);
		}

		out.println("");

		for (int i = 0; i <= 9; i++) {
			for (int z = 0; z <= 9; z++) {
				// Mostra os numeros
				if (z == 0) {
					out.print(i + " ");
				}

				// verifica o caractere para mostrar
				if (table[i][z] == "0" || table[i][z] == "1") {
					out.print(". ");
				} else if (table[i][z] == "2") {
					out.print("- ");
				} else if (table[i][z] == "3") {
					out.print("O ");
				}

			}
			out.println("");
		}
	}

	public String msgOpcoes() {
		out.println("Informe a Op��o: ");
		return new Scanner(System.in).next();
	}

	// Msg Ganhou
	public void msgGanhou() {
		out.println("Voc� Ganhou!!!");
	}
	
	// Msg Perdeu
	public void msgPerdeu() {
		out.println("Voc� Perdeu!!!");
	}
	
	// Msg Acertou Agua
	public void msgAcertouAgua() {
		out.println("Voc� acertou na �gua!");
	}

 
	// Mostra Pontua��o
	public void msgPontos(int count)
	{
		out.println("Restam " + count + " Pontos.");
	}

	// Msg Acerta
	public void msgAcertou(NavioColocado ship) {
		out.println("Voc� acertou um " + ship.getNome());
	}
	
	// Msg Destroi
	public void msgDestruiu(NavioColocado ship)
	{
		out.println("Voc� destruiu um " + ship.getNome());
	}
	
	   // Msg Erro
		public void msgErro(String mensagem) {
			
			out.println("--------------------");
			out.println(mensagem);
			out.println("--------------------");
		}

}
