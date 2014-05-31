package estruturas;

import java.util.ArrayList;

public class Arvore {
	
	private Node root;
	private int comp = 0;
	int res;
	String tmp;
	
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Arvore(){
		this.root = null;
	}
	
	public int getComp() {
		return comp;
	}

	public void setComp(int comp) {
		this.comp = comp;
	}
	
	public void inserir(String[] key){
		if (this.root == null){
			this.root = new Node(key);
			this.root.parent = null;
		}
		else{
		  buscaPosicao(this.root,key);
		}
	}
	
	
	
	private void buscaPosicao(Node node, String[] key){
		
		int res;
		
		res = key[0].compareToIgnoreCase(node.key[0]);
		
		if(res < 0){ // insere a esquerda
			
			if(node.left == null){
				node.left = new Node(key);
				node.left.parent = node;
			}
			else
				buscaPosicao(node.left,key);
		  	}
		
		else if(res > 0){ // insere a direita
			
		   if(node.right == null){
			   node.right = new Node(key);
			   node.right.parent = node;
		   }
		   else{
			   buscaPosicao(node.right, key);
		   }
		}
		else { // valor já existente
			return;
		}
	}
	
	

	public Node procurar(String key){
		
		Node currentNode = this.root;
		this.comp = 0;
	   
		while(currentNode != null){
			
			res = key.compareToIgnoreCase(currentNode.key[0]);
			this.setComp(this.getComp() + 1);
			
			if(res == 0){
				return currentNode;
			}
			else if(res < 0){
				currentNode = currentNode.left;
			}
			else{
				currentNode = currentNode.right;
			}
		}
		
		return currentNode;
	}
	
	public String[] procurarToArray(String key){
		
		Node resp = null;
		String[] ret = {"",""};
		
		resp = this.procurar(key);
		
		if (resp == null){
			return ret;
		}
		
		ret[0] = resp.key[0];
		ret[1] = resp.key[1];
		return ret;
	}
	
	public int deletar(String key){
		
		Node resp = procurar(key);
		
		if (resp != null){
			
			if (resp.left == null && resp.right == null){ // o nodo é uma folha
				
				if (resp.key[0].equals(resp.parent.left.key[0])){ // o nodo é folha da esquerda do pai 
					resp.parent.left = null;
					return 1;
				}
				else { // o nodo é folha da direita do pai
					resp.parent.right = null;
					return 1;
				}
			}
			else if (resp.left != null && resp.right == null){ // o nodo tem um filho a esquerda
				
				if (resp.key[0].equals(resp.parent.left.key[0])){ // o nodo é folha a esquerda do pai 
					resp.parent.left = resp.left;
					return 1;
				}
				else { // o nodo é folha a direita do pai
					resp.parent.right = resp.left;
					return 1;
				}
				
			}
			else if (resp.left == null && resp.right != null){ // o nodo tem um filho a direita
				
				if (resp.key[0].equals(resp.parent.left.key[0])){ // o nodo é folha a esquerda do pai 
					resp.parent.left = resp.right;
					return 1;
				}
				else { // o nodo é folha a direita do pai
					resp.parent.right = resp.right;
					return 1;
				}
				
			}
			else { // o nodo tem dois filhos
				Node sucessor = null;
				sucessor = buscaNodoEsquerdo(resp.right);
				
				if (sucessor.right != null){ // se o sucessor tem um filho a direita, apontar a raiz do sucessor para esse filho
					sucessor.parent.left = sucessor.right;
				}
				
				if (resp.key[0].equals(resp.parent.left.key[0])){ // se nodo a ser removido é nodo filho a esquerda, atualiza link na raiz para o sucesso
					resp.parent.left = sucessor;
				}
				else { // senão nodo removido é filho a direita
					resp.parent.right = sucessor;
				}
			}
			
			
			
		}
		
		return 0;
		
	}
	
	private Node buscaNodoEsquerdo(Node nodo){
		
		Node nodoFinal;
				
		if (nodo.left != null){
			nodoFinal = buscaNodoEsquerdo(nodo.left);
		}
		else {
			nodoFinal = nodo;
		}
		
		return nodoFinal; 
	}
	
	public ArrayList<String[]> tPrefixa(ArrayList<String[]> param, Node raiz){
		
		
		if (raiz != null ){
			param.add(raiz.key);
			tPrefixa(param,raiz.left);
			tPrefixa(param,raiz.right);
		}
		
		return param;
		
	}
	
	 public ArrayList<String[]> tInfixa (ArrayList<String[]> param, Node raiz){
		 
		 if (raiz != null){
			 tInfixa(param,raiz.left);
			 param.add(raiz.key);
			 tInfixa(param,raiz.right);
		 }
		 
		 return param;
	 }

	 public ArrayList<String[]> tPosFixa(ArrayList<String[]> param, Node raiz){
		 
		 if (raiz != null){
			 tPosFixa(param,raiz.left);
			 tPosFixa(param,raiz.right);
			 param.add(raiz.key);
		 }
		 
		 return param;
	 }
}
