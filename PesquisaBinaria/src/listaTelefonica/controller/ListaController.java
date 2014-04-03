package listaTelefonica.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import listaTelefonica.model.Pessoa;
import listaTelefonica.view.ConsoleView;


import estruturas.ListaEncadeada;
import estruturas.ListaOrdenada;
import estruturas.Nodo;

public class ListaController {
	private ListaEncadeada<Pessoa> arquivo;
	private ListaOrdenada<Pessoa> contatos;
	private ConsoleView view;
	private Nodo<Pessoa> current;
	private Scanner arq;
	
	public ListaController(ConsoleView view) {
		this.view = view;
		this.contatos = new ListaOrdenada<Pessoa>();
		this.arquivo  = new ListaEncadeada<Pessoa>();
		this.current = null;
	}

	public void loadFile(String filename) {
		try {
			arq = new Scanner(new FileReader(filename));
			while(arq.hasNext()) {
				String name = arq.nextLine();
				String phone = arq.nextLine();
				Pessoa pessoa = new Pessoa(name);
				pessoa.setTelefone(phone);
				arquivo.insert(new Nodo<Pessoa>(pessoa));
				if (!name.startsWith("#"))
					contatos.insert(new Nodo<Pessoa>(pessoa));
			}
			current = contatos.getHead();
		} catch (FileNotFoundException e) {
			view.logError(e.getMessage());
		}
	}

	public void showContato() {
		if (current == null) {
			view.message("Nenhum contato existente.");
		} else {
			Pessoa contato = current.getData();
			view.printContato(contato.getNome(), contato.getTelefone());
		}
		System.out.println("");
	}

	public void nextContato() {
		if (current != null) {
			current = current.getNext();
			if (current == null)
				current = contatos.getHead();
		}
		System.out.println("");
	}

	public void previousContato() {
		if (current != null) {
			current = current.getPrevious();
			if (current == null)
				current = contatos.getTail();
		}
		System.out.println("");
	}

	public void insertContato() {
		Pessoa contato = new Pessoa();
		contato.setNome(view.read("Nome"));
		contato.setTelefone(view.read("Telefone"));
		Nodo<Pessoa> novo = new Nodo<Pessoa>(contato);
		contatos.insert(novo);
		arquivo.append(new Nodo<Pessoa>(contato));
		current = novo;
		view.message("Contato cadastrado!");
		System.out.println("");
		
	}

	public void removeContato() {
		if (current != null) {
			contatos.remove(current);
			nextContato();
			view.message("Contato removido!");
		}
		System.out.println("");
	}

	private Nodo<Pessoa> procuraContato(ListaEncadeada<Pessoa> lista, String chave, boolean res)
	{
		int vezes = 0;
		Nodo<Pessoa> iter = lista.getHead();
		while (iter != null) {
			vezes++;
			Pessoa contato = iter.getData();
			String nome = contato.getNome().toLowerCase();
			if (nome.startsWith(chave)) {
				if (res)
					view.message("A busca sequêncial executou "+ vezes +" passos");
				return iter;
			}
			iter = iter.getNext();
		}
		if (res)
			view.message("A busca sequêncial executou "+ vezes +" passos");
		return null;
	}
	
	
	private void procuraContatoBinario(ListaEncadeada<Pessoa> lista, String chave)
	{
		ArrayList<String> nome = new ArrayList<String>();
		ArrayList<String> telefone = new ArrayList<String>();
		int [] busca = new int[2];
		
	
		Nodo<Pessoa> iter = lista.getHead();
		while (iter != null) {
			Pessoa contato = iter.getData();
			nome.add(contato.getNome());
			telefone.add(contato.getTelefone());
			iter = iter.getNext();
		}
		
		busca = buscaBinaria(nome,0,nome.size() - 1,chave,0);
		
		if (busca[1] > 0){
			view.message("A busca binária executou " + busca[1] + " passos");
			view.printContato(nome.get(busca[0]), telefone.get(busca[0]));			
		}
		else{
			view.message("A busca binária não encontrou o registro.");
		}
		
	}
	
	private int[] buscaBinaria(ArrayList<String> nomes,int s, int e, String chave, int vezes){
		
		int[] ret = new int[2];
		vezes++;
		
		int half = (int) (e - s)/2 + s;
		
		if (half == s && half < e && half > 0) 
			half++;
		
		int comp = chave.compareTo(nomes.get(half)); 
		
		if (comp == 0){		
			ret[0] = half;
			ret[1] = vezes;
		}
		else if (comp < 0){ 
			if (half == e)
				return ret;
			ret = buscaBinaria(nomes,s,half,chave,vezes);
		}
		else if (comp > 0){ 
			if (half == e)
				return ret;
			ret = buscaBinaria(nomes,half,e,chave,vezes);
		}
				
		return ret;
	}
	
	public void searchContato(String chave, boolean res) {
		if (chave.isEmpty()){
			chave = view.read("Inicio do Nome").toLowerCase();
		
		}
		
		Nodo<Pessoa> contato = procuraContato(contatos, chave, res);
		if (contato != null)
			current = contato;
	}
	public void searchContatoBinario() {
		String chave = view.read("Inicio do Nome").toLowerCase();
		procuraContatoBinario(contatos, chave);
		view.message("");
		searchContato(chave,true);
		
	}

	public void saveFile(String filename) {
		FileWriter arq = null;
		try {
			arq = new FileWriter(filename,false);
			Nodo<Pessoa> iter = arquivo.getHead();
			while (iter != null) {
				Pessoa contato = iter.getData();
				if (procuraContato(contatos, contato.getNome(),false) == null)
					arq.append("#"+contato.getNome()+"\n");
				else
					arq.append(contato.getNome()+"\n");
				arq.append(contato.getTelefone()+"\n");
				iter = iter.getNext();
			}
		} catch (IOException e) {
			view.message(e.getMessage());
		} finally {
			if (arq != null)
				try {
					arq.close();
				} catch (IOException e) {
					view.message(e.getMessage());
				}
		}
	}

}
