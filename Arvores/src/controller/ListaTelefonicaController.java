package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Contato;

import view.Screen;

public class ListaTelefonicaController {
	
	private Screen objScreen = new Screen();
	private Contato objContato = new Contato();
	private String contactFile = "contatos.txt"; 
	
	public ListaTelefonicaController() {
	}

	public void start(){
		
		loadFile(contactFile);
		
		char opt = ' ';
		String input;
		
		objScreen.showHead();
		
		while (opt != 's'){
		
			opt = Character.toLowerCase(objScreen.showSingleInputScreen
					("Aperte 'A' para adicionar contato\n" +
					 "Aperte 'R' para remover contato\n" +
					 "Aperte 'P' para procurar contato\n" +
					 "Aperte 'M' para mostrar todos contatos\n" +
					 "Aperte 'S' para salvar e encerrar\n",true));
		
			switch (opt){
				case 'a':
					
					String nome =  objScreen.showInputScreen("Insira nome",true);
					String tel =  objScreen.showInputScreen("Insira telefone",true);
					objContato.cadastrar(nome, tel);
				
					
					
				break;
				
				case 'p':
					input =  objScreen.showInputScreen("Digite o contato a ser pesquisado",true);
					
					if (objContato.procurar(input)){
						objScreen.showContact(objContato.getNome(),objContato.getTelefone(),objContato.getQtdComparacoes());
					}
					else {
						objScreen.showWarnMsg("Contato inexistente",3000);
					}
				break;
				
				case 'm':
					char sOpt = ' ';
					boolean sair = false;
					
					while (!sair){
					
						sOpt = Character.toLowerCase(objScreen.showSingleInputScreen("Aperte:\n 'A': Travessia prefixa \n 'B': Travessia infixa\n 'C': Travessia posfixa\n 'D': Busca em largura\n 'E': Busca em profundidade", true));
						
						switch (sOpt){
						
							case 'a':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Travessia prefixa");
								sair = true;
							break;
						
							case 'b':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Travessia infixa");
								sair = true;								
							break;
						
							case 'c':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Travessia posfixa");
								sair = true;								
							break;
						
							case 'd':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Busca em largura");
								sair = true;								
							break;
						
							case 'e':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Busca em profundidade");
								sair = true;								
							break;
							
							default:
								objScreen.showWarnMsg("Digite uma opção válida", 3000);
							break;
						}
					}
				break;
				
				case 'r':
					input =  objScreen.showInputScreen("Digite o contato a ser removido",true);
					
					if(objContato.excluir(input) == 1){
						objScreen.showWarnMsg("Contato removido com sucesso",2000);
					}
					else {
						objScreen.showWarnMsg("Contato não encontrado",2000);
					}
				break;
				
				case 's':
					saveFile(contactFile);
					objScreen.showMessage("Contatos salvos, aplicação encerrada");
				break;
			}
		}
	}
	
	
	private void loadFile(String filename) {
		
		try {
			
			Scanner arq = new Scanner(new FileReader(filename));
			
			while(arq.hasNext()) {				
				objContato.cadastrar(arq.nextLine(), arq.nextLine());
			}
		} 
		catch (FileNotFoundException e) {
			objScreen.showMessage(e.getMessage());
		}
	}
	
	public void saveFile(String filename) {
		
		FileWriter arq = null;
		try {
			arq = new FileWriter(filename,false);
			
			ArrayList<String[]> lstFinal = new ArrayList<>();
			
			lstFinal = objContato.listarContatos('c');
			
			for (int i=0;i < lstFinal.size();i++){
				arq.append(lstFinal.get(i)[0] +"\n");
				arq.append(lstFinal.get(i)[1] +"\n");
			}
			
			
		} catch (IOException e) {
			objScreen.showMessage(e.getMessage());
		} finally {
			if (arq != null)
				try {
					arq.close();
				} catch (IOException e) {
					objScreen.showMessage(e.getMessage());
				}
		}
	}
}
