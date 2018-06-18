package br.com.ivaniciojr.recursos;

public class Main {

	/*
	public static void main(String[] args) {
		
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();
		Aluno aluno3 = new Aluno();
		Aluno aluno4 = new Aluno();
		Aluno aluno5 = new Aluno();
		
		aluno1.setNome("Ivanicio Junior");
		aluno2.setNome("Lara Rodrigues");
		aluno3.setNome("Chico Dino");
		aluno4.setNome("Fulano de tal");
		aluno5.setNome("Larissa Rodrigues");

		aluno2.setNotas(new float[] { 4.9F, 9.3F, 3.4F } );
		aluno3.setNotas(new float[] { 8.9F, 5.5F, 8.8F } );
		aluno4.setNotas(new float[] { 7.2F, 8.2F, 1.4F });
		aluno5.setNotas(new float[] { 6.8F, 7.8F, 9.8F });
		aluno1.setNotas(new float[] { 7.9F, 8.3F, 8.4F } );

		Aluno[] vetorAlunos = new Aluno[] { aluno1, aluno2, aluno3, aluno4, aluno5 } ;

		bubbleSort(vetorAlunos);

		int i = 1;

		System.out.println("Lista de alunos em ordem decrescente: \n");
		for (Aluno aluno: vetorAlunos) {
			System.out.println((i)+". "+aluno.getNome()+" - "+aluno.getMedia());
			i++;
		}
	}

	public static void bubbleSort (Comparable[] v) {

		int size = v.length - 1;

		while (size > 1) {
			for (int i = 0; i < size; i++) {
				if (v[i].compareTo(v[i+1]) < 0) {
					Comparable aux = v[i];
					v[i] = v[i+1];
					v[i+1] = aux;
				}
			}
			size--;
		}
	}

	public static void quickSort (Comparable[] v, int low, int high) {

		int i = low, j = high;
		int aux;
		Comparable pivot = v[(low+high)];

		while (i <= j) {
			while (v[i].compareTo(pivot) <= 0)
				i++;

			while (v[j].compareTo(pivot) >= 0)
				j--;

			if (i <= j) {
				aux = v[i];

			}
		}
	}


	/*
	*
	* Começa verificando pelo valor mais significativo, nesse caso, o ano.
	*

	public static int compare(Data data1, Data data2) {

		if (data1.getAno() < data2.getAno()) {
			return -1;
		} else if (data1.getAno() > data2.getAno()) {
			return 1;
		} else {
			// anos iguais
			if (data1.getMes() < data2.getMes()) {
				return -1;
			} else if (data1.getMes() > data2.getMes()) {
				return 1;
			} else {
				return data1.getDia() - data2.getDia();
			}
		}
	}*/
}
