package controlador;

import java.util.ArrayList;
import java.util.Random;

import modelo.*;


public class Controlador {
	
	ArrayList<Navio> listaNavio;
	ArrayList<NavioColocado> listaNaviosColocados;
	// Matriz 10 x 10
	String[][] tabela = new String[10][10];
	
	MatrizCenario matriz;
	int chances;

    
	// Construtor... Inicia as variaveis
	public Controlador() {
		
		listaNavio = new ArrayList<Navio>();
		listaNaviosColocados = new ArrayList<NavioColocado>();
		// Pontuacao Inicial
		chances = 15;
/*
 * 1 Porta Avioes
 * 2 Destroyer
 * 2 Fragata
 * 3 Torpedeiro
 * 5 Submarinos
 */
		
		listaNavio.add(new Navio("Porta-avioes", 5));
		listaNavio.add(new Navio("Destroyer", 4));
		listaNavio.add(new Navio("Destroyer", 4));
		listaNavio.add(new Navio("Fragata", 3));
		listaNavio.add(new Navio("Fragata", 3));
		listaNavio.add(new Navio("Torpedeiro", 2));
		listaNavio.add(new Navio("Torpedeiro", 2));
		listaNavio.add(new Navio("Torpedeiro", 2));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));

		matriz = new MatrizCenario();

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				tabela[i][j] = "0";
			}
		}

		montaTabela();
	}



	// Mostra tabela
	public void mostraTabela() {
		
		matriz.mostraTabela(this.tabela);
		
		String coordenada = matriz.msgOpcoes();
		if (coordenada.length() == 2)
		{
			tiro(coordenada);
		} else {
			matriz.msgErro("Digite opcao correta!");
			mostraTabela();
		}
		
	}
	

	// Coordenadas do tiro
	private void tiro(String coord)
	{
		int x = Integer.parseInt(coord.substring(1, 2));
		int y = Utils.converteLetraPraInteiro(coord.charAt(0));
		
		String value = tabela[x][y];
		
		if (value.equals("0"))
		{
			tabela[x][y] = "2";
			this.chances--;
			matriz.msgAcertouAgua();
			matriz.msgPontos(this.chances);
			
		} else if(value.equals("1"))
		{
			tabela[x][y] = "3";
			this.chances--;
			this.chances += 3;
			hitShip(x, y);
			matriz.msgPontos(this.chances);
		}
		else
		{
			matriz.msgErro("Opcao ja foi informada!");
		}
		
		verificaStatus();
		mostraTabela();
	}

  // Monta os navios na matriz
	private void montaTabela() {
		boolean ok = false;
		
		for (Navio item : listaNavio) {
			ok = false;
			while (!ok) {
								boolean horizontal = (new Random()).nextBoolean();
				int iniPosDin = (new Random()).nextInt(10 - item.getTamanho());
				int iniPosFix = (new Random()).nextInt(10);

				// verifica se o navio pode ser colocado na horizontal
				ok = checkShip(iniPosFix, iniPosDin, horizontal,
						item.getTamanho());

				// coloca o navio
				if (ok) {
					colocaNavio(iniPosFix, iniPosDin, horizontal,
							item.getTamanho(), item.getNome());
				}
			}
		}
	}

	// Checa o navio na matriz
	private boolean checkShip(int iniPosFix, int iniPosDin, boolean horizontal, int tamanho) {
		
		boolean ok = true;
		
		// Horizontal
		if (horizontal) {
			if (iniPosDin + tamanho <= 9) {
				for (int i = iniPosDin; i < 10 - tamanho; i++) {
					if (tabela[i][iniPosFix] == "1") {
						ok = false;
						break;
					}
				}
			} else {
				ok = false;
			}
		}
		// Vertical
		else {
			if (iniPosDin + tamanho <= 9) {
				for (int i = iniPosDin; i < 10 - tamanho; i++) {
					if (tabela[iniPosFix][i] == "1") {
						ok = false;
						break;
					}
				}
			}
			else
			{
				ok = false;
			}
		}

		return ok;
	}
	
	// Verifica navio atigido
		private void hitShip(int x, int y)
		{
			// For each deployed ship
			for (NavioColocado i : listaNaviosColocados)
			{
				// Cada Navio colocado
				for (String c : i.getcolocaCoordenada())
				{
					// Se encontrar, vai para o contador. 
					if (c.equals(x + "" + y)){
						i.setContador(i.getContador()+1);
						matriz.msgAcertou(i);
						if (i.getContador() == i.getTamanho())
						{
							this.chances += 5;
							matriz.msgDestruiu(i);
						}
						break;
					}
				}
				
			}
		}

// coloca o navio na matriz
	private void colocaNavio(int iniPosFix, int iniPosDin, boolean horizontal, int tamanho, String nome) {
		NavioColocado deployed = new NavioColocado(nome, tamanho);
		
		// Horizontal
		if (horizontal) {
			for (int i = iniPosDin; i < iniPosDin + tamanho; i++) {
				tabela[i][iniPosFix] = "1";
				deployed.addcolocaCoordenada(i + "" + iniPosFix);
			}
		}
		// Vertical
		else {
			for (int i = iniPosDin; i < iniPosDin + tamanho; i++) {
				tabela[iniPosFix][i] = "1";
				deployed.addcolocaCoordenada(iniPosFix + "" + i);
			}
		}
		
		listaNaviosColocados.add(deployed);
	}

	
    // Status
	private void verificaStatus()
	{
		verificaGanhou();
		verificaDerrota();
	}
	
    // Valida se Ganhou o jogo!!
	private void verificaGanhou()
	{
		boolean Ok = true;
		for(NavioColocado n : listaNaviosColocados)
		{
			if (n.getTamanho() != n.getContador())
			{
				Ok = false;
				break;
			}
		}
		
		if (Ok)
		{
			matriz.msgGanhou();
			System.exit(0);
		}
	}
	
    // Valida se perdeu o jogo
	private void verificaDerrota()
	{
		if(chances == 0)
		{
			matriz.msgPerdeu();
			System.exit(0);
		}
	}
	
}
