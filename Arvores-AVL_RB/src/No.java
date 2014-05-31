public class No {
	No pai;
	String codigo;
	Pessoa valor;
	No filhoEsquerda;
	No filhoDireita;
	int balanco;
	boolean isPreto;
	
	public No()
	{
		valor = new Pessoa();
		filhoEsquerda = null;
		filhoDireita = null;
		isPreto = false;
	}

	public void mostraNo() {
		System.out.print("{");
		System.out.print(codigo);
		System.out.print(", ");
		System.out.print(valor.getNome());
		System.out.print(", ");
		System.out.print(valor.getFone());
		System.out.print("} ");
		System.out.println("");
	}
	
	public void debug()
	{
		System.out.print("Pai null: ");
		System.out.println(pai == null);
	}
}
