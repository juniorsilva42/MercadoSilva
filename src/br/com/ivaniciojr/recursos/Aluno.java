package br.com.ivaniciojr.recursos;

public class Aluno implements Comparable<Aluno> {
	
	private float notas[];
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNotas(float[] notas) {
		this.notas = notas;
	}

	public float[] getNotas() {
		return notas;
	}
	
	public float getMedia () {
		float soma = 0; 
		for (float nota: notas)
			soma += nota;
		
		return soma / notas.length;
	}
	
	public void setNotas () {
		this.notas = notas;
	}
	
	@Override	
	public int compareTo (Aluno aluno) {
		
		float media1 = this.getMedia();
		float media2 = aluno.getMedia();
		
		if (media1 < media2 ) {
			return -1; 
		} else if (media1 > media2) {
			return 1;
		} else {
			return 0;
		}
	}

}
