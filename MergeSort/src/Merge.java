public class Merge {

	public static int compara = 0;
	public static int troca = 0;

	public static void main(String[] args) {
		int array[] = { 12, 8, 3, 20, 4, 16, 2, 10, 19, 6, 7, 14, 15, 1, 17, 9,
				5, 11, 13, 18 };

		System.out.println("[Numeros desordenados] ");
		printArray(array);

		System.out.println("\n");

		mergeSort(array);

		System.out.println("[Numeros ordenados] ");
		printArray(array);

		System.out.println("\n");
		System.out.println("Numero de comparacoes: " + compara);

		System.out.println("Trocas: " + troca);

	}

	private static int[] mergeSort(int[] array) {
		if (array.length == 1) {
			return array;
		} else {
			// Recebe parte esquerda do array
			int[] left = new int[array.length / 2];
			System.arraycopy(array, 0, left, 0, left.length);

			// Recebe parte direita do array
			int[] right = new int[array.length - left.length];
			System.arraycopy(array, left.length, right, 0, right.length);

			// sucessivamente até retornar 1
			left = mergeSort(left);
			right = mergeSort(right);

			// Une as "metades"
			merge(left, right, array);

			return array;
		}

	}

	private static void merge(int[] left, int[] right, int[] array) {
		int indiceEsquerdo = 0;
		int indiceDireito = 0;
		int arrayIndice = 0;

		while (indiceEsquerdo < left.length && indiceDireito < right.length) {
			if (left[indiceEsquerdo] < right[indiceDireito]) {
				array[arrayIndice] = left[indiceEsquerdo];
				indiceEsquerdo++;
				troca++;
			} else {
				array[arrayIndice] = right[indiceDireito];
				indiceDireito++;
			}
			arrayIndice++;
		}

		int[] resto;
		int indiceResto;
		if (indiceEsquerdo >= left.length) {
			resto = right;
			indiceResto = indiceDireito;
		} else {
			resto = left;
			indiceResto = indiceEsquerdo;
		}

		for (int i = indiceResto; i < resto.length; i++) {
			array[arrayIndice] = resto[i];
			arrayIndice++;
			compara++;
		}

	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
