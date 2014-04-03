package apps;

import apps.views.ConsoleView;

import estruturas.ListaOrdenada;
import estruturas.Nodo;

public class Controller {
	
	private ListaOrdenada<Integer>
			lista = new ListaOrdenada<Integer>();
	private ConsoleView view = new ConsoleView();
	
	public void iniciaLista() {
		for (int i = 0; i < 50; ++i) {
			Nodo<Integer> novo = new Nodo<Integer>();
			novo.setData((int)(Math.random() * 10000));
			lista.insert(novo);
		}
	}
	
	public void imprimeLista() {
		Nodo<Integer> nodo = lista.getHead();
		while (nodo != null) {
			view.imprimeInteiro(nodo.getData());
			nodo = nodo.getNext();
		}
	}
	
}









