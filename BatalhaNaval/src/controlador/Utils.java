package controlador;

public class Utils {
    
	// Converter a letra em Inteiro
	public static int converteLetraPraInteiro(char letra)
	{
		return ((int)Character.toLowerCase(letra)) - 97;
	}
}
