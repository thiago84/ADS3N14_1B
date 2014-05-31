package model;

import java.util.ArrayList;

import estruturas.Arvore;

public class Contato {
	
	private String nome;
	private String telefone;
	private Arvore objArvore = new Arvore();
	private int qtdComparacoes = 0;
	
	public int getQtdComparacoes() {
		return this.qtdComparacoes;
	}

	public void setQtdComparacoes(int qtdComparacoes) {
		this.qtdComparacoes = qtdComparacoes;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Contato (){
		
	}
	
	public Contato(String nome, String telefone){
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public boolean procurar(String nome){
		
		this.qtdComparacoes = 0;
		
		String[] ret = objArvore.procurarToArray(nome);
		
		if (ret[0].isEmpty()){
			this.nome = "";
			this.telefone = "";
			this.qtdComparacoes = objArvore.getComp();
			return false;
		}
		else {
			this.nome = ret[0];
			this.telefone = ret[1];
			this.qtdComparacoes = objArvore.getComp();
			return true;
		}
	}
	
	public void cadastrar(String nome, String telefone){
		String[] contato = {"",""};
		contato[0] = nome;
		contato[1] = telefone;
		
		objArvore.inserir(contato);
	}
	
	public int excluir(String nome){
		return objArvore.deletar(nome);
	}
	
	public ArrayList<String[]> listarContatos(char tipo){
		
		ArrayList<String[]> retList = new ArrayList<>();
		
		switch (tipo){
			case 'a':
				retList = objArvore.tPrefixa(retList,objArvore.getRoot());
			break;
			
			case 'b':
				retList = objArvore.tInfixa(retList,objArvore.getRoot());
			break;
			
			case 'c':
				retList = objArvore.tPosFixa(retList,objArvore.getRoot());
			break;
			
			case 'd':
				
			break;
			
			case 'e':
				
			break;
		}
		
		return retList;
	}
}
