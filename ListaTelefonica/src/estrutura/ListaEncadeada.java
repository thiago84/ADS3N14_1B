package estrutura;

import static java.lang.System.out;

public class ListaEncadeada<T extends Comparable<T>> {

	private Nodo<T> head; 
	private Nodo<T> tail; 

	public Nodo<T> getHead()
	{
		return head;
	}

	public void print()
	{
		Nodo<?> nodo = head;
		do {
			out.println(nodo.getData());
			nodo = nodo.getNext();
		} while (nodo != null);
	}

	public void insert(Nodo<T> novo)
	{
		novo.setNext(head);
		head = novo;

		if (tail == null)
			tail = head;
	}

	public void insert(Nodo<T> novo, Nodo<T> anterior)
	{
		if (anterior == tail) {
			tail.setNext((Nodo<T>)novo);
			tail = novo;
		} else {
			novo.setNext(anterior.getNext());
			anterior.setNext(novo);
		}
	}

	public void append(Nodo<T> novo)
	{
		tail.setNext(novo);
		tail = novo;
	}
}