
public class RBT extends Arvore{
	public int comparacoes;
	public int rotacoes;
	public boolean removido;
	
	private void resetaAtributos()
	{
		comparacoes = 0;
		rotacoes = 0;
	}

	public void insereRBT(No no) {
		resetaAtributos();
		insereRBT(raiz, no);
	}

	public void insereRBT(No p, No q) {
		if (p == null) {
			raiz = q;
			verificaNodo(q);
		} else {

			if (q.codigo.toLowerCase().compareTo(p.codigo.toLowerCase()) < 0) {
				comparacoes++;
				if (p.filhoEsquerda == null) {
					p.filhoEsquerda = q;
					q.pai = p;

					verificaNodo(q);
				} else {
					insereRBT(p.filhoEsquerda, q);
				}

			} else if (q.codigo.toLowerCase().compareTo(p.codigo.toLowerCase()) >= 0) {
				comparacoes++;
				if (p.filhoDireita == null) {
					p.filhoDireita = q;
					q.pai = p;

					verificaNodo(q);
				} else {
					insereRBT(p.filhoDireita, q);
				}
			}
		}
	}


	public void removeRBT(String k) {
		removido = false;
		resetaAtributos();
		removeRBT(raiz, k);
	}
	
	public void removeRBT(No p, String q) {
		if (p == null) {
			return;
		} else {
			comparacoes++;
			if (p.codigo.toLowerCase().compareTo(q.toLowerCase()) > 0) {
				removeRBT(p.filhoEsquerda, q);
			} else if (p.codigo.toLowerCase().compareTo(q.toLowerCase()) < 0) {
				removeRBT(p.filhoDireita, q);
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
			verificaNodo(r.pai);
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

		return v;
	}

	public No rotaDir(No n) {
		if (n == null) return null;
		
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

		return v;
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
	
	public void mostraArvore()
	{
		System.out.println("================================================");
		System.out.println("Árvore RBT");
		mostraArvore(raiz);
		System.out.println("");
		System.out.println("Fim Árvore RBT");
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
		
		if (!no.isPreto) System.out.print("(R)");
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
	
	public void caso1(No no)
	{
		raiz.isPreto = true;
	}
	
	public void caso3(No no)
	{
		No pai = no.pai;
		No avo = no.pai.pai;
		No tio;
		
		if (no.pai.pai.filhoDireita == no.pai)	tio = no.pai.pai.filhoEsquerda;
		else tio = no.pai.pai.filhoDireita;
		
		if (tio != null) tio.isPreto = true;
		pai.isPreto = true;
		avo.isPreto = false;
		
		verificaNodo(avo);
	}
	
	public void caso4(No no)
	{
		No pai = no.pai;
		No avo = no.pai.pai;
		boolean paiEsq;
		
		if (avo.filhoDireita == null) paiEsq = true;
		else if (avo.filhoEsquerda == null) paiEsq = false;
		else if (avo.filhoDireita == pai) paiEsq = false;
		else paiEsq = true;
		
		if (paiEsq) rotaEsq(pai);
		else rotaDir(pai);
		caso5(pai);
	}
	
	public void caso5(No no)
	{
		No pai = no.pai;
		No avo = no.pai.pai;
		boolean paiEsq;
	
		if (avo.filhoDireita == null) paiEsq = true;
		else if (avo.filhoEsquerda == null) paiEsq = false;
		else if (avo.filhoDireita == pai) paiEsq = false;
		else paiEsq = true;
		
		if (paiEsq) rotaDir(avo);
		else rotaEsq(avo);
		
		pai.isPreto = true;
		avo.isPreto = false;
	}
	
	public void verificaNodo(No no)
	{
		boolean paiEsq;
		boolean noEsq;
		
		if (no.pai != null)
		{
			// noEsq
			if (no.pai.filhoDireita == null) noEsq = true;
			else if (no.pai.filhoEsquerda == null) noEsq = false;
			else if (no.pai.filhoDireita == no) noEsq = false;
			else noEsq = true;
			
			if (no.pai.isPreto)
			{
				return;
			}
			else
			{
				// paiEsq
				if (no.pai.pai.filhoDireita == null) paiEsq = true;
				else if (no.pai.pai.filhoEsquerda == null) paiEsq = false;
				else if (no.pai.pai.filhoDireita == no) paiEsq = false;
				else paiEsq = true;
				
				if (paiEsq)
				{
					if (no.pai.pai.filhoDireita != null && !no.pai.pai.filhoDireita.isPreto) caso3(no);
					else if (no.pai.pai.filhoDireita == null || no.pai.pai.filhoDireita.isPreto)
					{
						if (noEsq != paiEsq) caso4(no);
						else caso5(no);
					}
				}
				else if (!paiEsq)
				{
					if (no.pai.pai.filhoEsquerda != null && !no.pai.pai.filhoEsquerda.isPreto) caso3(no);
					else if (no.pai.pai.filhoEsquerda == null || no.pai.pai.filhoEsquerda.isPreto)
					{
						if (noEsq != paiEsq) caso4(no);
						else caso5(no);
					}
				}
			}
		}
		else
		{
			if (raiz == no)
			{
				if (!no.isPreto)
				{
					caso1(no);
				}
			}
		}
		
	}
}
