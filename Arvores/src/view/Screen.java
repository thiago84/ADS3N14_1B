package view;

import java.util.ArrayList;
import java.util.Scanner;

public class Screen {

	Boolean debug = true;
	
	public void showHead(){
		System.out.println("[ Lista Telefonica ]\n");
	}
	
	public void newScreen (){
		System.out.println("                                                      ");
	}
	
	public void showMessage(String msg){
		
		System.out.println(msg);
	}
	
	public void showDebugMessage(String msg){
		if (this.debug){
			System.out.println("DEBUG: " + msg);
		}
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void showContact(String nome, String telefone, int comp){
		System.out.println(" ");
		System.out.println("NOME: " + nome);
		System.out.println("TELEFONE: " + telefone);
		System.out.println("NUMERO DE COMPARAÇÕES: " + comp);
		System.out.println(" ");
		
	}
	
	public void showContactList(ArrayList<String[]> in,String order){
		System.out.println(" ");
		System.out.println("Ordenado por "+ order);
		for (int i=0;i<in.size();i++){
			System.out.print(" CONTATO NUMERO: " + (i + 1) + "\n");
			System.out.print(" NOME: "+ in.get(i)[0] + "\n");
			System.out.print(" TELEFONE: "+ in.get(i)[1] + "\n");
			System.out.println(" ");			
		}
		
	}
	
	public void showWarnMsg(String msg,int sleepTime){
		
		
		if (sleepTime < 1)
			return;
		
		System.out.println(msg);
		
		try {
			Thread.sleep(sleepTime);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public String showInputScreen(String msg,boolean vrfIn){
		
		
		Scanner sc = new Scanner(System.in);
		String ret = "";
		
		if (vrfIn){
			while (ret.equals("")){
				System.out.print(msg + ":");
				ret = sc.nextLine();
			}
		}
		else {
			System.out.print(msg + ":");
			ret = sc.nextLine();
		}
		
		return ret;
	}
	
	public char showSingleInputScreen(String msg,boolean vrfIn){
		
		
		Scanner sc = new Scanner(System.in);
		char ret = ' ';
		
		if (vrfIn){
			
			do {
				System.out.print(msg + ":");
				ret = sc.next().toCharArray()[0];
				
			} while (!Character.isLetter(ret));
		}
		else {
			System.out.print(msg + ":");
			ret = sc.next().charAt(0);
		}
		
		return ret;
	}

}
