import java.util.ArrayList;

public class Arvore {
	public No raiz;
	public int comparacoes;
	public int numNos;

	public Arvore() { // construtor
		raiz = null;
		comparacoes = 1;
		numNos = 0;
	}
	
	/*
	 * O método pesquisa busaca na arvore um nó com base na chave que lhe é
	 * passado pelo parâmetro chave
	 */
	public No pesquisa(String chave) {
		comparacoes = 1;
		No atual = raiz;
		while (!atual.codigo.toLowerCase().trim().equals(chave.toLowerCase().trim())) {
			comparacoes++;
			if (chave.toLowerCase().compareTo(atual.codigo.toLowerCase()) < 0)
				atual = atual.filhoEsquerda;
			else
				atual = atual.filhoDireita;
			if (atual == null) {
				System.out.println("Comparações: " + comparacoes);
				return null;
			}

		}
		System.out.println("Comparações: " + comparacoes);
		return atual;
	} // fim do método pesquisa

	/*
	 * O método insere insere um nó na arvore, recebendo como parâmetro os dados
	 * do nó
	 */
	public void insere(No novo, boolean contar) {
		if (contar) numNos++;
		No novoNo = novo;
		
		if (raiz == null)
			raiz = novoNo;
		else // raiz ocupada
		{
			No atual = raiz;
			No parente;
			while (true) {
				parente = atual;
				if (novo.codigo.compareTo(atual.codigo) < 0) {
					atual = atual.filhoEsquerda;
					if (atual == null) { // insere a esquerda
						parente.filhoEsquerda = novoNo;
						novoNo.pai = parente;
						return;
					}
				} else // ou vai para direita?
				{
					atual = atual.filhoDireita;
					if (atual == null) {
						parente.filhoDireita = novoNo;
						novoNo.pai = parente;
						return;
					}
				} // fim do else ir para direita
			} // fim do while
		} // fim do else não raiz
	} // fim do método insere

	/*
	 * O método delete apaga um nó da árvore passado como parâmetro pela
	 * variável chave
	 */
	public boolean delete(String chave) {
		numNos--;
		No atual = raiz;
		No parente = raiz;
		boolean eFilhoEsquerda = true;

		while (!atual.codigo.toLowerCase().trim().equals(chave.toLowerCase().trim())) // busca nó
		{
			parente = atual;
			if (chave.toLowerCase().compareTo(atual.codigo.toLowerCase()) < 0) {
				eFilhoEsquerda = true;
				atual = atual.filhoEsquerda;
			} else {
				eFilhoEsquerda = false;
				atual = atual.filhoDireita;
			}
			if (atual == null)
				return false; // não o encontrou
		} // fim do while

		// se não há filho, simplesmente elimina-o
		if (atual.filhoEsquerda == null && atual.filhoDireita == null) {
			if (atual == raiz) // se raiz,
				raiz = null;
			else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = null;
			else
				atual.pai.filhoDireita = null;
		}
		// se não há filho à direita, substitui por subárvore à esquerda
		else if (atual.filhoDireita == null){
			if (atual == raiz)
				raiz = atual.filhoEsquerda;
			else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = atual.filhoEsquerda;
			else
				atual.pai.filhoDireita = atual.filhoEsquerda;
		}

		// se não há filho à esquerda, substitui por subárvore à direita
		else if (atual.filhoEsquerda == null){
			if (atual == raiz)
				raiz = atual.filhoDireita;
			else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = atual.filhoDireita;
			else
				atual.pai.filhoDireita = atual.filhoDireita;
		}

		else // dois filhos, assim substitua-o com o sucessor inorder
		{
			// connecta parente do atual ao successor
			if (atual == raiz)
			{
				raiz = null;
			}
			else if (eFilhoEsquerda)
				atual.pai.filhoEsquerda = null;
			else
				atual.pai.filhoDireita = null;
			
			insere(atual.filhoDireita, false);
			insere(atual.filhoEsquerda, false);
		} // fim do else dois filhos
		return true;
	} // fim do método delete()

	/*
	 * O método travesseia apaga um nó da árvore passado como parâmetro pela
	 * variável chave
	 */
	public void travessia(int tipoTravessia) {
		switch (tipoTravessia) {
		case 1:
			System.out.println("\nTravessia usando Preorder: ");
			preOrder(raiz);
			break;
		case 2:
			System.out.println("\nTravessia usando Inorder:  ");
			inOrder(raiz);
			break;
		case 3:
			System.out.println("\nTravessia usando Postorder: ");
			posOrder(raiz);
			break;
		case 4:
			System.out.println("\nTravessia usando Busca por Profundidade: ");
			profundidade();
			break;
		case 5:
			System.out.println("\nTravessia usando Busca por Largura: ");
			largura();
			break;
		}
		System.out.println();
	}

	/*
	 * O método preOrder
	 */
	private void preOrder(No localraiz) {
		if (localraiz != null) {
			localraiz.mostraNo();
			preOrder(localraiz.filhoEsquerda);
			preOrder(localraiz.filhoDireita);
		}
	}

	/*
	 * O método inOrder
	 */
	private void inOrder(No localraiz) {
		if (localraiz != null) {
			inOrder(localraiz.filhoEsquerda);
			localraiz.mostraNo();
			inOrder(localraiz.filhoDireita);
		}
	}

	/*
	 * O método posOrder
	 */
	private void posOrder(No localraiz) {
		if (localraiz != null) {
			posOrder(localraiz.filhoEsquerda);
			posOrder(localraiz.filhoDireita);
			localraiz.mostraNo();
		}
	}

	private void profundidade() {
		ArrayList<No> pilha = new ArrayList<No>();

		pilha.add(raiz);

		while (!pilha.isEmpty()) {
			No no = pilha.get(pilha.size() - 1);
			no.mostraNo();
			pilha.remove(no);

			if (no.filhoDireita != null) {
				pilha.add(no.filhoDireita);
			}

			if (no.filhoEsquerda != null) {
				pilha.add(no.filhoEsquerda);
			}
		}
	}

	private void largura() {
		ArrayList<No> fila = new ArrayList<No>();

		fila.add(raiz);

		while (!fila.isEmpty()) {
			No no = fila.get(0);
			no.mostraNo();
			fila.remove(no);

			if (no.filhoEsquerda != null) {
				fila.add(no.filhoEsquerda);
			}

			if (no.filhoDireita != null) {
				fila.add(no.filhoDireita);
			}
		}
	}
}
