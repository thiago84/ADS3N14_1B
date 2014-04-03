package modelo;

import java.util.ArrayList;

public class NavioColocado extends Navio {
	private ArrayList<String> colocaCoordenada = new ArrayList<String>();
	private int contador;


	public NavioColocado(String nome, int tamanho)
	{
		super(nome, tamanho);
		this.contador = 0;
	}
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public ArrayList<String> getcolocaCoordenada() {
		return colocaCoordenada;
	}

	public void setcolocaCoordenada(ArrayList<String> colocaCoordenada) {
		this.colocaCoordenada = colocaCoordenada;
	}
	
	public void addcolocaCoordenada(String coord)
	{
		this.colocaCoordenada.add(coord);
	}
	

}
