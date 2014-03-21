package modelo;

public class Pessoa implements Comparable<Pessoa> {
	private String nome;
	private String telefone;
	
	public Pessoa(String nome, String telefone){
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public Pessoa(){
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int compareTo(Pessoa p) {
		return this.getNome().compareToIgnoreCase(p.getNome());
	}

	@Override
	public String toString(){
		return this.getNome()+": "+this.getTelefone();
	}
	
	
}

