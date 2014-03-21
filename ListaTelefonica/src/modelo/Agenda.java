package modelo;

import estrutura.ListaOrdenada;
import estrutura.Nodo;

public class Agenda {
	private ListaOrdenada <Pessoa> lista = new ListaOrdenada<Pessoa>();
	private Nodo<Pessoa> atual = null;
	
	public Agenda(){
		
	}
	
	public void insertPessoa(Pessoa p){
		Nodo<Pessoa> nodoPessoa = new Nodo<Pessoa>(p); 
		this.lista.append(nodoPessoa);
	}
	
	public void printList(){
		this.lista.print();
	}
		
	public void listByChar(String nome){
		Nodo<Pessoa> nodoPessoa = this.lista.getHead();
		while(nodoPessoa != null){
			if(nodoPessoa.getData().getNome().toUpperCase().charAt(0) == nome.toUpperCase().charAt(0)){
				System.out.println(nodoPessoa.getData().toString());
			}
			nodoPessoa = nodoPessoa.getNext();
		}
	}
	
	public Pessoa getContatoAtual(){
		if(this.atual != null){
			return this.atual.getData();
		}
		return null;
	}
	
	public void excluir(){
		
	}
	
	public void next(){
		this.atual = this.lista.getNext(this.atual);
	}
	
	public void prev(){
		this.atual = this.lista.getPrev(this.atual);
	}
	
}
