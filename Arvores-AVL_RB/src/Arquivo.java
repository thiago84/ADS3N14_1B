import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Arquivo {
	public static AVL carregarAVL()
	{
		AVL avl = new AVL();
		No raiz = null;
		Pessoa pess = new Pessoa();
		String linha;

		try {
			FileReader fReader = new FileReader("contatos.txt");
			BufferedReader textReader = new BufferedReader(fReader);

			while ((linha = textReader.readLine()) != null) {
				pess = new Pessoa();
				String[] linhaSplit = linha.split("##");
				No no = new No();
				
				pess.setNome(linhaSplit[0]);
				pess.setFone(linhaSplit[1]);
				no.valor = pess;
				no.codigo = linhaSplit[0];
				
				no.pai = raiz;
				
				avl.insereAVL(no);
			}
			
			textReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}

		return avl;
	}
	
	public static RBT carregarRBT()
	{
		RBT rbt = new RBT();
		No raiz = null;
		Pessoa pess = new Pessoa();
		String linha;

		try {
			FileReader fReader = new FileReader("contatos.txt");
			BufferedReader textReader = new BufferedReader(fReader);

			while ((linha = textReader.readLine()) != null) {
				pess = new Pessoa();
				String[] linhaSplit = linha.split("##");
				No no = new No();
				
				pess.setNome(linhaSplit[0]);
				pess.setFone(linhaSplit[1]);
				no.valor = pess;
				no.codigo = linhaSplit[0];
				no.isPreto = false;
				
				no.pai = raiz;
				
				rbt.insereRBT(no);
			}
			
			textReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}

		return rbt;
	}
	
	public static void salvar(Arvore arv)
	{
		ArrayList<No> nos = recuperaNos(arv.raiz);
		
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("contatos.txt"), "utf-8"));
			
			for (No item : nos)
			{
				writer.write(item.valor.getNome() + "##" + item.valor.getFone() + "\n");
			}
		}
		catch (IOException ex) {}
		finally 
		{
			try
			{
				writer.close();
			}
			catch (IOException e) {}
		}
	}
	
	public static ArrayList<No> recuperaNos(No raiz)
	{
		ArrayList<No> fila = new ArrayList<No>();
		ArrayList<No> lista = new ArrayList<No>();
		
		// Add root
		fila.add(raiz);
		
		// While queue is not empty
		while(!fila.isEmpty())
		{
			No no = fila.get(0);
			lista.add(no);
			fila.remove(no);
			
			// Add left child
			if (no.filhoEsquerda != null)
			{
				fila.add(no.filhoEsquerda);
			}
			
			// Add right child
			if (no.filhoDireita != null)
			{
				fila.add(no.filhoDireita);
			}
		}
		
		return lista;
	}
}
