package br.com.ivaniciojr.recursos;

public class Main {

	public static void main(String[] args) {
		
		Aluno junior = new Aluno();
		junior.setNome("Junior");
		junior.setNotas(new float[] { 6, 8, 7 });
		
		Aluno ivanicio = new Aluno();
		ivanicio.setNome("Ivanicio");
		ivanicio.setNotas(new float[] { 9, 8.5F, 7.2f });
	
		Aluno fulano = new Aluno();
		fulano.setNome("Fulano");
		fulano.setNotas(new float[] { 7.2F, 5.5F, 9.2f });
		
		Aluno[] vetor = new Aluno[] { junior, ivanicio, fulano };
		bubble(vetor);
		
		for (Aluno aluno: vetor) {
			System.out.println(aluno.getNome()+" "+aluno.getMedia());
		}
	}

	public static void bubble(Comparable[] vetor) {

		int tamanho = vetor.length;

		while (tamanho > 1) {
			for (int i = 0; i < tamanho-1; i++) {
				if (vetor[i].compareTo(vetor[i+1]) > 0) {
					Comparable auxiliar = vetor[i];
					vetor[i] = vetor[i+1];
					vetor[i+1] = auxiliar;
				}
			}
			tamanho--;
		}
	}

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
	}
}
