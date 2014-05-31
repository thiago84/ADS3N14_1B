

public class AVL extends Arvore {
	public int comparacoes;
	public int rotacoes;
	public boolean removido;
	
	private void resetaAtributos()
	{
		comparacoes = 0;
		rotacoes = 0;
	}

	public void insereAVL(No no) {
		resetaAtributos();
		insereAVL(raiz, no);
	}

	public void insereAVL(No p, No q) {
		if (p == null) {
			raiz = q;
		} else {

			if (q.codigo.toLowerCase().compareTo(p.codigo.toLowerCase()) < 0) {
				comparacoes++;
				if (p.filhoEsquerda == null) {
					p.filhoEsquerda = q;
					q.pai = p;

					balancoRec(p);
				} else {
					insereAVL(p.filhoEsquerda, q);
				}

			} else if (q.codigo.toLowerCase().compareTo(p.codigo.toLowerCase()) >= 0) {
				comparacoes++;
				if (p.filhoDireita == null) {
					p.filhoDireita = q;
					q.pai = p;

					balancoRec(p);
				} else {
					insereAVL(p.filhoDireita, q);
				}
			}
		}
	}

	public void balancoRec(No atual) {

		setBalanco(atual);
		int balanco = atual.balanco;

		if (balanco == -2) {

			comparacoes++;
			if (altura(atual.filhoEsquerda.filhoEsquerda) >= altura(atual.filhoEsquerda.filhoDireita)) {
				atual = rotaDir(atual);
			} else {
				atual = rotaEsqDir(atual);
			}
		} else if (balanco == 2) {
			comparacoes++;
			if (altura(atual.filhoDireita.filhoDireita) >= altura(atual.filhoDireita.filhoEsquerda)) {
				atual = rotaEsq(atual);
			} else {
				atual = rotaDirEsq(atual);
			}
		}

		if (atual.pai != null) {
			balancoRec(atual.pai);
		} else {
			raiz = atual;
			System.out
					.println("Balanceado");
		}
	}

	public void removeAVL(String k) {
		removido = false;
		resetaAtributos();
		removeAVL(raiz, k);
	}
	
	public void removeAVL(No p, String q) {
		if (p == null) {
			return;
		} else {
			comparacoes++;
			if (p.codigo.toLowerCase().compareTo(q.toLowerCase()) > 0) {
				removeAVL(p.filhoEsquerda, q);
			} else if (p.codigo.toLowerCase().compareTo(q.toLowerCase()) < 0) {
				removeAVL(p.filhoDireita, q);
			} else if (p.codigo.toLowerCase().trim().equals(q.toLowerCase().trim())) {
				removido = true;
				removerNo(p);
			}
		}
	}

	public void removerNo(No q) {
		No r;
		if (q.filhoEsquerda == null || q.filhoDireita == null) {
			if (q.pai == null) {
				raiz = null;
				q = null;
				return;
			}
			r = q;
		} else {
			r = successor(q);
			q.codigo = r.codigo;
		}

		No p;
		if (r.filhoEsquerda != null) {
			p = r.filhoEsquerda;
		} else {
			p = r.filhoDireita;
		}

		if (p != null) {
			p.pai = r.pai;
		}

		if (r.pai == null) {
			raiz = p;
		} else {
			if (r == r.pai.filhoEsquerda) {
				r.pai.filhoEsquerda = p;
			} else {
				r.pai.filhoDireita = p;
			}
			balancoRec(r.pai);
		}
		r = null;
	}

	public No rotaEsq(No n) {
		rotacoes++;
		System.out.println("Rotação Esquerda");
		No v = n.filhoDireita;
		v.pai = n.pai;

		n.filhoDireita = v.filhoEsquerda;

		if (n.filhoDireita != null) {
			n.filhoDireita.pai = n;
		}

		v.filhoEsquerda = n;
		n.pai = v;

		if (v.pai != null) {
			if (v.pai.filhoDireita == n) {
				v.pai.filhoDireita = v;
			} else if (v.pai.filhoEsquerda == n) {
				v.pai.filhoEsquerda = v;
			}
		}

		setBalanco(n);
		setBalanco(v);

		return v;
	}

	public No rotaDir(No n) {
		rotacoes++;
		System.out.println("Rotação Direita");
		No v = n.filhoEsquerda;
		v.pai = n.pai;

		n.filhoEsquerda = v.filhoDireita;

		if (n.filhoEsquerda != null) {
			n.filhoEsquerda.pai = n;
		}

		v.filhoDireita = n;
		n.pai = v;

		if (v.pai != null) {
			if (v.pai.filhoDireita == n) {
				v.pai.filhoDireita = v;
			} else if (v.pai.filhoEsquerda == n) {
				v.pai.filhoEsquerda = v;
			}
		}

		setBalanco(n);
		setBalanco(v);

		return v;
	}

	public No rotaEsqDir(No u) {
		rotacoes++;
		rotacoes++;
		System.out.println("Rotação Esquerda / Direita");
		u.filhoEsquerda = rotaEsq(u.filhoEsquerda);
		return rotaDir(u);
	}

	public No rotaDirEsq(No u) {
		rotacoes++;
		rotacoes++;
		System.out.println("Rotação Direita / Esquerda");
		u.filhoDireita = rotaDir(u.filhoDireita);
		return rotaEsq(u);
	}

	public No successor(No q) {
		if (q.filhoDireita != null) {
			No r = q.filhoDireita;
			while (r.filhoEsquerda != null) {
				r = r.filhoEsquerda;
			}
			return r;
		} else {
			No p = q.pai;
			while (p != null && q == p.filhoDireita) {
				q = p;
				p = q.pai;
			}
			return p;
		}
	}

	private int altura(No cur) {
		if (cur == null) {
			return -1;
		}
		if (cur.filhoEsquerda == null && cur.filhoDireita == null) {
			return 0;
		} else if (cur.filhoEsquerda == null) {
			return 1 + altura(cur.filhoDireita);
		} else if (cur.filhoDireita == null) {
			return 1 + altura(cur.filhoEsquerda);
		} else {
			return 1 + maximum(altura(cur.filhoEsquerda), altura(cur.filhoDireita));
		}
	}

	private int maximum(int a, int b) {
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}

	private void setBalanco(No cur) {
		cur.balanco = altura(cur.filhoDireita) - altura(cur.filhoEsquerda);
	}
	
	public void mostraArvore()
	{
		System.out.println("================================================");
		System.out.println("Árvore AVL");
		mostraArvore(raiz);
		System.out.println("");
		System.out.println("Fim Árvore AVL");
		System.out.println("================================================");
	}
	
	public void mostraArvore(No no)
	{
		boolean temEsq;
		boolean temDir;
		
		if (no.filhoEsquerda == null)
		{
			temEsq = false;
		}
		else
		{
			temEsq = true;
		}
		
		if (no.filhoDireita == null)
		{
			temDir = false;
		}
		else
		{
			temDir = true;
		}
		
		System.out.print(no.codigo);
		
		if (temDir || temEsq)
		{
			System.out.print("(");
			
			if (temEsq)
			{
				mostraArvore(no.filhoEsquerda);
			}
			else 
			{
				System.out.print("()");
			}
			
			System.out.print(" - ");
			
			if (temDir)
			{
				mostraArvore(no.filhoDireita);
			}
			else
			{
				System.out.print("()");
			}
			
			System.out.print(")");
		}
	}
}