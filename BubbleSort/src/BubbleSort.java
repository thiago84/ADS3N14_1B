public class BubbleSort {

	public static void main(String[] args) {
		int array[] = { 8, 3, 4, 2, 6, 7, 1, 9, 5, 0 };

		System.out.print("[Numeros desordenados] ");
		for (int a = 0; a < array.length; a++) {
			System.out.print(array[a] + "  ");
		}
		System.out.println();

		int compara = 0;
		int troca = 0;

		int n = array.length;

		for (int a = 0; a < n; a++) {
			for (int b = 1; b < (n - a); b++) {

				compara++;

				int t;

				if (array[b - 1] > array[b]) {

					t = array[b - 1];

					array[b - 1] = array[b];

					array[b] = t;

					troca++;

				}
			}
		}

		System.out.print("[Numeros ordenados] ");
		for (int c = 0; c < array.length; c++) {
			System.out.print(array[c] + "  ");

		}
		System.out.println("\n");
		System.out.println("Numero de comparacoes: " + compara);

		System.out.println("Trocas: " + troca);

	}

}