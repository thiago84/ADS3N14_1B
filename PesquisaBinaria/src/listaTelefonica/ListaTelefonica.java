package listaTelefonica;

import listaTelefonica.controller.ListaController;
import listaTelefonica.view.ConsoleView;

public class ListaTelefonica {
	
	public static void main(String[] args) {
		ConsoleView view = new ConsoleView();
		ListaController controller = new ListaController(view);
		String command = "";
		
		controller.loadFile("telefones.dat");
		view.message(
				"1 - INSERIR CONTATO.\n" +
				"2 - PESQUISA BINARIA DE CONTATO.\n" +
				"3 - PROCURAR CONTATO.\n" +
				"4 - PROXIMO CONTATO.\n" +
				"5 - ANTERIOR CONTATO.\n" +
				"6 - EXCLUIR CONTATO.\n" +
				"7 - FECHAR PROGRAMA.\n");
		
		int mostraCont = 0;
		
		while (!command.equals("7")) {
			
			if (mostraCont == 1){
				controller.showContato();
				mostraCont = 0;
			}
			
			command = view.read("DIGITE A OPÇÃO DESEJADA").toLowerCase();
			if (command.equals("4")){
				controller.nextContato();
				mostraCont = 1;
			}
			else if (command.equals("5")){
				controller.previousContato();
				mostraCont = 1;
			}
			else if (command.equals("1")){
				controller.insertContato();
				mostraCont = 0;
			}
			else if (command.equals("6")){
				controller.removeContato();
				mostraCont = 0;
			}
			else if (command.equals("3")){
				controller.searchContato("",false);
				mostraCont = 1;
			}
			else if (command.equals("2")){
				controller.searchContatoBinario();
				mostraCont = 1;
			}
			
			else if (command.equals("7")){
				controller.saveFile("telefones.dat");
				System.exit(0);
			}
			else{
				view.message("COMANDO INVÁLIDO ! \npossiveis comandos: 1 - 2 - 3 - 4 - 5 - 6 - 7");
				view.message("");
				mostraCont = 0;
			}
		}
	}

}
