package estrutura;



public class ListaOrdenada<T extends Comparable<T>> extends ListaEncadeada<T> {

	private Nodo<T> findBefore(Nodo<T> novo) {
		Nodo<T> atual = getHead();
		Nodo<T> anterior = null;

		while (atual != null) {
			int comp = atual.compareTo(novo);
			if (comp < 0) {
				anterior = atual;
				atual = atual.getNext();
			}
			if (comp == 0)
				return atual;
			if (comp > 0)
				return anterior;
		}

		return anterior;
	}

	@Override
	public void insert(Nodo<T> novo)
	{
		Nodo<T> anterior = findBefore(novo);
		if (anterior == null) {
			super.insert(novo);
		} else {
			super.insert(novo, anterior);
		}
	}
	
	public Nodo<T> getPrev(Nodo<T> atual){
		if(atual != null){
			Nodo<T> aux = this.getHead();
			while(aux != null){
				if(aux.getNext() != null){
					if(aux.getNext().compareTo(atual) == 0){
						 return aux;
					}
				}
				aux = aux.getNext();
			}
			
		} else {
			return this.getHead();
		}
		
		return null;
	}
	
	public Nodo<T> getNext(Nodo<T> atual){
		if(atual != null){
			 return atual.getNext();
		} else {
			 return this.getHead();
		}

	}


	@Override
	public void insert(Nodo<T> novo, Nodo<T> anterior)
	{
		insert(novo);
	}

	@Override
	public void append(Nodo<T> novo)
	{
		insert(novo);
	}
}